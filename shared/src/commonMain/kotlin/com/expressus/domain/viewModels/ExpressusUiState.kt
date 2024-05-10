package com.expressus.domain.viewModels

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