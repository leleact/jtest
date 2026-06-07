// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.servicecomb-conventions")
}

description = "servicecomb-client"

dependencies {
    "implementation"("org.apache.servicecomb:solution-basic")
    "implementation"("org.apache.servicecomb:registry-zero-config")
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "testImplementation"("org.awaitility:awaitility")
    "implementation"("org.apache.logging.log4j:log4j-slf4j-impl")
}

