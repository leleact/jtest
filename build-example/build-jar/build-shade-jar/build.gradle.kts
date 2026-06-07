// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
    id("com.gradleup.shadow")
}

description = "build-shade-jar"

dependencies {
    "implementation"("org.apache.logging.log4j:log4j-slf4j-impl")
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveClassifier.set("shaded")
    manifest { attributes["Main-Class"] = "com.github.leleact.jtest.build.shade.jar.Main" }
    mergeServiceFiles()
}

