plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("kotlinx-serialization")
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Expressus, a multiplatform coffee machine!"
        homepage = "https://github.com/GuilhE/Expressus"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = false // SwiftUI preview requires dynamic framework
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Libs.Koin.android)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val commonMain by getting {
            dependencies {
                implementation(Libs.JetBrains.atomicFu)
                implementation(Libs.JetBrains.serializationJson)
                implementation(Libs.JetBrains.kotlinxCoroutinesCore)
                with(Libs.Multiplatform) {
                    implementation(multiplatformSettings)
                    api(kermit)
                    api(mokoMvvm)
                    api(orbitMviCore)
                }
            }
        }

        val shared by creating {
            dependencies {
                api(Libs.Koin.core)
            }
            androidMain.dependsOn(this)
            iosMain.dependsOn(this)
            commonMain.dependsOn(this)
        }

        targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
            compilations["main"].kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
        }
    }
}

android {
    compileSdk = SDK.compile
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = SDK.min
        targetSdk = SDK.target
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}