// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.spring-boot-conventions")
}

description = "swagger"

dependencies {
    "implementation"("org.springframework.boot:spring-boot-starter-web")
    "implementation"("org.springframework.boot:spring-boot-starter-validation")
    "implementation"("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"(libs.legacy.servlet.api)
    "testImplementation"("org.awaitility:awaitility")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("ch.qos.logback:logback-classic")
    "implementation"(libs.javassist)
}

