package com.expressus.domain.stateMachines

import kotlinx.serialization.Serializable

internal sealed class ExpressusState {

    @Serializable
    sealed class State : ExpressusState() {
        @Serializable
        data object Idle : State()

        @Serializable
        data object Grinding : State()

        @Serializable
        data object Pouring : State()
    }

    @Serializable
    sealed class Event : ExpressusState() {
        @Serializable
        data object OnStartGrinding : Event()

        @Serializable
        data object OnStartPouring : Event()

        @Serializable
        data object OnFinishPouring : Event()
    }

    sealed class SideEffect : ExpressusState() {
        data object Grinding : SideEffect()

        data object Pouring : SideEffect()

        data object Served : SideEffect()
    }
}