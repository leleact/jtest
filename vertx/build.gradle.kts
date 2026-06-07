// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "vertx"

dependencies {
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "testImplementation"("org.awaitility:awaitility")
    "implementation"("org.apache.logging.log4j:log4j-slf4j2-impl")
    "implementation"(libs.vertx.core)
    "implementation"(libs.vertx.web)
    "annotationProcessor"("io.vertx:vertx-codegen:${libs.versions.vertx.get()}:processor")
    "annotationProcessor"("io.vertx:vertx-service-proxy:${libs.versions.vertx.get()}")
}

