@file:Suppress("UnstableApiUsage", "unused")

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

class KMPAndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        pluginManager.apply("com.android.kotlin.multiplatform.library")

        val kmpe = extensions.getByType<KotlinMultiplatformExtension>()
        val androidLibrary = (kmpe as ExtensionAware).extensions
            .getByType(KotlinMultiplatformAndroidLibraryTarget::class.java)
        androidLibrary.apply {
            val catalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
            compileSdk = catalog.findVersion("androidCompileSdk").get().toString().toInt()
            minSdk = catalog.findVersion("androidMinSdk").get().toString().toInt()
            compilerOptions { jvmTarget.set(JvmTarget.JVM_17) }
            lint {
                disable.add("Instantiatable")
                abortOnError = false
            }
        }

        kotlinExtension.apply {
            sourceSets.all {
                languageSettings.apply {
                    optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                    optIn("kotlinx.cinterop.ExperimentalForeignApi")
                    optIn("kotlin.experimental.ExperimentalObjCName")
                    optIn("kotlin.RequiresOptIn")
                }
            }
        }
    }
}