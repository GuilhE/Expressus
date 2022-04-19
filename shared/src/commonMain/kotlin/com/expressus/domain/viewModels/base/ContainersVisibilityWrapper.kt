package com.expressus.domain.viewModels.base

import com.expressus.domain.stateMachines.ExpressusUiState
import com.expressus.domain.viewModels.containerHostSavedState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

/**
 * Just to keep it private without public access.
 * @param initialState The initial state of the container.
 */
internal fun <STATE : Any, SIDE_EFFECT : Any> CoroutineScope.containerHostVisibilityWrapper(initialState: STATE) =
    object : ContainerHost<STATE, SIDE_EFFECT> {
        override val container = this@containerHostVisibilityWrapper.container<STATE, SIDE_EFFECT>(initialState)
    }

/**
 * Just to keep it private without public access.
 * @param stateFlow Flow to collect and save states from.
 * @param onRestore Callback with the cached state to be restored.
 */
internal fun CoroutineScope.stateHolderVisibilityWrapper(
    stateFlow: Flow<ExpressusUiState>,
    onRestore: (ExpressusUiState) -> Unit
) = object : ContainerHostSavedStateBehavior<ExpressusUiState> {

    override val containerStateHolder = this@stateHolderVisibilityWrapper.containerHostSavedState(stateFlow, onRestore)

    override fun handleSavedState(restore: Boolean) {
        containerStateHolder.handleSavedState(restore)
    }
}