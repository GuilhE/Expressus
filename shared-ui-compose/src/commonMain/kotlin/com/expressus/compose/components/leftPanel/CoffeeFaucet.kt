package com.expressus.compose.components.leftPanel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.Panel

@Composable
fun CoffeeFaucet(width: Dp) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colors.primary,
            MaterialTheme.colors.primaryVariant
        )
    )

    val height = width.value / 3
    Box(Modifier, contentAlignment = Alignment.Center) {
        Box(Modifier.padding(top = height.dp)) {
            Panel(
                Modifier
                    .width(width / 3)
                    .height(width / 4),
                brush = gradient,
                bottomOffset = width / 10,
                convexTop = false
            )
        }
        Panel(
            Modifier
                .width(width)
                .height(height.dp),
            brush = gradient,
            bottomOffset = width / 10,
            convexTop = false
        )
    }
}
