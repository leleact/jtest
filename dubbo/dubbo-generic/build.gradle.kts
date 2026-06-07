// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.spring-boot-conventions")
}

description = "dubbo-generic"

dependencies {
    api(platform(libs.bom.dubbo))
    "implementation"(libs.dubbo)
    "implementation"(platform(libs.dubbo.zookeeper.curator5))
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.springframework.boot:spring-boot-starter")
    "implementation"(libs.dubbo.spring.boot.starter)
    "testImplementation"("org.springframework.boot:spring-boot-starter-test")
    "implementation"("org.springframework.boot:spring-boot-devtools")
    "implementation"("org.apache.curator:curator-framework")
    "implementation"("org.apache.curator:curator-recipes")
}

