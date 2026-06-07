// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "mapstruct"

dependencies {
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"("ch.qos.logback:logback-classic")
    "testImplementation"("org.awaitility:awaitility")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"(libs.mapstruct)
    "annotationProcessor"("org.mapstruct:mapstruct-processor:${libs.versions.mapstruct.get()}")
    "testAnnotationProcessor"("org.mapstruct:mapstruct-processor:${libs.versions.mapstruct.get()}")
}

