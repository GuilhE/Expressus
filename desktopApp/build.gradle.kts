import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("buildlogic.plugins.kmp.compose")
}

compose {
    desktop {
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

                with(project.file("src/desktopMain/resources")) {
                    macOS { iconFile.set(resolve("icon.icns")) }
                    linux { iconFile.set(resolve("icon.png")) }
                    windows { iconFile.set(resolve("icon.ico")) }
                }
            }
        }
    }
}

kotlin {
    jvm("desktop")
    sourceSets {
        val desktopMain by getting {
            dependencies {
                implementation(projects.shared)
                implementation(projects.sharedUiCompose)
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.swing)
            }
        }
    }
}