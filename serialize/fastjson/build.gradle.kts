// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "fastjson"

dependencies {
    "implementation"("org.springframework.boot:spring-boot-starter-log4j2")
    "testImplementation"("junit:junit")
    "implementation"(libs.fastjson)
    "implementation"(libs.lombok)
}

