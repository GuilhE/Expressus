@file:OptIn(ExperimentalSettingsImplementation::class)

package com.expressus

import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.JvmPreferencesSettings
import com.russhwolf.settings.Settings

actual class PlatformDependencies actual constructor() {
    actual fun getSettings(): Settings = JvmPreferencesSettings.Factory().create("Expressus-settings")
}