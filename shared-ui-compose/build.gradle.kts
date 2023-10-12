plugins {
    id("buildlogic.plugins.kmp.library.android")
    id("org.jetbrains.compose")
    kotlin("native.cocoapods")
    alias(libs.plugins.ksp)
}

compose {
    kotlinCompilerPlugin.set(libs.versions.composeMultiplatformCompiler)
}

android {
    namespace = "com.expressus.compose"
    sourceSets["main"].res.srcDirs("src/commonMain/resources")
}

kotlin {
    jvm()
    androidTarget()
    val iosX64 = iosX64()
    val iosArm64 = iosArm64()
    val iosSimulatorArm64 = iosSimulatorArm64()

    applyDefaultHierarchyTemplate()

    cocoapods {
        summary = "Expressus, a multiplatform coffee machine!"
        homepage = "https://github.com/GuilhE/Expressus"
        ios.deploymentTarget = "16.0"
        version = "1.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "SharedComposables"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(libs.jetbrains.kotlinx.coroutines.core)
            }
        }
        val jvmMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(compose.preview)
            }
        }
        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.multiplatform.composeuiviewcontroller.annotations)
            }
        }
        listOf(iosX64, iosArm64, iosSimulatorArm64).forEach { target ->
            getByName("${target.targetName}Main") {
                dependsOn(iosMain)
            }

            val targetName = target.name.replaceFirstChar { it.uppercaseChar() }
            dependencies.add("ksp$targetName", libs.multiplatform.composeuiviewcontroller.ksp)
        }
    }
}

tasks.matching { it.name == "syncFramework" }.configureEach { finalizedBy(":addFilesToXcodeproj") }