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

        commonMain.dependencies {
            implementation(libs.jetbrains.kotlinx.coroutines.core)
            implementation(libs.jetbrains.kotlinx.serialization)
            implementation(libs.jetbrains.kotlinx.atomicfu)
            api(libs.multiplatform.kermit)
            api(libs.multiplatform.mokoMvvm)
            api(libs.multiplatform.orbitMvi.core)
            api(libs.koin.core)
            implementation(libs.multiplatform.multiplatformSettings)
        }
        androidMain.dependencies {
            implementation(libs.koin.android)
        }

        val all by creating {
            dependencies {
                api(libs.koin.core)
                implementation(libs.multiplatform.multiplatformSettings)
            }
            androidMain.configure { dependsOn(this) }
            iosMain.configure { dependsOn(this) }
            jvmMain.configure { dependsOn(this) }
        }

        targets.withType<KotlinNativeTarget> {
            compilations["main"].kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
        }
    }
}