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
    implementation(project(":shared-ui-compose"))

    // Android
    implementation(Libs.Android.Core.material)
    implementation(Libs.Android.Lifecycle.lifecycleRuntimeKtx)

    // Android Compose
    with(Libs.Android.Compose){
        implementation(activity)
        implementation(material)
        implementation(ui)
        implementation(runtime)
        implementation(foundationLayout)
        implementation(uiToolingPreview)
        implementation(uiTooling)
    }

    // Di
    implementation(Libs.Koin.android)
}