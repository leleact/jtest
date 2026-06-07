// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.war-conventions")
}

description = "spring-web"

dependencies {
    "implementation"("jakarta.annotation:jakarta.annotation-api")
    "testImplementation"("org.springframework:spring-test")
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"("ch.qos.logback:logback-classic")
    "testImplementation"("org.mockito:mockito-core")
    "testImplementation"("org.awaitility:awaitility")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.apache.logging.log4j:log4j-to-slf4j")
    "implementation"("org.springframework:spring-web")
    "implementation"("org.springframework:spring-webmvc")
}

