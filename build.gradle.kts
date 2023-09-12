buildscript {
    repositories {
        google()
        mavenCentral()
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
        //https://discuss.kotlinlang.org/t/disabling-androidandroidtestrelease-source-set-in-gradle-kotlin-dsl-script
        project.extensions.findByType<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension>()?.let { kmpExt ->
            kmpExt.sourceSets.removeAll {
                setOf(
                    "androidAndroidTestRelease",
                    "androidTestFixtures",
                    "androidTestFixturesDebug",
                    "androidTestFixturesRelease",
                ).contains(it.name)
            }
        }
    }
}

tasks.register<Exec>("addFilesToXcodeproj") {
    workingDir(layout.projectDirectory)
    commandLine("bash", "-c", "./exportToXcode.sh")
}