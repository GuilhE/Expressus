package com.expressus

import android.content.SharedPreferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

lateinit var sharedPrefsForPlatformDependencies: SharedPreferences

actual class PlatformDependencies actual constructor() {
    actual fun getSettings(): Settings = SharedPreferencesSettings(sharedPrefsForPlatformDependencies)
}