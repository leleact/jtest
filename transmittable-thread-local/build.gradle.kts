plugins {
    id("jtest.java-conventions")
}

description = "transmittable-thread-local"

dependencies {
    implementation(libs.transmittable.thread.local)
    implementation("ch.qos.logback:logback-classic")
}
