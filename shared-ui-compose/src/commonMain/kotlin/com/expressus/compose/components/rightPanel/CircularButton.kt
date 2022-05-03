package com.expressus.compose.components.rightPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp

@Composable
fun CircularButton(size: Dp, onClick: () -> Unit) {
    Box(Modifier
        .size(size)
        //we could have specified a Shape, but this way ripple will respect shape
        .graphicsLayer {
            shape = CircleShape
            clip = true
        }
        .background(
            brush = Brush.radialGradient(
                center = Offset(1f, 1f),
                colors = listOf(MaterialTheme.colors.surface, MaterialTheme.colors.background)
            )
        )
        .clickable { onClick() }
    )
}