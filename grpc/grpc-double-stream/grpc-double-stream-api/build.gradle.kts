// Auto-generated from pom.xml by scripts/generate_gradle.py
// Do not edit by hand.

plugins {
    id("jtest.java-conventions")
    id("com.google.protobuf")
}

description = "grpc-double-stream-api"

dependencies {
    "implementation"("ch.qos.logback:logback-classic")
    "compileOnly"(libs.lombok)
    "testImplementation"(libs.lombok)
    "implementation"(libs.grpc.netty.shaded)
    "implementation"(libs.grpc.protobuf)
    "implementation"(libs.protobuf.java)
    "implementation"(libs.grpc.stub)
}

// gRPC + Protobuf code generation.
val catalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
val protobufVersion = catalog.findVersion("protobuf").orElseThrow().requiredVersion
val grpcVersion = catalog.findVersion("grpc").orElseThrow().requiredVersion
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
    }
    generateProtoTasks {
        all().configureEach {
            plugins {
                create("grpc")
            }
        }
    }
}

