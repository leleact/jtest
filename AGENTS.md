# Repository Guidelines for Agents

This file orients automated coding agents (Codex et al.) working in the
`jtest` repository. Human contributors should read `README.md` first; this
document focuses on the conventions an agent must respect to keep the
multi-module build healthy.

## Project Snapshot

- Language / runtime: Java 21 (toolchain pinned in `buildSrc`).
- Build system: Gradle (Kotlin DSL) via the wrapper. Do **not** invoke a
  globally-installed `gradle`; always use `./gradlew` or `gradlew.bat`.
- Structure: ~150 leaf modules grouped by topic (`jdk/`, `spring/`,
  `dubbo/`, `grpc/`, `mybatis/`, `log/`, `algorithm/`, ...). Each leaf is
  a learning / demo module; aggregator directories contain no sources.
- Shared configuration lives in `buildSrc/` as convention plugins and
  in `gradle/libs.versions.toml` (single version catalog).
- CI: GitHub Actions workflow `.github/workflows/test.yml` runs
  `./gradlew --no-daemon build` on Ubuntu with Temurin JDK 21.

## Build and Test Commands

Run from the repository root unless noted:

```bash
./gradlew build                          # compile + test + assemble all modules
./gradlew test                           # run tests in every module
./gradlew :jdk:jdk-string:test           # test a single module
./gradlew :spring:spring-boot:spring-boot-web:bootRun   # run a Spring Boot demo
./gradlew :<path>:build --info           # debug a single-module failure
```

Notes:

- The Gradle daemon is configured for a 4 GiB heap (`gradle.properties`).
  Lower-memory environments may need `--no-daemon` and `-Dorg.gradle.workers.max=2`.
- Parallel execution and the build cache are enabled by default; treat
  tasks as parallel-safe and never write to another module's output dir.
- Many demo modules ship `@Disabled` tests that need external brokers
  (Zookeeper, MySQL, RabbitMQ, etc.). `failOnNoDiscoveredTests = false`
  is intentional; do not "fix" empty test runs by removing the option.

## Module Layout Rules

- Module `build.gradle.kts` files carry a header
  `// Auto-generated from pom.xml by scripts/generate_gradle.py`.
  Those files are regenerated from `scripts/.cache/poms.json` and must
  stay machine-writable. Prefer changing the generator or the catalog
  over hand-editing a generated build file. If a manual tweak is
  unavoidable, drop the auto-generated header and document the
  divergence in a comment so the next regeneration does not silently
  clobber it.
- New subprojects must be registered in `settings.gradle.kts` between
  the `BEGIN GENERATED SUBPROJECTS` / `END GENERATED SUBPROJECTS`
  markers, ideally by re-running `scripts/generate_gradle.py`.
- Apply exactly one convention plugin per module:
  - `jtest.java-base-conventions` — plain Java library, no Spring BOM.
    Use this when Spring Boot's pins conflict (e.g. ServiceComb 2.8.x).
  - `jtest.java-conventions` — default; layers Spring Boot / Cloud /
    Statemachine / Log4j BOMs on top of the base.
  - `jtest.spring-boot-conventions` — Spring Boot apps; keeps the
    regular `jar` task enabled alongside `bootJar`.
  - `jtest.servicecomb-conventions` — ServiceComb (Spring 5 era) demos.
  - `jtest.war-conventions` — modules packaged as WAR.

## Dependency Conventions

- All versions live in `gradle/libs.versions.toml`. **Never** hard-code
  a `group:name:version` triple in a module unless the version is
  managed by a BOM (Spring Boot, Spring Cloud, Log4j, Netty, Dubbo,
  java-chassis, JUnit). Versionless coordinates that resolve through
  those BOMs are the preferred form for managed artifacts.
- New external dependencies: add a version under `[versions]`, a
  library alias under `[libraries]`, then reference it as `libs.xxx`
  from the module build script. If `scripts/generate_gradle.py` will
  emit the dependency, also extend that script's `CATALOG_MAP`.
- Lombok is wired globally in `jtest.java-base-conventions` (compile
  and test, plus annotation processors). Do not re-declare it as
  `implementation`.
- JUnit 5 is the default test platform via the `bom-junit` catalog
  alias. Use `org.junit.jupiter:junit-jupiter` without a version.

## Coding Style

- Follow `.editorconfig`: UTF-8, LF line endings, 4-space indent for
  Java/SQL/properties, 2-space for XML, 120-column soft limit, final
  newline, no trailing whitespace.
- Compiler flags enforce `-Xlint:all -parameters` and treat
  deprecation as visible warnings; keep new code warning-clean.
- Default to ASCII in source unless a file is already non-ASCII.
- Package root is `com.github.leleact.jtest.*`; mirror the module path
  when adding new packages.

## Testing Expectations

- Prefer JUnit 5 (`org.junit.jupiter.api.*`). AssertJ / Awaitility /
  Mockito are available through the relevant BOMs when needed.
- A test that requires external infrastructure should be annotated
  with `@Disabled("reason")` rather than removed; the build relies on
  empty discovery being non-fatal.
- When touching shared convention plugins or the catalog, run at
  minimum `./gradlew build` on a small representative module set
  (e.g. one `jdk/`, one `spring-boot`, one `dubbo`) before declaring
  the change complete. Run the full `./gradlew build` for any change
  that could affect cross-module resolution.

## Tooling Scripts

- `scripts/generate_gradle.py` — regenerates module `build.gradle.kts`
  files and the generated block in `settings.gradle.kts` from the
  cached POM snapshot at `scripts/.cache/poms.json`. Idempotent.
- `scripts/analyze_poms.py` / `scripts/survey.py` — diagnostic helpers
  over the same POM JSON; useful when extending the generator.
- The legacy `pom.xml` tree has already been removed; the JSON cache
  is the source of truth for regeneration.

## Git and Workflow

- Default branch: `main`. Create feature branches with the `codex/`
  prefix when working as an agent (unless the user requests otherwise).
- Do not run destructive git commands (`reset --hard`, `checkout --`,
  force pushes) without an explicit request.
- The worktree may be dirty; leave unrelated changes alone and work
  with them rather than reverting.
- Keep commits scoped: code change, generator change, or catalog
  change as separate commits when feasible, so regeneration noise is
  easy to review.

## Safe-Change Checklist

Before handing work back, confirm:

1. The relevant module(s) build with `./gradlew :path:build`.
2. No version strings were inlined that belong in the catalog.
3. Generated build files still match what `scripts/generate_gradle.py`
   would emit, or the divergence is intentional and commented.
4. New modules are wired into `settings.gradle.kts`.
5. Editor-config conventions are respected (encoding, indentation,
   final newline).
