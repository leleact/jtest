// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "dubbo-reference"

dependencies {
    api(platform(libs.bom.dubbo))
    "implementation"(libs.dubbo)
    "implementation"(platform(libs.dubbo.zookeeper.curator5))
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"(libs.dubbo.spring.boot.starter)
    "testImplementation"("org.springframework.boot:spring-boot-starter-test")
}

