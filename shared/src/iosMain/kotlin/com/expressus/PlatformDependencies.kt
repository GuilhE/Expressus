package com.expressus

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual class PlatformDependencies actual constructor() {
    actual fun getSettings(): Settings = AppleSettings(NSUserDefaults())
}