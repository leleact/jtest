// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "apache-commons-pool"

dependencies {
    "implementation"("org.apache.commons:commons-pool2")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("ch.qos.logback:logback-classic")
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
}

