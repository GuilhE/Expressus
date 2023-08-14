@file:Suppress("unused", "SpellCheckingInspection")

package com.expressus.compose

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.ComposeUIViewController
import com.expressus.compose.components.CoffeeSelectorsMobile
import com.expressus.compose.components.ExpressusMobile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import platform.UIKit.UIViewController

object ExpressusUIViewController {

    //https://github.com/JetBrains/compose-multiplatform/issues/3478
    private val viewState = MutableStateFlow(ViewState())
    private data class ViewState(
        val isGrinding: Boolean = false,
        val isPouring: Boolean = false,
        val isMakingCoffee: Boolean = false,
        val status: String = ""
    )

    fun composable(makeCoffee: () -> Unit): UIViewController {
        return ComposeUIViewController {
            with(viewState.collectAsState().value) {
                ExpressusMobile(this.isGrinding, this.isPouring, this.isMakingCoffee, this.status, makeCoffee)
            }
        }
    }

    fun update(isGrinding: Boolean, isPouring: Boolean, isMakingCoffee: Boolean, status: String) {
        viewState.update { ViewState(isGrinding = isGrinding, isPouring = isPouring, isMakingCoffee = isMakingCoffee, status = status) }
    }
}

object CoffeeSelectorsUIViewController {
    
    //https://github.com/JetBrains/compose-multiplatform/issues/3478
    private val isMakingCoffee = mutableStateOf(false)
    
    fun composable(onAnyClick: () -> Unit, onSwiftUiClick: () -> Unit, onComposeClick: () -> Unit): UIViewController {
        return ComposeUIViewController {
            CoffeeSelectorsMobile(isMakingCoffee.value, onAnyClick, onSwiftUiClick, onComposeClick)
        }
    }

    fun update(isMakingCoffee: Boolean) {
        this.isMakingCoffee.value = isMakingCoffee
    }
}
