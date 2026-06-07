// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.spring-boot-conventions")
}

description = "spring-cloud-zookeeper-client"

dependencies {
    "implementation"("jakarta.annotation:jakarta.annotation-api")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.springframework.boot:spring-boot-starter-web")
    "implementation"("org.springframework.cloud:spring-cloud-starter-zookeeper-all") {
        exclude(group = "org.apache.zookeeper", module = "zookeeper")
    }
    "implementation"("org.apache.zookeeper:zookeeper:3.9.3") {
        exclude(group = "org.slf4j", module = "slf4j-log4j12")
    }
    "testImplementation"("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "junit", module = "junit")
    }
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
}

