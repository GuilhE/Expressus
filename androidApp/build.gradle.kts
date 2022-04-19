plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = SDK.compile

    defaultConfig {
        applicationId = "com.expressus.android"
        minSdk = SDK.min
        targetSdk = SDK.target
        versionCode = 1
        versionName = "1.0"

        resValue("string", "app_name_label", "Expressus")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Android.Compose.compiler
    }

    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf(
            "-Xskip-prerelease-check",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlin.RequiresOptIn"
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))

    // Android
    implementation(Libs.Android.Core.material)
    implementation(Libs.Android.Lifecycle.lifecycleRuntimeKtx)

    // Android Compose
    implementation(Libs.Android.Compose.activity)
    implementation(Libs.Android.Compose.material)
    implementation(Libs.Android.Compose.ui)
    implementation(Libs.Android.Compose.runtime)
    implementation(Libs.Android.Compose.foundationLayout)
    debugImplementation(Libs.Android.Compose.uiTooling)
    
    // Di
    implementation(Libs.Koin.android)
}