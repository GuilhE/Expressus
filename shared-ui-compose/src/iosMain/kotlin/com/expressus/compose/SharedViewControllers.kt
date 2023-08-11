@file:Suppress("unused", "SpellCheckingInspection")

package com.expressus.compose

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.window.ComposeUIViewController
import com.expressus.compose.components.CoffeeSelectorsMobile
import com.expressus.compose.components.ExpressusMobile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import platform.UIKit.UIViewController

object SharedViewControllers {

    //https://github.com/JetBrains/compose-multiplatform/issues/3478
    private data class ComposeUIViewState(
        val isGrinding: Boolean = false,
        val isPouring: Boolean = false,
        val isMakingCoffee: Boolean = false,
        val status: String = ""
    )

    private val viewState = MutableStateFlow(ComposeUIViewState())

    fun expressus(makeCoffee: () -> Unit): UIViewController {
        return ComposeUIViewController {
            with(viewState.collectAsState().value) {
                ExpressusMobile(this.isGrinding, this.isPouring, this.isMakingCoffee, this.status, makeCoffee)
            }
        }
    }

    fun updateExpressus(isGrinding: Boolean, isPouring: Boolean, isMakingCoffee: Boolean, status: String) {
        viewState.update { ComposeUIViewState(isGrinding = isGrinding, isPouring = isPouring, isMakingCoffee = isMakingCoffee, status = status) }
    }

    fun coffeeSelectors(onSwiftUiClick: () -> Unit, onComposeClick: () -> Unit): UIViewController {
        return ComposeUIViewController { CoffeeSelectorsMobile(onSwiftUiClick, onComposeClick) }
    }
}