package com.expressus.compose.previews.leftPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.leftPanel.CoffeeStream

@Composable
@Preview
private fun CoffeeStreamPreview() {
    BoxWithConstraints(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CoffeeStream(
            Modifier
                .width(this@BoxWithConstraints.maxWidth / 20)
                .fillMaxHeight()
                .padding(vertical = 15.dp),
            speed = 10L,
            pouring = true
        )
    }
}