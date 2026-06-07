// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.spring-boot-conventions")
}

description = "spring-profile"

dependencies {
    "implementation"("jakarta.annotation:jakarta.annotation-api")
    "testImplementation"("org.springframework:spring-test")
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"("ch.qos.logback:logback-classic")
    "testImplementation"("org.mockito:mockito-core")
    "testImplementation"("org.awaitility:awaitility")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.springframework.boot:spring-boot-starter") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
    "testImplementation"("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "junit", module = "junit")
    }
    "implementation"("org.springframework.boot:spring-boot-starter-log4j2")
}

