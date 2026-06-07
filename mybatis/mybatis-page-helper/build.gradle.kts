// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "mybatis-page-helper"

dependencies {
    "testImplementation"("org.springframework:spring-test")
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"("ch.qos.logback:logback-classic")
    "testImplementation"("org.awaitility:awaitility")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"(libs.mybatis.spring.boot.starter)
    "implementation"("org.springframework.boot:spring-boot-starter-test")
    "implementation"("com.h2database:h2")
    "implementation"(libs.pagehelper.spring.boot.starter)
}

