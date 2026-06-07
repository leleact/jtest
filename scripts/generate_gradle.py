#!/usr/bin/env python3
"""Generate Gradle build files for every jtest subproject.

Reads parsed POM JSON, propagates parent properties/deps, then emits:
  - settings.gradle.kts include() lines for each leaf module
  - build.gradle.kts in each leaf module directory
  - removes legacy pom.xml files at the very end (only after green build)

This script is intentionally idempotent: re-running it overwrites the
generated Gradle files.
"""
from __future__ import annotations

import json
import os
import re
import sys
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
# Snapshot of every Maven POM at the point of the Gradle migration. After
# the POM files were deleted this JSON remains the source of truth for
# the generator. Re-run `scripts/analyze_poms.py` against any restored
# POM tree if you need to refresh it.
POM_JSON = ROOT / "scripts" / ".cache" / "poms.json"

# ---------------------------------------------------------------------------
# Maven coordinate -> Gradle catalog alias (libs.xxx)
# ---------------------------------------------------------------------------
# Only coordinates with an EXPLICIT version in the POMs get a catalog alias.
# Coordinates without a version are managed by a BOM (spring-boot,
# spring-cloud, log4j, etc.) and Gradle resolves them through the imported
# platforms in the convention plugin.

CATALOG_MAP = {
    # Persistence
    "org.mybatis:mybatis": "libs.mybatis",
    "org.mybatis:mybatis-spring": "libs.mybatis.spring",
    "org.mybatis.spring.boot:mybatis-spring-boot-starter": "libs.mybatis.spring.boot.starter",
    "com.github.pagehelper:pagehelper-spring-boot-starter": "libs.pagehelper.spring.boot.starter",
    "com.alibaba:druid": "libs.druid",
    "org.apache.shardingsphere:shardingsphere-jdbc": "libs.shardingsphere.jdbc",
    "com.mysql:mysql-connector-j": "libs.mysql.connector.j",
    "org.mariadb.jdbc:mariadb-java-client": "libs.mariadb.client",
    "ch.vorburger.mariaDB4j:mariaDB4j": "libs.mariadb4j",

    # RPC / network
    "org.apache.dubbo:dubbo": "libs.dubbo",
    "org.apache.dubbo:dubbo-spring-boot-starter": "libs.dubbo.spring.boot.starter",
    "org.apache.dubbo:dubbo-dependencies-zookeeper-curator5": "libs.dubbo.zookeeper.curator5",
    "io.grpc:grpc-netty-shaded": "libs.grpc.netty.shaded",
    "io.grpc:grpc-protobuf": "libs.grpc.protobuf",
    "io.grpc:grpc-stub": "libs.grpc.stub",
    "com.google.protobuf:protobuf-java": "libs.protobuf.java",
    "io.vertx:vertx-core": "libs.vertx.core",
    "io.vertx:vertx-web": "libs.vertx.web",
    "org.apache.httpcomponents:httpclient": "libs.apache.httpclient",
    "org.apache.httpcomponents:httpmime": "libs.apache.httpmime",
    "javax.servlet:servlet-api": "libs.legacy.servlet.api",

    # Spring extras
    "org.springframework.cloud:spring-cloud-starter-netflix-ribbon":
        "libs.spring.cloud.starter.netflix.ribbon",

    # Logging
    "com.lmax:disruptor": "libs.disruptor",

    # Util
    "org.projectlombok:lombok": "libs.lombok",
    "org.mapstruct:mapstruct": "libs.mapstruct",
    "com.google.guava:guava": "libs.guava",
    "com.thoughtworks.xstream:xstream": "libs.xstream",
    "com.alibaba:fastjson": "libs.fastjson",
    "org.javassist:javassist": "libs.javassist",
    "org.bouncycastle:bcprov-jdk18on": "libs.bouncycastle",
    "io.reactivex.rxjava3:rxjava": "libs.rxjava",

    # JAXB / activation
    "jakarta.el:jakarta.el-api": "libs.jakarta.el.api",
    "org.glassfish.expressly:expressly": "libs.glassfish.expressly",
    "javax.xml.bind:jaxb-api": "libs.jaxb.api",
    "com.sun.xml.bind:jaxb-impl": "libs.jaxb.impl",
    "javax.activation:javax.activation-api": "libs.javax.activation.api",

    # Findbugs
    "com.google.code.findbugs:jsr305": "libs.findbugs.jsr305",
    "com.google.code.findbugs:annotations": "libs.findbugs.annotations",

    # Apache commons
    "commons-io:commons-io": "libs.commons.io",
    "commons-beanutils:commons-beanutils": "libs.commons.beanutils",
}

