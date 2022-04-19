package com.expressus.domain.stateMachines.base

import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

@Serializable
internal data class StateMachineState<STATE, EVENT>(val state: STATE, val event: EVENT)

internal class StateMachineCache(
    val stateMachineName: String,
    val settings: Settings,
    val decoder: Json = Json { ignoreUnknownKeys = true }
) {

    @OptIn(ExperimentalSerializationApi::class)
    inline fun <reified STATE : Any, reified EVENT : Any> saveState(state: StateMachineState<STATE, EVENT>) {
        settings.putString("${stateMachineName}_cache", decoder.encodeToString(state))
    }

    @OptIn(ExperimentalSerializationApi::class)
    inline fun <reified STATE, reified EVENT> restoreState(): StateMachineState<STATE, EVENT>? {
        val key = "${stateMachineName}_cache"
        return if (settings.contains(key)) {
            try {
                decoder.decodeFromString(settings.getString(key))
            } catch (e: SerializationException) {
                null
            }
        } else null
    }

    fun clearSavedState() {
        settings.remove("${stateMachineName}_cache")
    }
}