@file:Suppress("unused")

package com.expressus.domain.stateMachines

import co.touchlab.kermit.Logger
import com.expressus.domain.stateMachines.core.StateMachine
import com.expressus.domain.stateMachines.core.StateMachineLogDecorator
import com.expressus.domain.stateMachines.core.cache.StateMachineCacheBehavior
import com.expressus.domain.stateMachines.core.cache.StateMachineSnapshot
import com.whosnext.app.fsm.core.cache.StateMachineCache
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class ExpressusStateMachine(
    shouldRestoreState: Boolean,
    onTransition: (effect: ExpressusState.SideEffect) -> Unit,
    onRestored: ((state: ExpressusState.State) -> Unit)? = null
) : StateMachineLogDecorator<ExpressusState.State, ExpressusState.Event, ExpressusState.SideEffect>,
    StateMachineCacheBehavior<ExpressusState.State, ExpressusState.Event, ExpressusState.SideEffect>,
    KoinComponent {

    override val stateMachineName: String = "Expressus"
    override val logger = Logger.withTag(stateMachineName)
    override val disableLogs: Boolean = false

    private val cache = StateMachineCache(stateMachineName, get())
    private val stateMachine = StateMachine.create<ExpressusState.State, ExpressusState.Event, ExpressusState.SideEffect> {
        val snapshot: StateMachineSnapshot<ExpressusState.State, ExpressusState.Event>? = if (shouldRestoreState) cache.restoreState() else null
        val initialState = snapshot?.state ?: ExpressusState.State.Idle
        initialState(initialState).apply {
            logInitState(initialState)
            onRestored?.invoke(initialState)
        }

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
                cache.saveState(StateMachineSnapshot(fromState, event))
                onTransition((sideEffect as ExpressusState.SideEffect))
            }
        }
    }

    init {
        handleCache(shouldRestoreState)
    }

    private fun handleCache(shouldRestoreState: Boolean) {
        if (shouldRestoreState) {
            if (!restoreStateIfAvailable()) {
                clearSavedState()
            }
        } else {
            clearSavedState()
        }
    }

    fun currentState(): ExpressusState.State = stateMachine.state

    fun transitionOn(event: ExpressusState.Event) {
        stateMachine.transition(event)
    }

    private fun restoreStateIfAvailable(): Boolean {
        val snapshot: StateMachineSnapshot<ExpressusState.State, ExpressusState.Event>? = cache.restoreState()
        return snapshot?.let { current ->
            cache.clear()
            logStateRestore(current.state, current.event)
            stateMachine.transition(current.event)
            true
        } ?: false
    }

    override fun clearSavedState() {
        cache.clear()
    }
}