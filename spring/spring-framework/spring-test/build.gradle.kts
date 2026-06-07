// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.spring-boot-conventions")
}

description = "spring-test"

dependencies {
    "implementation"("jakarta.annotation:jakarta.annotation-api")
    "testImplementation"("org.springframework:spring-test")
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "implementation"("ch.qos.logback:logback-classic")
    "testImplementation"("org.mockito:mockito-core")
    "testImplementation"("org.awaitility:awaitility")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.springframework.boot:spring-boot-starter")
    "implementation"("org.springframework.boot:spring-boot-starter-web")
    "testImplementation"("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "junit", module = "junit")
    }
    "implementation"(libs.mybatis.spring.boot.starter)
    "implementation"(libs.mysql.connector.j)
    "testImplementation"("com.h2database:h2")
}

