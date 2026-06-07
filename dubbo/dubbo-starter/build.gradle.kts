// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.spring-boot-conventions")
}

description = "dubbo-starter"

dependencies {
    api(platform(libs.bom.dubbo))
    "implementation"(libs.dubbo)
    "implementation"(platform(libs.dubbo.zookeeper.curator5))
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"(libs.dubbo.spring.boot.starter)
    "testImplementation"("org.springframework.boot:spring-boot-starter-test")
    "implementation"("org.springframework.boot:spring-boot-devtools")
    "implementation"("org.apache.logging.log4j:log4j-slf4j-impl")
    "implementation"("org.apache.logging.log4j:log4j-to-slf4j")
}

