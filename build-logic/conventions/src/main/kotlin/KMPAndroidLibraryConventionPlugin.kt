import com.android.build.gradle.LibraryExtension
import extensions.addKotlinAndroidConfigurations
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@OptIn(ExperimentalKotlinGradlePluginApi::class)
class KMPAndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")
            pluginManager.apply("com.android.library")
            extensions.configure<LibraryExtension> {
                addKotlinAndroidConfigurations(extensions.getByType<VersionCatalogsExtension>().named("libs")).also {
                    sourceSets.getByName("main").manifest.srcFile("src/androidMain/AndroidManifest.xml")
                }
            }
            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_17)
                    }
                }
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
}