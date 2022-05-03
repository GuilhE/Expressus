package com.expressus.compose.components.leftPanel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.Panel
import com.expressus.compose.themes.CoffeeTheme

@Composable
fun BottomPanel() {
    CoffeeTheme {
        val gradient = Brush.horizontalGradient(
            colors = listOf(
                MaterialTheme.colors.primary,
                MaterialTheme.colors.secondaryVariant,
                MaterialTheme.colors.secondary,
                MaterialTheme.colors.secondary
            )
        )
        Panel(
            modifier = Modifier
                .fillMaxSize()
                .blur(0.5.dp)
                .padding(start = 30.dp, end = 30.dp, top = 30.dp),
            brush = gradient,
            convexTop = false,
            topOffset = 30.dp,
            bottomOffset = 0.dp
        )
    }
}