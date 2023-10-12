import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("buildlogic.plugins.kmp.library.android")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
    kotlin("native.cocoapods")
}

android {
    namespace = "com.expressus"
}

kotlin {
    jvm()
    androidTarget()
    iosArm64()
    iosSimulatorArm64()

    applyDefaultHierarchyTemplate()

    cocoapods {
        summary = "Expressus, a multiplatform coffee machine!"
        homepage = "https://github.com/GuilhE/Expressus"
        ios.deploymentTarget = "16.0"
        version = "1.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "Shared"
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(libs.jetbrains.kotlinx.coroutines.core)
                implementation(libs.jetbrains.kotlinx.serialization)
                implementation(libs.jetbrains.kotlinx.atomicfu)
                api(libs.multiplatform.kermit)
                api(libs.multiplatform.mokoMvvm)
                api(libs.multiplatform.orbitMvi.core)
                api(libs.koin.core)
                implementation(libs.multiplatform.multiplatformSettings)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.koin.android)
            }
        }

        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val jvmMain by getting

        val all by creating {
            dependencies {
                api(libs.koin.core)
                implementation(libs.multiplatform.multiplatformSettings)
            }
//            commonMain.dependsOn(this)
            androidMain.dependsOn(this)
            iosMain.dependsOn(this)
            jvmMain.dependsOn(this)
        }

        targets.withType<KotlinNativeTarget> {
            compilations["main"].kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
        }
    }
}