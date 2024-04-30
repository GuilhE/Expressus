plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.toString()))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
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
            id = "${project.group}.kmp.library.android"
            implementationClass = "KMPAndroidLibraryConventionPlugin"
        }
        register("CMPLibraryConventionPlugin") {
            id = "${project.group}.kmp.compose"
            implementationClass = "CMPLibraryConventionPlugin"
        }
    }
}