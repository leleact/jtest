// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "apache-commons-bean-utils"

dependencies {
    "implementation"("org.junit.jupiter:junit-jupiter-api")
    "implementation"(libs.commons.beanutils) {
        exclude(group = "commons-logging", module = "commons-logging")
        exclude(group = "commons-collections", module = "commons-collections")
    }
    "implementation"("org.apache.logging.log4j:log4j-slf4j-impl")
    "implementation"("org.slf4j:jcl-over-slf4j")
    "implementation"(libs.lombok)
}

