plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose") version Versions.JetBrains.Compose.desktop
    id("maven-publish")
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
    sourceSets["main"].let {
        it.manifest.srcFile("src/androidMain/AndroidManifest.xml")
        it.res.srcDirs("src/commonMain/resources")
    }
    defaultConfig {
        minSdk = SDK.min
        targetSdk = SDK.target
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}