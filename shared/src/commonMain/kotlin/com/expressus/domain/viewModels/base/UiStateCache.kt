package com.expressus.domain.viewModels.base

import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

/**
 * Functionality to save and restore a [UI_STATE].
 * @param uiStateName Key to used inside [Settings].
 * @param settings Instance of [multiplatform-settings](https://github.com/russhwolf/multiplatform-settings).
 * @param decoder [Json] instance for json operations.
 */
internal class UiStateCache(
    private val uiStateName: String,
    private val settings: Settings,
    private val decoder: Json = Json { ignoreUnknownKeys = true }
) {
    @OptIn(ExperimentalSerializationApi::class)
    inline fun <reified UI_STATE : Any> saveState(state: UI_STATE) {
        settings.putString("${uiStateName}_cache", decoder.encodeToString(state))
    }

    @OptIn(ExperimentalSerializationApi::class)
    inline fun <reified UI_STATE> restoreState(): UI_STATE? {
        val key = "${uiStateName}_cache"
        return if (settings.contains(key)) {
            try {
                decoder.decodeFromString(settings.getString(key))
            } catch (e: SerializationException) {
                null
            }
        } else null
    }

    fun clearState() {
        settings.remove("${uiStateName}_cache")
    }
}