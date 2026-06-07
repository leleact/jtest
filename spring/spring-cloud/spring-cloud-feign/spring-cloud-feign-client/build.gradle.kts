// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.spring-boot-conventions")
}

description = "spring-cloud-feign-client"

dependencies {
    "implementation"("jakarta.annotation:jakarta.annotation-api")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.springframework.cloud:spring-cloud-starter-openfeign")
    "implementation"("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    "implementation"("org.springframework.cloud:spring-cloud-loadbalancer")
    "implementation"("io.github.openfeign:feign-httpclient")
    "testImplementation"("org.springframework.boot:spring-boot-starter-test")
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"(project(":spring:spring-cloud:spring-cloud-feign:spring-cloud-feign-api"))
}

