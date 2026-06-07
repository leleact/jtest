import org.gradle.accessors.dm.LibrariesForLibs

// Convention plugin for Apache ServiceComb 2.x modules. ServiceComb 2.8.x
// expects Spring 5 / javax.* APIs, so we deliberately skip the shared
// Spring Boot 3 BOM that `jtest.java-conventions` applies and instead
// import the `java-chassis-dependencies` BOM via Spring's dependency
// management plugin.
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
        mavenBom(gav(libs.bom.java.chassis))
        // log4j BOM is safe across both Spring 5 and Spring 6.
        mavenBom(gav(libs.bom.log4j))
    }
}
