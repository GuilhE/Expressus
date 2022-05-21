import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version Versions.JetBrains.Compose.desktop
}

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(project(":shared"))
                implementation(project(":shared-ui-compose"))
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "presentation.ExpressusKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            val iconsRoot = project.file("src/jvmMain/resources")
            macOS {
                iconFile.set(iconsRoot.resolve("icon.icns"))
            }
            windows {
                iconFile.set(iconsRoot.resolve("icon.ico"))
            }
            linux {
                iconFile.set(iconsRoot.resolve("icon.png"))
            }
        }
    }
}