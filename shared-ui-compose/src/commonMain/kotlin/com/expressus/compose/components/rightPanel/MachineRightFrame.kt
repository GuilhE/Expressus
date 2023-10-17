package com.expressus.compose.components.rightPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.expressus.compose.themes.MachineTheme

@Composable
fun MachineRightFrame(modifier: Modifier, content: @Composable ColumnScope.() -> Unit = {}) {
    MachineTheme {
        Box(modifier) {
            Column(
                Modifier
                    .background(
                        Brush.horizontalGradient(
                            0.0f to MaterialTheme.colors.primary,
                            0.05f to MaterialTheme.colors.primaryVariant,
                            0.5f to MaterialTheme.colors.surface,
                            1.0f to MaterialTheme.colors.primary,
                        )
                    )
                    .padding(horizontal = 10.dp)
                    .drawWithContent {
                        drawContent()
                        drawRect(
                            brush = Brush.horizontalGradient(
                                0.0f to Color.Black.copy(0.85f),
                                0.09f to Color.Transparent,
                                0.5f to Color.Transparent,
                                1.0f to Color.Black.copy(0.7f)
                            ),
                            blendMode = BlendMode.Darken
                        )
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                content = content
            )
        }
    }
}