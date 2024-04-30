plugins {
    id("buildlogic.plugins.kmp.library.android")
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.google.ksp)
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
    iosX64()

    cocoapods {
        summary = "Expressus, a multiplatform coffee machine!"
        homepage = "https://github.com/GuilhE/Expressus"
        ios.deploymentTarget = "17.0"
        version = "1.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "Shared"
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
                optIn("kotlin.experimental.ExperimentalObjCName")
                optIn("kotlin.RequiresOptIn")
            }
        }

        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.atomicfu)
            implementation(libs.kmp.settings)
            api(libs.kmp.kermit)
            api(libs.kmp.mokoMvvm)
            api(libs.kmp.orbitMvi.core)
            api(libs.kmp.koin.core)
        }
        androidMain.dependencies { implementation(libs.kmp.koin.android) }
        iosMain {
            @Suppress("OPT_IN_USAGE")
            compilerOptions {
                freeCompilerArgs.add("-Xexport-kdoc")
            }
        }
    }
}