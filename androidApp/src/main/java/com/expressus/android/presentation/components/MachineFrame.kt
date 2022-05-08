package com.expressus.android.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.expressus.compose.components.Panel
import com.expressus.compose.themes.MachineTheme

@Composable
fun MachineFrame(modifier: Modifier, content: @Composable ColumnScope.() -> Unit = {}) {
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

        Panel(modifier = modifier, brush = gradient) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                content = content
            )
        }
    }
}

@Composable
@Preview
private fun MachineFramePreview() {
    MachineFrame(modifier = Modifier.fillMaxSize()) {}
}