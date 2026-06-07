// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.spring-boot-conventions")
}

description = "spring-boot-mybatis"

dependencies {
    "implementation"("jakarta.annotation:jakarta.annotation-api")
    "implementation"("org.springframework.boot:spring-boot-starter")
    "testImplementation"("org.springframework.boot:spring-boot-starter-test")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"(libs.mybatis.spring.boot.starter)
    "implementation"(libs.mysql.connector.j)
    "implementation"("org.springframework.boot:spring-boot-starter-web")
}

