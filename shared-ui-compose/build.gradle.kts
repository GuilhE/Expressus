import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("buildlogic.plugins.kmp.library")
    id("buildlogic.plugins.kmp.compose")
    kotlin("native.cocoapods")
    alias(libs.plugins.composeuiviewcontroller)
}

ComposeUiViewController {
    iosAppName = "Expressus"
    targetName = "Expressus"
}

kotlin {
    jvm()

    android { namespace = "com.expressus.compose" }
    androidLibrary { androidResources.enable = true }

    listOf(iosArm64(), iosSimulatorArm64()).forEach { target ->
        target.compilerOptions {
            freeCompilerArgs.add("-Xbinary=bundleId=com.expressus.compose")
        }
    }

    cocoapods {
        summary = "Expressus, a multiplatform coffee machine!"
        homepage = "https://github.com/GuilhE/Expressus"
        ios.deploymentTarget = "26.0"
        version = "1.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "ExpressusComposables"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.jetbrains.compose)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.collections)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.jetbrains.compose.ui.tooling)
        }
    }
}

compose {
    desktop {
        application {
            mainClass = "com.expressus.compose.ExpressusKt"
        }
    }
}