# Scope -> Gradle configuration name
SCOPE_MAP = {
    None: "implementation",
    "": "implementation",
    "compile": "implementation",
    "provided": "compileOnly",
    "runtime": "runtimeOnly",
    "test": "testImplementation",
    "import": None,
    "system": None,
}

# Modules whose Maven groupId starts with this prefix are local subprojects.
INTERNAL_GROUP_PREFIX = "com.github.leleact"


def load_poms():
    return json.loads(POM_JSON.read_text(encoding="utf-8"))

def build_index(poms):
    """Index by (groupId, artifactId) -> pom record so we can walk parents."""
    by_ga = {}
    by_path = {}
    for p in poms:
        ga = (p.get("groupId"), p.get("artifactId"))
        by_ga[ga] = p
        by_path[p["path"]] = p
    return by_ga, by_path


def parent_chain(pom, by_ga, by_path):
    """Yield pom then each ancestor (root pom last)."""
    seen = set()
    current = pom
    while current is not None and current["path"] not in seen:
        seen.add(current["path"])
        yield current
        parent = current.get("parent")
        if not parent:
            break
        key = (parent.get("groupId"), parent.get("artifactId"))
        current = by_ga.get(key)
    return


def collect_properties(pom, by_ga, by_path):
    """Merge properties from root -> child (child wins)."""
    chain = list(parent_chain(pom, by_ga, by_path))
    merged = {}
    for ancestor in reversed(chain):
        merged.update(ancestor.get("properties") or {})
    # Built-in placeholders
    merged.setdefault("project.version", pom.get("version", "1.0-SNAPSHOT"))
    merged.setdefault("project.groupId", pom.get("groupId", ""))
    merged.setdefault("project.artifactId", pom.get("artifactId", ""))
    return merged


PROP_RE = re.compile(r"\$\{([^}]+)\}")


def resolve(value, props):
    if value is None:
        return None
    seen = 0
    while seen < 8:
        m = PROP_RE.search(value)
        if not m:
            return value
        key = m.group(1)
        if key not in props:
            return value
        value = value[: m.start()] + props[key] + value[m.end():]
        seen += 1
    return value


def collect_dependencies(pom, by_ga, by_path):
    """Inherit dependencies from each ancestor; child entries replace parent."""
    chain = list(parent_chain(pom, by_ga, by_path))
    # ancestors first so child overrides
    merged = {}
    order = []
    for ancestor in reversed(chain):
        for d in ancestor.get("dependencies") or []:
            key = (d.get("groupId"), d.get("artifactId"), d.get("classifier"))
            if key not in merged:
                order.append(key)
            merged[key] = d
    return [merged[k] for k in order]


def collect_dep_mgmt(pom, by_ga, by_path):
    chain = list(parent_chain(pom, by_ga, by_path))
    merged = {}
    order = []
    for ancestor in reversed(chain):
        for d in ancestor.get("dependency_management") or []:
            key = (d.get("groupId"), d.get("artifactId"))
            if key not in merged:
                order.append(key)
            merged[key] = d
    return [merged[k] for k in order]


def collect_plugins(pom, by_ga, by_path):
    chain = list(parent_chain(pom, by_ga, by_path))
    out = []
    for ancestor in reversed(chain):
        for pl in (ancestor.get("plugins") or []):
            out.append(pl)
    return out

def gradle_dep_notation(dep, props, internal_artifact_to_path):
    group = dep.get("groupId") or ""
    artifact = dep.get("artifactId") or ""
    version_raw = dep.get("version")
    version = resolve(version_raw, props)

    # Internal cross-module dependency
    if group.startswith(INTERNAL_GROUP_PREFIX):
        path = internal_artifact_to_path.get(artifact)
        if path:
            return ("project", path, None)
        # Unknown internal artifact: fall through to a literal coord.

    key = f"{group}:{artifact}"
    alias = CATALOG_MAP.get(key)
    if alias:
        return ("alias", alias, None)

    # No catalog hit. Decide whether to add a version literal.
    if version:
        # Resolve internal $ that may remain (rare).
        return ("literal", f"{group}:{artifact}:{version}", None)
    return ("managed", f"{group}:{artifact}", None)


