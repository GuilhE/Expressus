package com.expressus.compose.components.leftPanel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.Panel
import com.expressus.compose.themes.MachineTheme

@Composable
fun MachineLeftFrame(modifier: Modifier, content: @Composable ColumnScope.() -> Unit = {}) {
    MachineTheme {
        val gradient = Brush.horizontalGradient(
            colors = listOf(
                MaterialTheme.colors.primary,
                MaterialTheme.colors.surface,
                MaterialTheme.colors.surface,
                MaterialTheme.colors.surface,
                MaterialTheme.colors.primary,
            )
        )

        Panel(modifier = modifier, brush = gradient, topOffset = 10.dp, bottomOffset = 0.dp) {
            Column(
                modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = content
            )
        }
    }
}