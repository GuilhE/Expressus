package com.expressus.domain.viewModels.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch

/**
 * Contains base logic to store a [STATE] emitted by [stateFlow].
 * @param scope Where [stateFlow] will collect new state emissions.
 * @param stateFlow Flow to collect and save states from.
 */
internal abstract class UiStateCacheProxy<STATE : Any>(scope: CoroutineScope, stateFlow: Flow<STATE>) {

    init {
        scope.launch {
            stateFlow.drop(1).collect { saveState(it) }
        }
    }

    abstract fun saveState(state: STATE)
    abstract fun restoreState()
    abstract fun clearState()

}