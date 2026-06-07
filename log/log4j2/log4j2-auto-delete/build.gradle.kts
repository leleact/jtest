// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "log4j2-auto-delete"

dependencies {
    "implementation"("org.apache.logging.log4j:log4j-slf4j2-impl")
    "implementation"(libs.disruptor)
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"(libs.findbugs.annotations)
    "implementation"("junit:junit")
}

