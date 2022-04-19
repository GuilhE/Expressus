package com.expressus.domain.viewModels.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Contains base logic to store a [STATE] emitted by [stateFlow].
 * @param scope Where [stateFlow] will collect new state emissions.
 * @param stateFlow Flow to collect and save states from.
 */
internal abstract class ContainerHostSavedState<STATE : Any>(scope: CoroutineScope, stateFlow: Flow<STATE>) {

    init {
        scope.launch {
            stateFlow.collect {
                saveState(it)
            }
        }
    }

    abstract fun saveState(state: STATE)
    abstract fun restoreState()
    abstract fun clearState()

    fun handleSavedState(restore: Boolean) {
        if (restore) {
            restoreState()
        } else {
            clearState()
        }
    }
}