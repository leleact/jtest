// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "xstream"

dependencies {
    "implementation"(libs.xstream)
    "implementation"("org.apache.logging.log4j:log4j-slf4j-impl")
    "implementation"(libs.lombok)
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
}

