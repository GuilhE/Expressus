plugins {
    id("buildlogic.plugins.kmp.library.android")
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlinx.atomicfu)
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
    listOf(iosArm64(), iosSimulatorArm64(), iosX64()).forEach { _ ->
        compilerOptions {
            freeCompilerArgs.add("-Xexport-kdoc")
        }
    }

    cocoapods {
        summary = "Expressus, a multiplatform coffee machine!"
        homepage = "https://github.com/GuilhE/Expressus"
        ios.deploymentTarget = "17.0"
        version = "1.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "ExpressusShared"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization)
            implementation(libs.kmp.settings)
            api(libs.androidx.lifecycle.viewmodel)
            api(libs.kmp.kermit)
            api(libs.kmp.orbitMvi.core)
            api(libs.kmp.koin.core)
            //https://github.com/Kotlin/kotlinx-atomicfu/issues/469#issuecomment-2326868412
            implementation("org.jetbrains.kotlinx:atomicfu:0.27.0")
        }
        androidMain.dependencies { implementation(libs.kmp.koin.android) }
    }
}