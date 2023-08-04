package extensions

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.artifacts.VersionCatalog

internal fun LibraryExtension.addKotlinAndroidConfigurations(libs: VersionCatalog) {
    apply {
        compileSdk = libs.findVersion("androidCompileSdk").get().toString().toInt()
        defaultConfig {
            minSdk = libs.findVersion("androidMinSdk").get().toString().toInt()
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        lint {
            disable.add("Instantiatable")
            abortOnError = false
        }
    }
}