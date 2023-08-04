plugins {
    id("buildlogic.plugins.kmp.library.android")
    alias(libs.plugins.jetbrains.compose.desktop)
}

kotlin {
    jvm()
    androidTarget()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(compose.preview)
            }
        }
    }
}

compose {
    kotlinCompilerPlugin.set(libs.versions.composeMultiplatformCompiler)
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.0")
}

android {
    namespace = "com.expressus.compose"
    sourceSets["main"].res.srcDirs("src/commonMain/resources")
}