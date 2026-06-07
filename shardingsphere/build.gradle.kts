// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "shardingsphere"

dependencies {
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"("org.apache.logging.log4j:log4j-slf4j2-impl")
    "implementation"(libs.disruptor)
    "implementation"(libs.shardingsphere.jdbc)
    "implementation"(libs.druid)
    "implementation"(libs.mysql.connector.j)
    "implementation"("org.apache.commons:commons-lang3")
    "implementation"(libs.commons.io)
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine")
    "testImplementation"("org.awaitility:awaitility")
    "implementation"("com.h2database:h2")
    "testImplementation"(libs.mariadb4j)
    "testImplementation"(libs.mariadb.client)
    "implementation"(libs.jaxb.api)
    "implementation"(libs.jaxb.impl)
    "implementation"(libs.javax.activation.api)
}

