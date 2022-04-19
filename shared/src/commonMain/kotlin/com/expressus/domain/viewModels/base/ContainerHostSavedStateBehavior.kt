package com.expressus.domain.viewModels.base

internal interface ContainerHostSavedStateBehavior<STATE : Any> {
    fun handleSavedState(restore: Boolean)
    val containerStateHolder: ContainerHostSavedState<STATE>
}