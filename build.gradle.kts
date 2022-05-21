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


    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        with(kotlinOptions) {
            jvmTarget = "11"
            freeCompilerArgs = listOf(
                "-Xskip-prerelease-check",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlin.RequiresOptIn"
            )
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}