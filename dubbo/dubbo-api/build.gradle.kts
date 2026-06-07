// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "dubbo-api"

dependencies {
    api(platform(libs.bom.dubbo))
    "implementation"(libs.dubbo)
    "implementation"(platform(libs.dubbo.zookeeper.curator5))
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
}

