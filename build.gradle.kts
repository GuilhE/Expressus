buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libs.Gradle.androidTools)
        classpath(Libs.Gradle.kotlin)
        classpath(Libs.Gradle.kotlinSerialization)
    }
}

plugins {
    id(Libs.Gradle.dependencyUpdate).version(Versions.Gradle.dependencyUpdate)
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}