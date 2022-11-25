plugins {
    id("java")
    id ("com.github.johnrengelman.shadow") version "7.1.0"
    `maven-publish`
}

group = "de.simonsator.partyandfriends"
version = "1.0.4-RELEASE"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://jitpack.io")
}

dependencies {
    compileOnly(libs.minestom)
    implementation(libs.citystom)
    implementation(libs.jedis)
    compileOnly(libs.lombok)
    compileOnly(libs.pafmysql)
    annotationProcessor(libs.lombok)
}

publishing {
    publications.create<MavenPublication>("maven") {
        afterEvaluate {
            val shadowJar = tasks.findByName("shadowJar")
            if (shadowJar == null) from(components["java"])
            else artifact(shadowJar)
        }
    }
}

project.tasks.findByName("jar")?.enabled = false

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    mergeServiceFiles()
    archiveClassifier.set("")
}
