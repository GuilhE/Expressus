package com.expressus

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings

actual class PlatformDependencies actual constructor() {
    actual fun getSettings(): Settings = PreferencesSettings.Factory().create("Expressus-settings")
}