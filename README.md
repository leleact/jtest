# JTest

JTest

[![GitHub Workflow Status (branch)](https://img.shields.io/github/actions/workflow/status/leleact/jtest/test.yml?style=for-the-badge)](https://github.com/leleact/jtest)

## Build

The project is built with the bundled Gradle Wrapper (Java 21). Always
use `./gradlew` (or `gradlew.bat` on Windows) so the exact Gradle
version pinned in `gradle/wrapper/gradle-wrapper.properties` is used.
All dependency versions live in a single version catalog at `gradle/libs.versions.toml`
and shared compile / test settings live in `buildSrc/` as convention
plugins (`jtest.java-conventions`, `jtest.spring-boot-conventions`,
`jtest.servicecomb-conventions`, `jtest.war-conventions`). Individual
module build files are generated from the historical POM tree by
`scripts/generate_gradle.py`, which reads `scripts/.cache/poms.json`.

```
./gradlew build       # compile + test + assemble everything
./gradlew test        # run every module's tests
```
