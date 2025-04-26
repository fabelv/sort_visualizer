plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(libs.guava)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

application {
    mainClass.set("org.example.App")
}

javafx {
    version = "24.0.1"
    modules("javafx.controls", "javafx.fxml")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
