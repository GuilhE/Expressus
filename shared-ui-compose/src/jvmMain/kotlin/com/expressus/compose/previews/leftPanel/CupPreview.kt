package com.expressus.compose.previews.leftPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.leftPanel.Cup

@Composable
@Preview
private fun CupPreview() {
    BoxWithConstraints(
        Modifier.fillMaxSize().background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        val cupHeight = remember(this@BoxWithConstraints.maxWidth) { this@BoxWithConstraints.maxWidth / 3 }
        val shadowHeight = remember(cupHeight) { (cupHeight + 5.dp) / 10 }

        //shadow
        Canvas(Modifier.size(cupHeight)) {
            drawOval(
                color = Color.Black.copy(0.2f),
                topLeft = Offset(0f, this.size.height - shadowHeight.value / 2),
                size = Size(size.width, shadowHeight.value)
            )
        }
        Cup(cupHeight)
    }
}