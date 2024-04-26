import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(libs.gradle.android.tools)
        classpath(libs.gradle.kotlin)
        classpath(libs.gradle.kotlin.serialization)
        classpath(libs.gradle.compose.multiplatform)
    }
}

allprojects {
    afterEvaluate {
        project.extensions.findByType<KotlinMultiplatformExtension>()?.let { ext ->
            ext.sourceSets {
                //https://discuss.kotlinlang.org/t/disabling-androidandroidtestrelease-source-set-in-gradle-kotlin-dsl-script
                sequenceOf("AndroidTest", "TestFixtures").forEach { artifact ->
                    sequenceOf("", "Release", "Debug").forEach { variant ->
                        findByName("android$artifact$variant")?.let(::remove)
                    }
                }
            }
        }
    }
}

tasks.register<Exec>("addFilesToXcodeproj") {
    workingDir(layout.projectDirectory)
    commandLine("bash", "-c", "./exportToXcode.sh")
}