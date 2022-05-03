package com.expressus.android.presentation.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.Panel

@Composable
@Preview
private fun PanelPreview() {
    val gradient = Brush.horizontalGradient(colors = listOf(Color.Red, Color.Yellow))
    Column(Modifier.fillMaxSize().background(Color.Black).padding(10.dp)) {
        Panel(
            Modifier
                .width(400.dp)
                .height(400.dp)
                .border(2.dp, Color.Red),
            brush = gradient,
            topOffset = 20.dp
        )
        Spacer(Modifier.size(30.dp))
        Panel(
            Modifier
                .width(400.dp)
                .height(400.dp)
                .border(2.dp, Color.Red),
            brush = gradient,
            topOffset = 20.dp,
            convexTop = false,
            convexBottom = false
        )
    }
}