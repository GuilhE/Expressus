package com.expressus.compose

import androidx.compose.ui.window.ComposeUIViewController
import com.expressus.compose.components.ExpressusMobile
import platform.UIKit.UIViewController

object SharedViewControllers {
    fun Expressus(isGrinding: Boolean, isPouring: Boolean, isMakingCoffee: Boolean, status: String, makeCoffee: () -> Unit): UIViewController {
        return ComposeUIViewController {
            ExpressusMobile(isGrinding, isPouring, isMakingCoffee, status, makeCoffee)
        }
    }
}