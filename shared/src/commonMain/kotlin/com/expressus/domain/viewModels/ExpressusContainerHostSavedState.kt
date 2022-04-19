package com.expressus.domain.viewModels

import com.expressus.domain.stateMachines.ExpressusUiState
import com.expressus.domain.viewModels.base.ContainerHostSavedState
import com.expressus.domain.viewModels.base.UiStateCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 * Extension function to create [TimerContainerHostSavedState].
 * @param stateFlow Flow to collect and save states from.
 * @param onRestore Callback with the cached state to be restored.
 */
internal fun CoroutineScope.containerHostSavedState(
    stateFlow: Flow<ExpressusUiState>,
    onRestore: (ExpressusUiState) -> Unit
) = ExpressusContainerHostSavedState(this, stateFlow, onRestore)

/**
 * Implementation of [ContainerHostSavedState]. It will use [UiStateCache] to save and restore states.
 * @param scope Where [stateFlow] will collect new state emissions.
 * @param stateFlow Flow to collect and save states from.
 * @param onRestore Callback with the cached state to be restored.
 */
internal class ExpressusContainerHostSavedState(
    scope: CoroutineScope,
    stateFlow: Flow<ExpressusUiState>,
    private val onRestore: (ExpressusUiState) -> Unit
) : ContainerHostSavedState<ExpressusUiState>(scope, stateFlow), KoinComponent {

    private val cache = UiStateCache("UiState", get())

    override fun saveState(state: ExpressusUiState) {
        cache.saveState(state)
    }

    override fun restoreState() {
        cache.restoreState<ExpressusUiState>()?.let { cached -> onRestore(cached) }
    }

    override fun clearState() {
        cache.clearState()
    }
}
