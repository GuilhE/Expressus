import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.jetbrains.compose.desktop)
}

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(projects.shared)
                implementation(projects.sharedUiCompose)
                implementation(compose.desktop.currentOs)
                implementation(libs.jetbrains.kotlinx.coroutines.swing)
            }
        }
    }
}

compose {
    kotlinCompilerPlugin.set(libs.versions.composeMultiplatformCompiler)
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.0")
}

compose.desktop {
    application {
        mainClass = "presentation.ExpressusKt"
        jvmArgs += listOf("-Xmx2G")
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)

            version = "1.0.0"
            packageVersion = version as String
            packageName = "Expressus"
            description = "Kotlin Multiplatform Coffee Machine"
            copyright = "Copyright (c) 2022-present GuilhE"
            licenseFile.set(project.file("../LICENSE"))

            with(project.file("src/jvmMain/resources")) {
                macOS { iconFile.set(resolve("icon.icns")) }
                linux { iconFile.set(resolve("icon.png")) }
                windows { iconFile.set(resolve("icon.ico")) }
            }
        }
    }
}