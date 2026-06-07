// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "jackson"

dependencies {
    "implementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"("com.fasterxml.jackson.core:jackson-databind")
    "implementation"("ch.qos.logback:logback-classic")
    "implementation"(libs.lombok)
}

