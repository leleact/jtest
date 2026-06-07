// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
    id("com.gradleup.shadow")
}

description = "apache-commons-lang3"

dependencies {
    "implementation"("org.junit.jupiter:junit-jupiter-api")
    "implementation"("org.apache.logging.log4j:log4j-slf4j-impl")
    "implementation"("org.slf4j:jcl-over-slf4j")
    "implementation"(libs.lombok)
    "implementation"("org.apache.commons:commons-lang3")
}

