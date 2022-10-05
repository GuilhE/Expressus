package com.expressus

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual class PlatformDependencies actual constructor() {
    actual fun getSettings(): Settings = NSUserDefaultsSettings(NSUserDefaults())
}