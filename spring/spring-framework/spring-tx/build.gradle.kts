// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "spring-tx"

dependencies {
    "implementation"("jakarta.annotation:jakarta.annotation-api")
    "testImplementation"("org.springframework:spring-test")
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"("ch.qos.logback:logback-classic")
    "testImplementation"("org.mockito:mockito-core")
    "testImplementation"("org.awaitility:awaitility")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"(libs.mybatis.spring)
    "implementation"(libs.mybatis)
    "implementation"("com.h2database:h2")
    "implementation"(libs.mysql.connector.j)
    "implementation"("org.springframework:spring-jdbc")
    "implementation"("org.springframework:spring-tx")
    "implementation"("org.springframework:spring-context")
    "implementation"("org.springframework:spring-aspects")
}

