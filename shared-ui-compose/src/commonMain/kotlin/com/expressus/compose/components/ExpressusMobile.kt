package com.expressus.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.centerPanel.MachineFrame
import com.expressus.compose.components.leftPanel.CoffeeSlot
import com.expressus.compose.components.leftPanel.FaucetOffsets
import com.expressus.compose.components.rightPanel.CircularButton
import com.expressus.compose.components.rightPanel.CoffeeSelectors
import com.expressus.compose.components.rightPanel.Display
import com.expressus.compose.components.rightPanel.FanGrid
import com.expressus.compose.components.rightPanel.MachineRightFrame
import com.expressus.compose.components.rightPanel.PaymentSocket
import com.expressus.compose.themes.CoffeeSelectorsTheme
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ExpressusMobile(isGrinding: Boolean, isPouring: Boolean, isMakingCoffee: Boolean, status: String, makeCoffee: () -> Unit) {
    MachineFrame(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CoffeeSlot(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(10.dp),
                isGrinding = isGrinding,
                isPouring = isPouring,
                pouringSpeed = 1L,
                slotOffset = 50.dp,
                faucetOffsets = FaucetOffsets(10.dp, 10.dp)
            )
            Spacer(Modifier.size(50.dp))
            Display(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 50.dp),
                text = status
            )
            Spacer(Modifier.size(50.dp))
            CoffeeSelectorsTheme {
                CircularButton(size = 70.dp, enabled = !isMakingCoffee) { makeCoffee() }
            }
        }
    }
}

@Composable
internal fun CoffeeSelectorsMobile(isMakingCoffee: Boolean, onAnyClick: () -> Unit, onSwiftUiClick: () -> Unit, onComposeClick: () -> Unit) {
    val options = remember { List(3) { "" } + listOf("SWIFT_UI", "COMPOSE") }
    MachineRightFrame(Modifier) {
        Column(
            Modifier.weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            CoffeeSelectors(Modifier, options.toImmutableList(), isMakingCoffee) { index ->
                when (index) {
                    options.size - 1 -> onComposeClick()
                    options.size - 2 -> onSwiftUiClick()
                    else -> onAnyClick()
                }
            }
            PaymentSocket(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            )
        }
        Column(
            Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FanGrid(
                Modifier
                    .width(120.dp)
                    .scale(1.5f)
            )
        }
    }
}