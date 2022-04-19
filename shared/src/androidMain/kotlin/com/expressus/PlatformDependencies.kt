package com.expressus

import android.content.SharedPreferences
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings

lateinit var sharedPrefsForPlatformDepedencies: SharedPreferences

actual class PlatformDependencies actual constructor() {
    actual fun getSettings(): Settings = AndroidSettings(sharedPrefsForPlatformDepedencies)
}