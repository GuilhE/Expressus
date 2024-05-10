package com.expressus.domain.viewModels.core

internal interface ContainerHostSavedStateBehavior<STATE : Any> {
    fun handleSavedState(restore: Boolean)
    fun clear()
    val containerStateHolder: UiStateCacheProxy<STATE>
}