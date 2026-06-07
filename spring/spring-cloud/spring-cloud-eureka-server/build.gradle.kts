// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.spring-boot-conventions")
}

description = "spring-cloud-eureka-server"

dependencies {
    "implementation"("jakarta.annotation:jakarta.annotation-api")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    "testImplementation"("org.springframework.boot:spring-boot-starter-test")
}

