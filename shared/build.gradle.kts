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
            implementation(libs.jetbrains.kotlinx.coroutines.core)
            implementation(libs.jetbrains.kotlinx.serialization)
            implementation(libs.jetbrains.kotlinx.atomicfu)
            implementation(libs.multiplatform.multiplatformSettings)
            api(libs.multiplatform.kermit)
            api(libs.multiplatform.mokoMvvm)
            api(libs.multiplatform.orbitMvi.core)
            api(libs.koin.core)
        }
        androidMain.dependencies { implementation(libs.koin.android) }
        iosMain {
            @Suppress("OPT_IN_USAGE")
            compilerOptions {
                freeCompilerArgs.add("-Xexport-kdoc")
            }
        }
    }
}