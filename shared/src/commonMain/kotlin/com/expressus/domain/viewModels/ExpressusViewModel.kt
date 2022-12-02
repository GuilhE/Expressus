package com.expressus.domain.viewModels

import com.expressus.compose.components.Helper
import com.expressus.domain.stateMachines.ExpressusState
import com.expressus.domain.stateMachines.ExpressusStateMachineWithSavedState
import com.expressus.domain.stateMachines.ExpressusUiState
import com.expressus.domain.viewModels.base.containerHostVisibilityWrapper
import com.expressus.domain.viewModels.base.stateHolderVisibilityWrapper
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class ExpressusViewModel : ViewModel(),Helper {

    private val host = viewModelScope.containerHostVisibilityWrapper<ExpressusUiState, Nothing>(ExpressusUiState())
    private val containerStateHolder = viewModelScope.stateHolderVisibilityWrapper(host.container.stateFlow) { state ->
        host.intent { reduce { state } }
    }
    val state = host.container.stateFlow

    private val stateMachine = ExpressusStateMachineWithSavedState {
        host.intent {
            reduce {
                when (it) {
                    is ExpressusState.SideEffect.Grinding -> ExpressusUiState(isGrinding = true).also { grind() }
                    is ExpressusState.SideEffect.Pouring -> ExpressusUiState(isPouring = true).also { pour() }
                    is ExpressusState.SideEffect.Served -> ExpressusUiState()
                }
            }
        }
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

    fun handleSavedState(restore: Boolean) {
        stateMachine.handleSavedState(restore)
        containerStateHolder.handleSavedState(restore)
    }

    override fun play() {
        print("Play")
    }

    override fun foo() {
        print("foo")
    }
}
