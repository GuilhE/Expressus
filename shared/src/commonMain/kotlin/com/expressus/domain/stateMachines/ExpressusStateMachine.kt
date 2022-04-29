package com.expressus.domain.stateMachines

import co.touchlab.kermit.Logger
import com.expressus.domain.stateMachines.base.StateMachine
import com.expressus.domain.stateMachines.base.StateMachineBehavior
import com.expressus.domain.stateMachines.base.StateMachineCache
import com.expressus.domain.stateMachines.base.StateMachineState
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class ExpressusStateMachine(
    mayRestoreState: Boolean,
    onTransition: (effect: ExpressusState.SideEffect) -> Unit
) : StateMachineBehavior<ExpressusState.State, ExpressusState.Event, ExpressusState.SideEffect>, KoinComponent {

    override val stateMachineName: String = "Expressus"
    override val logger = Logger.withTag(stateMachineName)
    private val cache = StateMachineCache(stateMachineName, get())

    override val stateMachine = StateMachine.create<ExpressusState.State, ExpressusState.Event, ExpressusState.SideEffect> {
        val cachedState: StateMachineState<ExpressusState.State, ExpressusState.Event>? = if (mayRestoreState) cache.restoreState() else null
        val initialState = cachedState?.state ?: ExpressusState.State.Idle
        initialState(initialState).apply { logInitState(initialState) }

        state<ExpressusState.State.Idle> {
            on<ExpressusState.Event.OnStartGrinding> {
                logState(ExpressusState.State.Idle, ExpressusState.Event.OnStartGrinding)
                transitionTo(ExpressusState.State.Grinding, ExpressusState.SideEffect.Grinding)
            }
        }

        state<ExpressusState.State.Grinding> {
            on<ExpressusState.Event.OnStartPouring> {
                logState(ExpressusState.State.Grinding, ExpressusState.Event.OnStartPouring)
                transitionTo(ExpressusState.State.Pouring, ExpressusState.SideEffect.Pouring)
            }
        }

        state<ExpressusState.State.Pouring> {
            on<ExpressusState.Event.OnFinishPouring> {
                logState(ExpressusState.State.Pouring, ExpressusState.Event.OnFinishPouring)
                transitionTo(ExpressusState.State.Idle, ExpressusState.SideEffect.Served)
            }
        }

        onTransition {
            val validTransition = it as? StateMachine.Transition.Valid ?: return@onTransition
            with(validTransition) {
                logTransition(this)
                cache.saveState(StateMachineState(fromState, event))
                onTransition((sideEffect as ExpressusState.SideEffect))
            }
        }
    }

    override fun restoreStateIfAvailable(): Boolean {
        val cachedState: StateMachineState<ExpressusState.State, ExpressusState.Event>? = cache.restoreState()
        return cachedState?.let { current ->
            clearState()
            logStateRestore(current.state, current.event)
            stateMachine.transition(current.event)
            true
        } ?: false
    }

    override fun clearState() {
        cache.clearSavedState()
    }
}