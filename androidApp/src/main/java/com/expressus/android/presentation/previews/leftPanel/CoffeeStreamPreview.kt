package com.expressus.android.presentation.previews.leftPanel

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.leftPanel.CoffeeStream

@Composable
@Preview
private fun CoffeeStreamPreview() {
    var pouring: Boolean by remember { mutableStateOf(false) }
    BoxWithConstraints(
        Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button({ pouring = !pouring }) {
                Text("Animate")
            }
            CoffeeStream(
                Modifier
                    .width(this@BoxWithConstraints.maxWidth / 20)
                    .fillMaxHeight()
                    .padding(vertical = 15.dp)
                    .border(2.dp, Color.Red),
                speed = 2L,
                pouring = pouring
            )
        }
    }
}