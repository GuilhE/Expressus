package com.expressus.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.expressus.compose.components.CoffeeSelectorsMobile
import com.expressus.compose.components.ExpressusMobile

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.weight(1f)) {
                ExpressusMobile(isGrinding = false, isPouring = false, isMakingCoffee = false, status = "Stand by") {}
            }
            Box(Modifier.weight(1f)) {
                CoffeeSelectorsMobile(false, {}, {}, {})
            }
        }
    }
}