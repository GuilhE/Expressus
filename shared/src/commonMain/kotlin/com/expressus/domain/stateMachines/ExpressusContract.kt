@file:Suppress("SpellCheckingInspection", "MemberVisibilityCanBePrivate", "unused")

package com.expressus.domain.stateMachines

import kotlinx.serialization.Serializable

@Serializable
data class ExpressusUiState(
    val isGrinding: Boolean = false,
    val isPouring: Boolean = false
) {
    fun isMakingCoffee() = isGrinding || isPouring
    fun isOnStandBy() = !isMakingCoffee()
    fun label() = toString() //for iOS since toString() wont be visible

    override fun toString(): String {
        return if (isMakingCoffee()) {
            when {
                isGrinding -> "Grinding"
                isPouring -> "Pouring"
                else -> "Stand by"
            }
        } else "Stand by"
    }
}

sealed class ExpressusState {

    @Serializable
    sealed class State : ExpressusState() {
        @Serializable
        object Idle : State() {
            override fun toString(): String = "Idle"
        }

        @Serializable
        object Grinding : State() {
            override fun toString(): String = "Grinding"
        }

        @Serializable
        object Pouring : State() {
            override fun toString(): String = "Pouring"
        }
    }

    @Serializable
    sealed class Event : ExpressusState() {
        @Serializable
        object OnStartGrinding : Event() {
            override fun toString(): String = "OnStartGrinding"
        }

        @Serializable
        object OnStartPouring : Event() {
            override fun toString(): String = "OnStartPouring"
        }

        @Serializable
        object OnFinishPouring : Event() {
            override fun toString(): String = "OnFinishPouring"
        }
    }

    sealed class SideEffect : ExpressusState() {
        object Grinding : SideEffect() {
            override fun toString(): String = "Grinding"
        }

        object Pouring : SideEffect() {
            override fun toString(): String = "Pouring"
        }

        object Served : SideEffect() {
            override fun toString(): String = "Served"
        }
    }
}