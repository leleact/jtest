// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "jdk-keystore"

dependencies {
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "testImplementation"("org.awaitility:awaitility")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("ch.qos.logback:logback-classic")
}

