package com.whosnext.app.fsm.core.cache

import com.expressus.domain.stateMachines.core.cache.StateMachineSnapshot
import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Functionality to save and restore a [StateMachineSnapshot].
 * @param stateMachineName Key to used inside [Settings].
 * @param settings Instance of [multiplatform-settings](https://github.com/russhwolf/multiplatform-settings).
 * @param decoder [Json] instance for json operations.
 */
internal class StateMachineCache(
    val stateMachineName: String,
    val settings: Settings,
    val decoder: Json = Json { ignoreUnknownKeys = true }
) {

    inline fun <reified STATE : Any, reified EVENT : Any> saveState(state: StateMachineSnapshot<STATE, EVENT>) {
        settings.putString("${stateMachineName}_cache", decoder.encodeToString(state))
    }

    inline fun <reified STATE, reified EVENT> restoreState(): StateMachineSnapshot<STATE, EVENT>? {
        val key = "${stateMachineName}_cache"
        return if (settings.contains(key)) {
            try {
                decoder.decodeFromString(settings.getString(key, ""))
            } catch (e: SerializationException) {
                null
            }
        } else null
    }

    fun clear() {
        settings.remove("${stateMachineName}_cache")
    }
}