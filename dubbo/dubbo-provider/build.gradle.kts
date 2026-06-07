// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.spring-boot-conventions")
}

description = "dubbo-provider"

dependencies {
    api(platform(libs.bom.dubbo))
    "implementation"(libs.dubbo)
    "implementation"(platform(libs.dubbo.zookeeper.curator5))
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "testImplementation"("org.springframework.boot:spring-boot-starter-test")
    "implementation"("org.springframework.boot:spring-boot-devtools")
    "implementation"(libs.dubbo.spring.boot.starter)
    "implementation"("org.springframework.boot:spring-boot-starter")
    "implementation"("org.springframework.boot:spring-boot-autoconfigure")
    "implementation"(project(":dubbo:dubbo-api"))
    "implementation"("com.fasterxml.jackson.core:jackson-annotations")
    "implementation"("com.fasterxml.jackson.core:jackson-core")
}

