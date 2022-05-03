import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose") version Versions.JetBrains.Compose.desktop
}

kotlin {
    jvm()
    android()

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

android {
    compileSdk = SDK.compile
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/commonMain/resources")
    defaultConfig {
        minSdk = SDK.min
        targetSdk = SDK.target
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<KotlinCompile> {
    with(kotlinOptions) {
        jvmTarget = "11"
        freeCompilerArgs = listOf(
            "-Xskip-prerelease-check",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlin.RequiresOptIn"
        )
    }
}