// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "netty-server"

dependencies {
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "testImplementation"("org.awaitility:awaitility")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.apache.logging.log4j:log4j-slf4j2-impl")
    "implementation"("io.netty:netty-all")
    "implementation"(project(":netty:netty-common"))
}

