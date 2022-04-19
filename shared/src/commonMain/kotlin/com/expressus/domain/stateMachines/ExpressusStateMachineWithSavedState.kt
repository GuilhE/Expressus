package com.expressus.domain.stateMachines

import com.expressus.domain.stateMachines.base.StateMachineBehavior
import com.expressus.domain.stateMachines.base.StateMachineSavedStateBehavior

internal class ExpressusStateMachineWithSavedState(
    onTransition: (sideEffect: ExpressusState.SideEffect) -> Unit
) : StateMachineSavedStateBehavior<ExpressusState.State, ExpressusState.Event, ExpressusState.SideEffect> {

    private var restoringState = false

    override val behavior: StateMachineBehavior<ExpressusState.State, ExpressusState.Event, ExpressusState.SideEffect> by lazy {
        ExpressusStateMachine(restoringState, onTransition)
    }

    override fun currentState(): ExpressusState.State = behavior.stateMachine.state

    override fun transitionOn(event: ExpressusState.Event) {
        behavior.stateMachine.transition(event)
    }

    override fun handleSavedState(restore: Boolean) {
        restoringState = restore
        if (restore) {
            behavior.restoreStateIfAvailable()
        } else {
            behavior.clearState()
        }
    }
}