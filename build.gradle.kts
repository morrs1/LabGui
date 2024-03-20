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
    maven("https://jitpack.io")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("de.vandermeer:asciitable:0.3.2")
    implementation("com.google.guava:guava:33.0.0-jre")
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
    implementation("com.ibm.icu:icu4j:73.2")
    implementation("org.jgrapht:jgrapht-core:1.5.1")
////    implementation("net.automatalib:automata-dot-visualizer:0.11.0")
//    implementation ("net.automatalib:automata-parent:0.11.0")
//    implementation("net.automatalib:automata-serialization-saf:0.11.0")
//    implementation("com.github.LearnLib:automatalib:develop-SNAPSHOT")


}

tasks.test {
    useJUnitPlatform()
}