// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "mybatis-generator"

dependencies {
    "testImplementation"("org.springframework:spring-test")
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"("ch.qos.logback:logback-classic")
    "testImplementation"("org.awaitility:awaitility")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"(libs.mybatis.spring.boot.starter)
    "implementation"("org.springframework.boot:spring-boot-starter")
    "testImplementation"("org.springframework.boot:spring-boot-starter-test")
    "implementation"(libs.mysql.connector.j)
}

