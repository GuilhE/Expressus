package com.expressus.compose

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.ComposeUIViewController
import com.expressus.compose.components.ExpressusMobile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import platform.UIKit.UIViewController

object SharedViewControllers {

//    fun expressus(isGrinding: Boolean, isPouring: Boolean, isMakingCoffee: Boolean, status: String, makeCoffee: () -> Unit): UIViewController {
//        return ComposeUIViewController {
//            ExpressusMobile(isGrinding, isPouring, isMakingCoffee, status, makeCoffee)
//        }
//    }

    private data class ComposeUIViewState(
        val isGrinding: Boolean = false,
        val isPouring: Boolean = false,
        val isMakingCoffee: Boolean = false,
        val status: String = ""
    )
    private val _state = MutableStateFlow(ComposeUIViewState())

    fun expressus(isGrinding: Boolean, isPouring: Boolean, isMakingCoffee: Boolean, status: String, makeCoffee: () -> Unit): UIViewController {
        return ComposeUIViewController {
            var state by remember { mutableStateOf(ComposeUIViewState(isGrinding, isPouring, isMakingCoffee, status)) }
            ExpressusMobile(state.isGrinding, state.isPouring, state.isMakingCoffee, state.status, makeCoffee)
            LaunchedEffect(Unit) {
                _state.collect { state = it }
            }

        }
    }

    fun updateExpressus(isGrinding: Boolean, isPouring: Boolean, isMakingCoffee: Boolean, status: String) {
        _state.update { ComposeUIViewState(isGrinding = isGrinding, isPouring = isPouring, isMakingCoffee = isMakingCoffee, status = status) }
    }
}