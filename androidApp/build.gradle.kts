plugins {
    id("buildlogic.plugins.application")
}

android {
    namespace = "com.expressus.android"
    defaultConfig {
        applicationId = "com.expressus.android"
        versionCode = 1
        versionName = "1.0"

        resValue("string", "app_name_label", "Expressus")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(projects.shared)
    implementation(projects.sharedUiCompose)

    implementation(libs.android.material)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.compose.activity)
    implementation(libs.bundles.jetbrains.compose)
    implementation(libs.jetbrains.compose.ui.tooling)

    implementation(libs.kmp.koin.android)
}