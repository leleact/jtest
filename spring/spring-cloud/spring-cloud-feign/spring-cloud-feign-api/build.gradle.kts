// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "spring-cloud-feign-api"

dependencies {
    "implementation"("jakarta.annotation:jakarta.annotation-api")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.springframework.cloud:spring-cloud-starter-openfeign")
    "implementation"("io.springfox:springfox-swagger2:2.9.2")
}

