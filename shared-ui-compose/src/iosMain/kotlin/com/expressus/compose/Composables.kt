@file:Suppress("unused", "SpellCheckingInspection")

package com.expressus.compose

import androidx.compose.runtime.Composable
import com.expressus.compose.components.CoffeeSelectorsMobile
import com.expressus.compose.components.ExpressusMobile
import com.github.guilhe.kmp.composeuiviewcontroller.ComposeUIViewController
import com.github.guilhe.kmp.composeuiviewcontroller.ComposeUIViewControllerState

data class ExpressusMobileState(
    val isGrinding: Boolean,
    val isPouring: Boolean,
    val isMakingCoffee: Boolean,
    val status: String
)

@ComposeUIViewController
@Composable
internal fun ExpressusMobile(@ComposeUIViewControllerState state: ExpressusMobileState, makeCoffee: () -> Unit) {
    with(state) {
        ExpressusMobile(isGrinding, isPouring, isMakingCoffee, status, makeCoffee)
    }
}

@ComposeUIViewController
@Composable
internal fun CoffeeSelectorsMobile(
    @ComposeUIViewControllerState isMakingCoffee: Boolean,
    onAnyClick: () -> Unit,
    onSwiftUiClick: () -> Unit,
    onComposeClick: () -> Unit
) {
    CoffeeSelectorsMobile(isMakingCoffee, onAnyClick, onSwiftUiClick, onComposeClick)
}