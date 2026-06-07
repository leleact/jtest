// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "datadic-validator"

dependencies {
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "testImplementation"("org.junit.jupiter:junit-jupiter-api")
    "implementation"("jakarta.validation:jakarta.validation-api")
    "implementation"("org.hibernate.validator:hibernate-validator")
    "implementation"(libs.jakarta.el.api)
    "implementation"(libs.glassfish.expressly)
    "implementation"("ch.qos.logback:logback-classic")
}

