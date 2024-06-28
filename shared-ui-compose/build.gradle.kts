plugins {
    id("buildlogic.plugins.kmp.library.android")
    id("buildlogic.plugins.kmp.compose")
    kotlin("native.cocoapods")
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.composeuiviewcontroller)
}

ComposeUiViewController {
    iosAppName="Expressus"
    targetName="Expressus"
}

android {
    namespace = "com.expressus.compose"
}

kotlin {
    jvm("desktop")
    listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach { target ->
        target.name.replaceFirstChar { it.uppercaseChar() }
    }

    cocoapods {
        summary = "Expressus, a multiplatform coffee machine!"
        homepage = "https://github.com/GuilhE/Expressus"
        ios.deploymentTarget = "17.0"
        version = "1.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "ExpressusComposables"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.kotlinx.coroutines.core)
        }
        val desktopMain by getting {
            dependencies { implementation(compose.preview) }
        }
        iosMain {
            dependencies {
                implementation(projects.shared)
            }
            @Suppress("OPT_IN_USAGE")
            compilerOptions {
                freeCompilerArgs.add("-Xbinary=bundleId=com.expressus.compose")
            }
        }
    }
}