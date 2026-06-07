// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "maven-plugin-google-checkstyle"

dependencies {
    "implementation"("org.springframework.boot:spring-boot-starter")
    "testImplementation"("org.springframework.boot:spring-boot-starter-test")
    "compileOnly"("com.puppycrawl.tools:checkstyle:10.12.0")
    "testImplementation"("com.puppycrawl.tools:checkstyle:10.12.0")
}