def render_dep_line(config, notation, dep):
    """Return the Kotlin DSL line for one dependency entry."""
    kind, value, _ = notation
    excl = dep.get("exclusions") or []

    # Skip type=pom dependencies that import BOMs at module level;
    # we'll re-add them as platform(...) entries explicitly when needed.
    if dep.get("type") == "pom" and dep.get("scope") == "import":
        return None

    # type=pom (non-import): treat as platform() artifact.
    is_platform = dep.get("type") == "pom" and dep.get("scope") != "import"

    if kind == "project":
        ref = f'project("{value}")'
    elif kind == "alias":
        ref = value  # libs.foo.bar
    else:  # literal or managed
        ref = f'"{value}"'

    if is_platform:
        ref = f"platform({ref})"

    if excl:
        excl_lines = []
        for e in excl:
            g = e.get("groupId") or ""
            a = e.get("artifactId") or ""
            excl_lines.append(f'        exclude(group = "{g}", module = "{a}")')
        body = "\n".join(excl_lines)
        return f'    "{config}"({ref}) {{\n{body}\n    }}'

    return f'    "{config}"({ref})'


SPECIAL_CONVENTION_ANCESTORS = {
    "servicecomb": "servicecomb",
}

# Per-module extra lines to append to the dependencies block. Used for
# things like annotation processor paths that Maven configures through
# the compiler plugin and that we can't infer from <dependencies> alone.
EXTRA_DEPENDENCY_LINES = {
    "mapstruct": [
        '    "annotationProcessor"("org.mapstruct:mapstruct-processor:${libs.versions.mapstruct.get()}")',
        '    "testAnnotationProcessor"("org.mapstruct:mapstruct-processor:${libs.versions.mapstruct.get()}")',
    ],
    "vertx": [
        '    "annotationProcessor"("io.vertx:vertx-codegen:${libs.versions.vertx.get()}:processor")',
        '    "annotationProcessor"("io.vertx:vertx-service-proxy:${libs.versions.vertx.get()}")',
    ],
    # log4j2-test exercises log4j-core APIs directly.
    "log4j2-test": [
        '    "testImplementation"("org.apache.logging.log4j:log4j-core")',
    ],
}

# Per-module extra blocks appended after the dependencies block (e.g. to
# mirror surefire <systemPropertyVariables> entries in Gradle's Test task).
EXTRA_TASK_BLOCKS = {
    "spring-boot-propertysource": [
        'tasks.named<Test>("test") {',
        '    systemProperty("environmentToken", "mvn-token")',
        '}',
        '',
    ],
}


def classify_module(pom, plugins, has_internal_grpc_plugin, ancestor_artifacts):
    """Pick which convention plugin to apply."""
    packaging = pom.get("packaging") or "jar"
    plugin_ids = {f"{p.get('groupId')}:{p.get('artifactId')}" for p in plugins}

    special = None
    for a in ancestor_artifacts:
        if a in SPECIAL_CONVENTION_ANCESTORS:
            special = SPECIAL_CONVENTION_ANCESTORS[a]
            break

    if special:
        kind = special
    elif packaging == "war":
        kind = "war"
    elif "org.springframework.boot:spring-boot-maven-plugin" in plugin_ids:
        kind = "spring-boot"
    else:
        kind = "java"

    extras = []
    if has_internal_grpc_plugin:
        extras.append("protobuf")
    if "org.apache.maven.plugins:maven-shade-plugin" in plugin_ids:
        extras.append("shadow")
    if "org.apache.maven.plugins:maven-assembly-plugin" in plugin_ids:
        extras.append("shadow")

    return kind, extras


