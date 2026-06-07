// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "apache-http-client-examples"

dependencies {
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"("org.apache.logging.log4j:log4j-slf4j-impl")
    "implementation"("org.slf4j:jcl-over-slf4j")
    "implementation"(libs.apache.httpclient)
    "implementation"(libs.apache.httpmime)
    "implementation"(libs.lombok)
}

