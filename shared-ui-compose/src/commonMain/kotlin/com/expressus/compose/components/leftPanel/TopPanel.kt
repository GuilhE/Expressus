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
fun TopPanel() {
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
            Modifier
                .fillMaxSize()
                .blur(0.5.dp)
                .padding(horizontal = 30.dp, vertical = 20.dp),
            brush = gradient,
            topOffset = 20.dp
        )
    }
}