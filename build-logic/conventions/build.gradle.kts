import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    maven("https://redirector.kotlinlang.org/maven/dev")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.toString()))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    implementation(libs.gradle.android.tools)
    implementation(libs.gradle.kotlin)
}

group = "buildlogic.plugins"

gradlePlugin {
    plugins {
        register("AndroidAppConventionPlugin") {
            id = "${project.group}.application"
            implementationClass = "AndroidAppConventionPlugin"
        }
        register("KMPAndroidLibraryConventionPlugin") {
            id = "${project.group}.kmp.library"
            implementationClass = "KMPAndroidLibraryConventionPlugin"
        }
        register("CMPLibraryConventionPlugin") {
            id = "${project.group}.kmp.compose"
            implementationClass = "CMPLibraryConventionPlugin"
        }
    }
}