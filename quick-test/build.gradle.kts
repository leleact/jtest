// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "quick-test"

dependencies {
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("ch.qos.logback:logback-classic")
    "implementation"("org.springframework:spring-beans")
    "implementation"("org.springframework:spring-context")
}