def render_build_file(pom, by_ga, by_path, internal_index, gradle_path):
    packaging = pom.get("packaging") or "jar"
    props = collect_properties(pom, by_ga, by_path)
    deps = collect_dependencies(pom, by_ga, by_path)
    plugins = collect_plugins(pom, by_ga, by_path)

    # gRPC modules pull the protobuf-maven-plugin in their parent.
    has_protobuf = any(
        (p.get("artifactId") == "protobuf-maven-plugin") for p in plugins
    )

    ancestor_artifacts = [a.get("artifactId") for a in parent_chain(pom, by_ga, by_path)]
    kind, extras = classify_module(pom, plugins, has_protobuf, ancestor_artifacts)

    lines = ["// Auto-generated from pom.xml by scripts/generate_gradle.py", "// Do not edit by hand.", ""]
    lines.append("plugins {")
    if kind == "war":
        lines.append('    id("jtest.war-conventions")')
    elif kind == "spring-boot":
        lines.append('    id("jtest.spring-boot-conventions")')
    elif kind == "servicecomb":
        lines.append('    id("jtest.servicecomb-conventions")')
    else:
        lines.append('    id("jtest.java-conventions")')
    if "protobuf" in extras:
        lines.append('    id("com.google.protobuf")')
    if "shadow" in extras:
        lines.append('    id("com.gradleup.shadow")')
    lines.append("}")
    lines.append("")
    lines.append('description = ' + json.dumps(pom.get("name") or pom.get("artifactId")))
    lines.append("")

    # Imported BOMs as platform() entries (in addition to the global ones
    # already imported in the convention plugin).
    dep_mgmt = collect_dep_mgmt(pom, by_ga, by_path)
    extra_boms = []
    for d in dep_mgmt:
        if d.get("type") == "pom" and d.get("scope") == "import":
            g = d.get("groupId"); a = d.get("artifactId"); v = resolve(d.get("version"), props)
            gakey = f"{g}:{a}"
            # spring-boot, spring-cloud, log4j, spring-statemachine are already global.
            if gakey in {
                "org.springframework.boot:spring-boot-dependencies",
                "org.springframework.cloud:spring-cloud-dependencies",
                "org.apache.logging.log4j:log4j-bom",
                "org.springframework.statemachine:spring-statemachine-bom",
            }:
                continue
            # Already imported by the servicecomb convention plugin.
            if kind == "servicecomb" and gakey == "org.apache.servicecomb:java-chassis-dependencies":
                continue
            extra_boms.append((g, a, v))

    # dependencies block
    lines.append("dependencies {")
    if extra_boms:
        for g, a, v in extra_boms:
            if g == "org.apache.dubbo" and a == "dubbo-bom":
                lines.append("    api(platform(libs.bom.dubbo))")
            elif g == "org.apache.servicecomb" and a == "java-chassis-dependencies":
                lines.append("    api(platform(libs.bom.java.chassis))")
            else:
                lines.append(f'    api(platform("{g}:{a}:{v}"))')

    for d in deps:
        scope = d.get("scope")
        config = SCOPE_MAP.get(scope, "implementation")
        if config is None:
            continue
        notation = gradle_dep_notation(d, props, internal_index)
        # Maven's `provided` puts the dep on the test compile classpath
        # too. Gradle's `compileOnly` does not, so we emit both
        # configurations for parity with the Maven build.
        configs = [config]
        if scope == "provided":
            configs.append("testImplementation")
        for cfg in configs:
            line = render_dep_line(cfg, notation, d)
            if line:
                lines.append(line)

    # Module-specific extras (annotation processors, missing servlet API, etc.)
    for extra in EXTRA_DEPENDENCY_LINES.get(pom.get("artifactId"), []):
        lines.append(extra)

    lines.append("}")
    lines.append("")

    # Per-kind extra config
    if "protobuf" in extras:
        lines.append('// gRPC + Protobuf code generation.')
        lines.append('val catalog = extensions.getByType<VersionCatalogsExtension>().named("libs")')
        lines.append('val protobufVersion = catalog.findVersion("protobuf").orElseThrow().requiredVersion')
        lines.append('val grpcVersion = catalog.findVersion("grpc").orElseThrow().requiredVersion')
        lines.append('protobuf {')
        lines.append('    protoc {')
        lines.append('        artifact = "com.google.protobuf:protoc:$protobufVersion"')
        lines.append('    }')
        lines.append('    plugins {')
        lines.append('        create("grpc") {')
        lines.append('            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"')
        lines.append('        }')
        lines.append('    }')
        lines.append('    generateProtoTasks {')
        lines.append('        all().configureEach {')
        lines.append('            plugins {')
        lines.append('                create("grpc")')
        lines.append('            }')
        lines.append('        }')
        lines.append('    }')
        lines.append('}')
        lines.append('')

    if "shadow" in extras:
        # Detect mainClass from POM
        main_class = None
        for pl in plugins:
            if pl.get("artifactId") in {"maven-shade-plugin", "maven-assembly-plugin"}:
                # We extracted plugin meta but not nested configuration values.
                pass
        # Hard-coded mapping for the build-example modules:
        artifact_to_main = {
            "build-shade-jar": "com.github.leleact.jtest.build.shade.jar.Main",
            "build-one-jar": "com.github.leleact.jtest.build.one.jar.Main",
            "build-out-jar": "com.github.leleact.jtest.build.out.jar.Main",
        }
        main_class = artifact_to_main.get(pom.get("artifactId"))
        if main_class:
            lines.append('tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {')
            lines.append('    archiveClassifier.set("shaded")')
            lines.append(f'    manifest {{ attributes["Main-Class"] = "{main_class}" }}')
            lines.append('    mergeServiceFiles()')
            lines.append('}')
            lines.append('')

    # Per-module extra blocks (e.g. test JVM system properties).
    for block_line in EXTRA_TASK_BLOCKS.get(pom.get("artifactId"), []):
        lines.append(block_line)

    return "\n".join(lines) + "\n"

