package com.expressus.domain.stateMachines.core

import co.touchlab.kermit.Logger

internal interface StateMachineLogDecorator<STATE : Any, EVENT : Any, SIDE_EFFECT : Any> {

    fun logInitState(state: STATE) {
        if (!disableLogs) {
            logger.d { "=== $stateMachineName ===" }
            logger.d { "Init state: $state" }
        }
    }

    fun logState(state: STATE, event: EVENT) {
        if (!disableLogs) {
            logger.d { "======" }
            logger.d { "From state: $state" }
            logger.d { "On event: $event" }
        }
    }

    fun logTransition(transition: StateMachine.Transition.Valid<STATE, EVENT, SIDE_EFFECT>) {
        if (!disableLogs) {
            logger.d { "Transition to: ${transition.toState} (with sideEffect: ${transition.sideEffect})" }
        }
    }

    fun logStateRestore(state: STATE, event: EVENT) {
        if (!disableLogs) {
            logger.d { "=== $stateMachineName ===" }
            logger.d { "Restored to state: $state" }
            logger.d { "Will dispatch event: $event" }
        }
    }

    val stateMachineName: String
    val logger: Logger
    val disableLogs: Boolean
}