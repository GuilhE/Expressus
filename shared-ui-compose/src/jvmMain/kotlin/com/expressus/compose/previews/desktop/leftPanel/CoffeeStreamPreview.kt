package com.expressus.compose.previews.desktop.leftPanel

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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