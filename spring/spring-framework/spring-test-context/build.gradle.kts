// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "spring-test-context"

dependencies {
    "implementation"("jakarta.annotation:jakarta.annotation-api")
    "testImplementation"("org.springframework:spring-test")
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"("ch.qos.logback:logback-classic")
    "testImplementation"("org.mockito:mockito-core")
    "testImplementation"("org.awaitility:awaitility")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.springframework:spring-beans")
    "implementation"("org.springframework:spring-context")
    "implementation"("org.springframework:spring-jdbc")
    "implementation"("org.springframework:spring-tx")
    "implementation"(libs.mybatis.spring)
    "implementation"("com.zaxxer:HikariCP")
    "implementation"(libs.mybatis)
    "testImplementation"("com.h2database:h2")
}

