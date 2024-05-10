package com.expressus.domain.viewModels

import com.expressus.domain.viewModels.core.UiStateCacheProxy
import com.expressus.domain.viewModels.core.UiStateCache
import com.russhwolf.settings.Settings
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
) = ExpressusSavedStateContainer(this, stateFlow, onRestore)

/**
 * Implementation of [UiStateCacheProxy]. It will use [UiStateCache] to save and restore states.
 * @param scope Where [stateFlow] will collect new state emissions.
 * @param stateFlow Flow to collect and save states from.
 * @param onRestore Callback with the cached state to be restored.
 */
internal class ExpressusSavedStateContainer(
    scope: CoroutineScope,
    stateFlow: Flow<ExpressusUiState>,
    private val onRestore: (ExpressusUiState) -> Unit
) : UiStateCacheProxy<ExpressusUiState>(scope, stateFlow), KoinComponent {

    private val cache = UiStateCache("UiState", get<Settings>())

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
