package com.expressus.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.expressus.compose.themes.GlossyOverlayTheme

@Composable
fun Glossy(offset: Offset = Offset(0f, 0f), radius: Float = Float.POSITIVE_INFINITY) {
    GlossyOverlayTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        center = offset,
                        radius = radius,
                        colors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary)
                    )
                )
        )
    }
}