package com.expressus.domain.stateMachines.base

import co.touchlab.kermit.Logger
import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

internal interface StateMachineBehavior<STATE : Any, EVENT : Any, SIDE_EFFECT : Any> {

    fun logInitState(state: STATE) {
        logger.d { "=== $stateMachineName ===" }
        logger.d { "Init state: $state" }
    }

    fun logState(state: STATE, event: EVENT) {
        logger.d { "======" }
        logger.d { "From state: $state" }
        logger.d { "On event: $event" }
    }

    fun logTransition(transition: StateMachine.Transition.Valid<STATE, EVENT, SIDE_EFFECT>) {
        logger.d { "Transition to: ${transition.toState} (with sideEffect: ${transition.sideEffect})" }
    }

    fun logStateRestore(state: STATE, event: EVENT) {
        logger.d { "=== $stateMachineName ===" }
        logger.d { "Restored to state: $state" }
        logger.d { "Will dispatch event: $event" }
    }

    fun restoreStateIfAvailable(): Boolean
    fun clearState()

    val stateMachineName: String
    val logger: Logger
    val stateMachine: StateMachine<STATE, EVENT, SIDE_EFFECT>
}
