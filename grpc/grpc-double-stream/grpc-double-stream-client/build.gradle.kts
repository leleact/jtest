// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
}

description = "grpc-double-stream-client"

dependencies {
    "implementation"("ch.qos.logback:logback-classic")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"(libs.grpc.netty.shaded)
    "implementation"(libs.grpc.protobuf)
    "implementation"(libs.protobuf.java)
    "implementation"(libs.grpc.stub)
    "implementation"(project(":grpc:grpc-double-stream:grpc-double-stream-api"))
}

