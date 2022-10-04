pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Expressus"
include(":shared")
include(":shared-ui-compose")
include(":androidApp")
include(":desktopApp")
