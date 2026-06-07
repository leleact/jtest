// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.spring-boot-conventions")
}

description = "spring-cloud-zookeeper-server"

dependencies {
    "implementation"("jakarta.annotation:jakarta.annotation-api")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.springframework.boot:spring-boot-starter-web")
    "implementation"("org.springframework.cloud:spring-cloud-starter-zookeeper-discovery") {
        exclude(group = "org.apache.zookeeper", module = "zookeeper")
    }
    "implementation"("org.apache.zookeeper:zookeeper:3.4.6") {
        exclude(group = "org.slf4j", module = "slf4j-log4j12")
    }
}

