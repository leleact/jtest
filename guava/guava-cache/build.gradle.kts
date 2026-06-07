// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "guava-cache"

dependencies {
    "implementation"(libs.guava)
    "implementation"(libs.findbugs.jsr305)
    "implementation"("org.apache.logging.log4j:log4j-slf4j2-impl")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
}

