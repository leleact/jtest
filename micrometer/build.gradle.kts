// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "micrometer"

dependencies {
    "implementation"("io.micrometer:micrometer-core")
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "testImplementation"("org.awaitility:awaitility")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.apache.logging.log4j:log4j-slf4j2-impl")
    "implementation"(libs.disruptor)
}

