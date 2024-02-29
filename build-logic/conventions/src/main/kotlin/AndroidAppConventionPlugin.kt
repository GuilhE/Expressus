@file:Suppress("UnstableApiUsage", "unused")

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class AndroidAppConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            val versionCatalog = target.extensions.getByType<VersionCatalogsExtension>().named("libs")
            extensions.configure<BaseAppModuleExtension> {
                addKotlinAndroidConfigurations(versionCatalog)
                addComposeOptions(versionCatalog)
                addKotlinJvmOptions(buildComposeMetricsParameters())
            }
            addComposeDependencies(versionCatalog)
        }
    }

    private fun BaseAppModuleExtension.addKotlinAndroidConfigurations(libs: VersionCatalog) {
        apply {
            compileSdk = libs.findVersion("androidCompileSdk").get().toString().toInt()
            defaultConfig {
                targetSdk = libs.findVersion("androidTargetSdk").get().toString().toInt()
                minSdk = libs.findVersion("androidMinSdk").get().toString().toInt()

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                testInstrumentationRunnerArguments.putAll(
                    mapOf(
                        "disableAnalytics" to "true",
                        "clearPackageData" to "true"
                    )
                )
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            lint {
                disable.add("Instantiatable")
                abortOnError = false
            }

            testOptions {
                unitTests.apply {
                    isReturnDefaultValues = true
                    isIncludeAndroidResources = true
                }
            }

            packaging {
                // Optimize APK size - remove excess files in the manifest and APK
                resources {
                    excludes.addAll(
                        listOf(
                            "**/kotlin/**",
                            "**/*.kotlin_module",
                            "**/*.version",
                            "**/*.txt",
                            "**/*.xml",
                            "**/*.properties"
                        )
                    )
                }
            }
        }
    }
}