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
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(libs.jetbrains.kotlinx.coroutines.core)
        }
        jvmMain.dependencies {
            implementation(compose.preview)
        }
        iosMain.dependencies {
            implementation(libs.multiplatform.composeuiviewcontroller.annotations)
        }
        listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach { target ->
            val targetName = target.name.replaceFirstChar { it.uppercaseChar() }
            dependencies.add("ksp$targetName", libs.multiplatform.composeuiviewcontroller.ksp)

//            all {
//                https://kotlinlang.org/docs/ksp-quickstart.html#make-ide-aware-of-generated-code
//                kotlin.srcDir("build/generated/ksp/${target.targetName}/${target.targetName}Main/kotlin")
//            }
        }
    }
}

tasks.matching { it.name == "syncFramework" }.configureEach { finalizedBy(":addFilesToXcodeproj") }