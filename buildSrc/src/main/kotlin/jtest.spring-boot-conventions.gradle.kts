plugins {
    id("jtest.java-conventions")
    id("org.springframework.boot")
}

// Spring Boot plugin disables the regular Jar task by default; keep it
// enabled so projects can still consume the unpackaged jar in tests.
tasks.named<Jar>("jar") {
    enabled = true
}
