@file:Suppress("SpellCheckingInspection", "MemberVisibilityCanBePrivate", "unused")

package com.expressus.domain.stateMachines

import kotlinx.serialization.Serializable

@Serializable
data class ExpressusUiState(
    val brewing: Boolean = false,
    val pouring: Boolean = false
) {
    fun isMakingCoffee() = brewing || pouring
    fun isOnStandBy() = !isMakingCoffee()
    fun label() = toString() //for iOS since toString() wont be visible

    override fun toString(): String {
        return if (isMakingCoffee()) {
            when {
                brewing -> "Brewing"
                pouring -> "Pouring"
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
        object Brewing : State() {
            override fun toString(): String = "Brewing"
        }

        @Serializable
        object Pouring : State() {
            override fun toString(): String = "Pouring"
        }
    }

    @Serializable
    sealed class Event : ExpressusState() {
        @Serializable
        object OnStartBrewing : Event() {
            override fun toString(): String = "OnStartBrewing"
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
        object Brewing : SideEffect() {
            override fun toString(): String = "Brewing"
        }

        object Pouring : SideEffect() {
            override fun toString(): String = "Pouring"
        }

        object Served : SideEffect() {
            override fun toString(): String = "Served"
        }
    }
}