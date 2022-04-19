package com.expressus

import com.russhwolf.settings.Settings

expect class PlatformDependencies() {
    fun getSettings(): Settings
}