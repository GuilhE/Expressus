package com.expressus.compose.components.centerPanel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.expressus.compose.components.Panel
import com.expressus.compose.themes.MachineTheme

@Composable
fun MachineFrame(modifier: Modifier, content: @Composable ColumnScope.() -> Unit = {}) {
    MachineTheme {
        val gradient = Brush.horizontalGradient(
            colors = listOf(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.surface,
                MaterialTheme.colorScheme.surface,
                MaterialTheme.colorScheme.surface,
                MaterialTheme.colorScheme.primary,
            )
        )

        Panel(modifier = modifier, brush = gradient) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                content = content
            )
        }
    }
}