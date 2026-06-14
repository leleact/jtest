# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Test

```bash
./gradlew build                              # compile + test + assemble all ~150 modules
./gradlew test                               # run tests in every module
./gradlew :jdk:jdk-concurrency:test          # test a single module
./gradlew :jdk:jdk-concurrency:test --tests "*ThreadPoolTest*"  # single test class
./gradlew :spring:spring-boot:spring-boot-web:bootRun  # run a Spring Boot demo
./gradlew :<path>:build --info               # debug a single-module failure
```

- Java 21 (toolchain), Temurin JDK on CI, Gradle wrapper (`./gradlew` / `gradlew.bat`)
- Daemon configured for 4 GiB heap. Use `--no-daemon` and `-Dorg.gradle.workers.max=2` in constrained environments.
- `failOnNoDiscoveredTests = false` is intentional — many demo modules have `@Disabled` tests needing external brokers.
- **Always verify locally before pushing to CI** — run `./gradlew :<affected-module>:build` for all modules touched by a change. Only push once everything passes locally.

## Module Structure

~150 leaf modules grouped by topic. Aggregator directories contain no sources.

| Topic dir | Examples |
|-----------|----------|
| `jdk/` | concurrency, stream, lambda, reflection, string, collection, etc. |
| `spring/spring-framework/` | aop, ioc, mvc, jdbc, webflux, tx, retry, schedule |
| `spring/spring-boot/` | web, mybatis, aop, amqp, validation, test |
| `spring/spring-cloud/` | eureka, feign, ribbon, zookeeper |
| `dubbo/` | api, provider, consumer, generic, reference, starter |
| `grpc/` | double-stream api/client/server |
| `mybatis/` | generator, multiple-datasource, page-helper, spring |
| `netty/` | client, common, server |
| `log/` | log4j2 (async, perf, template), logback |
| `apache/` | commons-*, http-client variants |
| `algorithm/` | tree, leetcode problems |
| Other | `crypto/`, `mockito/`, `micrometer/`, `guava/`, `mapstruct/`, `serialize/`, `reactive/`, `validator/`, `shardingsphere/`, `servicecomb/` |

Each leaf module:
- Package root: `com.github.leleact.jtest.*`
- Applies exactly one convention plugin (see **Convention Plugins** below)
- Uses `libs.xxx` from the version catalog for dependencies

## Convention Plugins (buildSrc)

Apply **exactly one** per module:

| Plugin | When to use |
|--------|-------------|
| `jtest.java-base-conventions` | Plain Java library, no Spring BOM. For modules where Spring Boot's pins conflict (e.g. ServiceComb 2.8.x). |
| `jtest.java-conventions` | **Default.** Layers Spring Boot / Cloud / Statemachine / Log4j BOMs on base. |
| `jtest.spring-boot-conventions` | Spring Boot apps; keeps regular `jar` task enabled alongside `bootJar`. |
| `jtest.servicecomb-conventions` | ServiceComb (Spring 5 / javax era) demos. |
| `jtest.war-conventions` | Modules packaged as WAR. |

Lombok is wired globally (compile + test + annotation processors) — do not re-declare it.

## Version Catalog

All versions in `gradle/libs.versions.toml`. Never hard-code `group:name:version` triples. Use:
- `libs.xxx` for versioned dependencies
- Versionless BOM-managed coordinates (Spring Boot, Spring Cloud, Log4j, Netty, Dubbo, java-chassis, JUnit) resolve through imported BOMs

### Local Verification Rule

All code changes must be locally verified before pushing to CI:

1. Identify all modules affected by the change (use `grep -rl <libs.xxx> */build.gradle.kts */**/build.gradle.kts` for dependency changes)
2. Run `./gradlew :<each-affected-module>:build` locally and fix any errors
3. Only push once everything passes — CI is for regression checking, not first-pass validation

## Generated Build Files

Module `build.gradle.kts` files with the header `// Auto-generated from pom.xml by scripts/generate_gradle.py` are machine-generated from `scripts/.cache/poms.json`. Prefer changing the generator or catalog over hand-editing. If manual tweak is unavoidable, remove the auto-generated header and document the divergence.

New modules must be registered in `settings.gradle.kts` between the `BEGIN GENERATED SUBPROJECTS` / `END GENERATED SUBPROJECTS` markers.

## Coding Style

- `.editorconfig`: UTF-8, LF line endings, 4-space indent for Java/SQL/properties, 2-space for XML, 120-column soft limit
- Compiler: `-Xlint:all -parameters`, deprecation warnings visible
- JUnit 5 (`org.junit.jupiter.api.*`) is the test platform
- Tests requiring external infrastructure → `@Disabled("reason")`, not removed

## CI

GitHub Actions (`.github/workflows/test.yml`): `./gradlew --no-daemon build` on Ubuntu + Temurin JDK 21, Gradle user home cached.
