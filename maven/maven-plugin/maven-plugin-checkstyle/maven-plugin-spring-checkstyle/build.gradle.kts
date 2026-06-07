// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "maven-plugin-spring-checkstyle"

dependencies {
    "implementation"("org.springframework.boot:spring-boot-starter")
    "testImplementation"("org.springframework.boot:spring-boot-starter-test")
    "compileOnly"("org.apache.maven.plugins:maven-checkstyle-plugin:3.1.1")
    "testImplementation"("org.apache.maven.plugins:maven-checkstyle-plugin:3.1.1")
    "compileOnly"("com.puppycrawl.tools:checkstyle:8.45.1")
    "testImplementation"("com.puppycrawl.tools:checkstyle:8.45.1")
    "compileOnly"("io.spring.javaformat:spring-javaformat-checkstyle:0.0.31")
    "testImplementation"("io.spring.javaformat:spring-javaformat-checkstyle:0.0.31")
    "compileOnly"("io.spring.javaformat:spring-javaformat-maven-plugin:0.0.31")
    "testImplementation"("io.spring.javaformat:spring-javaformat-maven-plugin:0.0.31")
}

