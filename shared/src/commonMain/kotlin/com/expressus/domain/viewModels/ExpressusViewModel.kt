package com.expressus.domain.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.expressus.domain.stateMachines.ExpressusState
import com.expressus.domain.stateMachines.ExpressusStateMachine
import com.expressus.domain.viewModels.core.containerHostVisibilityWrapper
import com.expressus.domain.viewModels.core.stateHolderVisibilityWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExpressusViewModel(stateRestoreEnabled: Boolean = true) : ViewModel() {

    private val host = viewModelScope.containerHostVisibilityWrapper<ExpressusUiState, Nothing>(ExpressusUiState())
    private val containerStateHolder = viewModelScope.stateHolderVisibilityWrapper(host.container.stateFlow) { state ->
        host.intent { reduce { state } }
    }
    val state = host.container.stateFlow

    private val stateMachine = ExpressusStateMachine(stateRestoreEnabled,
        onTransition = { effect ->
            host.intent {
                reduce {
                    when (effect) {
                        is ExpressusState.SideEffect.Grinding -> ExpressusUiState(isGrinding = true).also { grind() }
                        is ExpressusState.SideEffect.Pouring -> ExpressusUiState(isPouring = true).also { pour() }
                        is ExpressusState.SideEffect.Served -> ExpressusUiState()
                    }
                }
            }
        }
    )

    init {
        if (!stateRestoreEnabled) {
            clearSavedStates()
        }
    }

    private fun clearSavedStates() {
        containerStateHolder.clear()
        stateMachine.clearSavedState()
    }

    private fun grind() {
        viewModelScope.launch {
            delay(5000)
            stateMachine.transitionOn(ExpressusState.Event.OnStartPouring)
        }
    }

    private fun pour() {
        viewModelScope.launch {
            delay(5000)
            stateMachine.transitionOn(ExpressusState.Event.OnFinishPouring)
        }
    }

    fun makeCoffee() {
        if (state.value.isOnStandBy()) {
            stateMachine.transitionOn(ExpressusState.Event.OnStartGrinding)
        }
    }
}
