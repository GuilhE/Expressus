package com.expressus.compose.components.leftPanel

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import com.expressus.compose.components.Panel

@Composable
fun CoffeeFaucet(modifier: Modifier, offsets: FaucetOffsets) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colors.primary,
            MaterialTheme.colors.primaryVariant
        )
    )
    BoxWithConstraints(modifier, contentAlignment = Alignment.Center) {
        Box(Modifier.padding(top = Dp(maxWidth.value / 3.5f))) {
            Panel(
                Modifier
                    .width(this@BoxWithConstraints.maxWidth / 3)
                    .height(this@BoxWithConstraints.maxWidth / 4),
                brush = gradient,
                bottomOffset = offsets.faucetOffset,
                convexTop = false
            )
        }
        Panel(
            Modifier
                .width(maxWidth)
                .height(maxWidth / 3),
            brush = gradient,
            bottomOffset = offsets.supportOffset,
            convexTop = false
        )
    }
}

data class FaucetOffsets(val faucetOffset: Dp, val supportOffset: Dp)