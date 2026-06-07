import org.gradle.accessors.dm.LibrariesForLibs

// The default Java convention plugin. Layers the shared Spring stack
// BOMs on top of `jtest.java-base-conventions`. Modules that conflict
// with Spring Boot's pinned versions (for example ServiceComb 2.8.x,
// which still expects Spring 5) should apply
// `jtest.java-base-conventions` instead and import their own BOM.
plugins {
    id("jtest.java-base-conventions")
    id("io.spring.dependency-management")
}

val libs = the<LibrariesForLibs>()

fun gav(provider: Provider<MinimalExternalModuleDependency>): String {
    val d = provider.get()
    return "${d.module.group}:${d.module.name}:${d.versionConstraint.requiredVersion}"
}

dependencyManagement {
    imports {
        mavenBom(gav(libs.bom.spring.boot))
        mavenBom(gav(libs.bom.spring.cloud))
        mavenBom(gav(libs.bom.spring.statemachine))
        mavenBom(gav(libs.bom.log4j))
    }
}
