// Root project: no source. All shared config is in buildSrc convention
// plugins. The third-party plugins below are pre-declared at the root so
// any subproject can apply them via plain `id("...")` without restating
// the version. Spring Boot and Spring dependency-management live on the
// buildSrc classpath (via buildSrc/build.gradle.kts) because our
// convention plugins apply them programmatically.
plugins {
    alias(libs.plugins.protobuf) apply false
    alias(libs.plugins.shadow) apply false
}
