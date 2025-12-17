plugins {
    id("buildlogic.plugins.kmp.library")
    kotlin("native.cocoapods")
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    jvm()

    android { namespace = "com.expressus" }

    listOf(iosArm64(), iosSimulatorArm64()).forEach { target ->
        target.compilerOptions { freeCompilerArgs.add("-Xexport-kdoc") }
    }

    cocoapods {
        summary = "Expressus, a multiplatform coffee machine!"
        homepage = "https://github.com/GuilhE/Expressus"
        ios.deploymentTarget = "26.0"
        version = "1.0"
        podfile = project.file("../iosApp/Podfile")
        framework { baseName = "ExpressusShared" }
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
        }
        androidMain.dependencies { implementation(libs.kmp.koin.android) }
    }
}