package com.expressus.domain.stateMachines.core.cache

internal interface StateMachineCacheBehavior<STATE : Any, EVENT : Any, SIDE_EFFECT : Any> {
    fun clearSavedState()
}