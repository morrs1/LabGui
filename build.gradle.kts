plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"

}
application {
    mainClass = "org.example.Main"
}

javafx {
    version = "21.0.2"
    modules("javafx.controls", "javafx.fxml", "javafx.media")
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}