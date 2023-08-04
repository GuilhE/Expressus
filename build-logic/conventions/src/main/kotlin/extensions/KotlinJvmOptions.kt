import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun LibraryExtension.addKotlinJvmOptions(options: List<String> = emptyList()) {
    (this as ExtensionAware).extensions.configure<KotlinJvmOptions>("kotlinOptions") { addOptions(options) }
}

internal fun BaseAppModuleExtension.addKotlinJvmOptions(options: List<String> = emptyList()) {
    (this as ExtensionAware).extensions.configure<KotlinJvmOptions>("kotlinOptions") { addOptions(options) }
}

private fun KotlinJvmOptions.addOptions(options: List<String> = emptyList()) {
    jvmTarget = JavaVersion.VERSION_17.toString()
    freeCompilerArgs = freeCompilerArgs + listOf(
        "-opt-in=kotlin.RequiresOptIn",
        "-opt-in=kotlin.Experimental",
        "-opt-in=kotlinx.coroutines.FlowPreview",
        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
    ) + options
}