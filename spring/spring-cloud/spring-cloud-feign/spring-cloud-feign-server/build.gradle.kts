// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.spring-boot-conventions")
}

description = "spring-cloud-feign-server"

dependencies {
    "implementation"("jakarta.annotation:jakarta.annotation-api")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.springframework.cloud:spring-cloud-starter-openfeign")
    "implementation"("org.springframework.boot:spring-boot-starter-web")
    "implementation"("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    "implementation"(project(":spring:spring-cloud:spring-cloud-feign:spring-cloud-feign-api"))
    "implementation"("io.springfox:springfox-swagger2:2.9.2")
    "implementation"("io.springfox:springfox-swagger-ui:2.9.2")
}