def build_internal_index(poms):
    """artifactId -> Gradle project path like ':spring:spring-boot:spring-boot-web'."""
    index = {}
    for p in poms:
        if p.get("packaging") == "pom":
            continue
        path = Path(p["path"]).parent.as_posix()
        if path == ".":
            continue
        gradle_path = ":" + path.replace("/", ":")
        index[p["artifactId"]] = gradle_path
    return index


def main():
    poms = load_poms()
    by_ga, by_path = build_index(poms)
    internal_index = build_internal_index(poms)

    leaves = [p for p in poms if p.get("packaging") in (None, "jar", "war")]

    # 1. settings.gradle.kts include() block.
    settings_path = ROOT / "settings.gradle.kts"
    current = settings_path.read_text(encoding="utf-8")
    begin = "// === BEGIN GENERATED SUBPROJECTS ==="
    end = "// === END GENERATED SUBPROJECTS ==="
    assert begin in current and end in current, "marker not found in settings.gradle.kts"

    include_lines = []
    for p in sorted(leaves, key=lambda x: x["path"]):
        gradle_path = internal_index.get(p["artifactId"])
        if not gradle_path:
            continue
        include_lines.append(f'include("{gradle_path[1:]}")')

    new_block = begin + "\n" + "\n".join(include_lines) + "\n" + end
    pattern = re.compile(re.escape(begin) + r".*?" + re.escape(end), re.DOTALL)
    new_settings = pattern.sub(new_block, current)
    settings_path.write_text(new_settings, encoding="utf-8")
    print(f"settings.gradle.kts: {len(include_lines)} subprojects")

    # 2. build.gradle.kts per leaf module.
    written = 0
    for p in leaves:
        gradle_path = internal_index.get(p["artifactId"])
        if not gradle_path:
            continue
        body = render_build_file(p, by_ga, by_path, internal_index, gradle_path)
        target = ROOT / Path(p["path"]).parent / "build.gradle.kts"
        target.write_text(body, encoding="utf-8")
        written += 1
    print(f"build.gradle.kts files written: {written}")

    # 3. Root build.gradle.kts (subprojects-level convention if needed).
    root_build = ROOT / "build.gradle.kts"
    if not root_build.exists():
        root_build.write_text("// Root project: no source. All shared config is in buildSrc convention plugins.\n", encoding="utf-8")
        print("root build.gradle.kts: created")

    # 4. Aggregator-only POM directories: write an empty build.gradle.kts so
    # Gradle treats them as containers without sources. Actually NOT needed
    # because we only include leaf subprojects in settings.gradle.kts.

    print("done.")


if __name__ == "__main__":
    main()
