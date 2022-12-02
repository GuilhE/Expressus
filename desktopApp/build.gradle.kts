import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version Versions.JetBrains.Compose.desktop
    id("maven-publish")
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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:${Versions.JetBrains.kotlinxCoroutines}")
            }
        }
    }
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