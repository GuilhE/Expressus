package com.expressus.domain.stateMachines.base

internal interface StateMachineSavedStateBehavior<STATE : Any, EVENT : Any, SIDE_EFFECT : Any> {
    /**
     * This instance must be initialized by lazy to happen after [handleSavedState].
     *
     * ```
     * override val behavior: StateMachineBehavior<STATE, EVENT, SIDE_EFFECT> by lazy { MyStateMachine() }
     * ```
     */
    val behavior: StateMachineBehavior<STATE, EVENT, SIDE_EFFECT>
    fun currentState(): STATE
    fun transitionOn(event: EVENT)
    fun handleSavedState(restore: Boolean)
}