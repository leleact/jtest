import java.time.Duration
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    `java-library`
}

group = "com.github.leleact.jtest"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
}

val libs = the<LibrariesForLibs>()

dependencies {
    // Lombok is enabled project-wide because most modules use @Slf4j / @Data.
    "compileOnly"(libs.lombok)
    "annotationProcessor"(libs.lombok)
    "testCompileOnly"(libs.lombok)
    "testAnnotationProcessor"(libs.lombok)

    // Default JUnit 5 platform. The BOM keeps jupiter and platform jars
    // aligned to a single Jupiter / Platform version regardless of any
    // other BOMs (Spring Boot, java-chassis, etc.) layered on top.
    "testImplementation"(platform(libs.bom.junit))
    "testImplementation"("org.junit.jupiter:junit-jupiter")
    "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(
        listOf(
            "-Xlint:all",
            "-Xlint:-processing",
            "-Xlint:-options",
            "-parameters",
        ),
    )
    options.isDeprecation = true
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    systemProperty("file.encoding", "UTF-8")
    testLogging {
        events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED)
        exceptionFormat = TestExceptionFormat.FULL
        showStandardStreams = false
    }
    systemProperty("java.net.preferIPv4Stack", "true")
    timeout.set(Duration.ofMinutes(10))

    // Many demo modules ship test sources whose only @Test methods are
    // intentionally @Disabled (they need an external broker/registry to
    // run). Gradle 9 turned "test sources present but nothing discovered"
    // into a hard failure; opt back out of that so those modules still
    // build cleanly on CI.
    failOnNoDiscoveredTests = false
}

tasks.named<Jar>("jar") {
    manifest {
        attributes(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
        )
    }
}
