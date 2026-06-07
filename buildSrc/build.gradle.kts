plugins {
    `kotlin-dsl`
}

// Bring third-party plugins onto the buildSrc classpath so the convention
// plugins (precompiled script plugins under buildSrc/src/main/kotlin) can
// apply them by id. The same plugins are NOT pre-declared at the root
// project level, so subprojects can also apply them with id("...") without
// having to re-state versions.
fun pluginDep(provider: Provider<PluginDependency>): String {
    val p = provider.get()
    return "${p.pluginId}:${p.pluginId}.gradle.plugin:${p.version.requiredVersion}"
}

dependencies {
    implementation(pluginDep(libs.plugins.spring.boot))
    implementation(pluginDep(libs.plugins.spring.dep.mgmt))

    // Expose the typed `libs` accessor (LibrariesForLibs) inside
    // precompiled script plugins. Without this, `the<LibrariesForLibs>()`
    // cannot be resolved at convention-plugin compile time.
    implementation(files(libs::class.java.protectionDomain.codeSource.location))
}

kotlin {
    jvmToolchain(21)
}
