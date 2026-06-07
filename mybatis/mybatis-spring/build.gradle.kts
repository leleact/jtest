// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "mybatis-spring"

dependencies {
    "testImplementation"("org.springframework:spring-test")
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"("ch.qos.logback:logback-classic")
    "testImplementation"("org.awaitility:awaitility")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"(libs.mybatis.spring.boot.starter)
    "implementation"(libs.mybatis.spring)
    "implementation"(libs.mybatis)
    "implementation"(libs.druid)
    "implementation"(libs.mysql.connector.j)
    "implementation"("com.fasterxml.jackson.core:jackson-core")
    "implementation"("com.fasterxml.jackson.core:jackson-databind")
    "testImplementation"("com.h2database:h2")
}

