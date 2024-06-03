package com.expressus.compose.components.leftPanel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import com.expressus.compose.components.Panel
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
data class FaucetOffsets(val faucetOffset: Dp, val supportOffset: Dp)

@Composable
fun CoffeeFaucet(modifier: Modifier, offsets: FaucetOffsets) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.primaryContainer
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