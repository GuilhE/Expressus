plugins {
    id("buildlogic.plugins.kmp.library.android")
    id("buildlogic.plugins.kmp.compose")
    kotlin("native.cocoapods")
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.expressus.compose"
}

kotlin {
    jvm("desktop")
    listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach { target ->
        val targetName = target.name.replaceFirstChar { it.uppercaseChar() }
        dependencies.add("ksp$targetName", libs.kmp.composeuiviewcontroller.ksp)
    }

    cocoapods {
        summary = "Expressus, a multiplatform coffee machine!"
        homepage = "https://github.com/GuilhE/Expressus"
        ios.deploymentTarget = "17.0"
        version = "1.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "SharedComposables"
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
                implementation(libs.kmp.composeuiviewcontroller.annotations)
            }
            @Suppress("OPT_IN_USAGE")
            compilerOptions {
                freeCompilerArgs.add("-Xbinary=bundleId=com.expressus.compose")
            }
        }
    }
}

tasks.matching { it.name == "syncFramework" }.configureEach { finalizedBy(":addFilesToXcodeproj") }