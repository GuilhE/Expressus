pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "Expressus"
include(":shared")
include(":shared-ui-compose")
include(":androidApp")
include(":desktopApp")
