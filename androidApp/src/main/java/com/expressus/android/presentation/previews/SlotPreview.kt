package com.expressus.android.presentation.previews

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.Slot

@Composable
@Preview
private fun SlotPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Slot(
            modifier = Modifier.size(400.dp),
            strokeWidth = 50.dp,
            topOffset = 20.dp,
            convexTop = true,
            convexBottom = true,
            flatTop = true,
            flatBottom = true,
            start = Color.Black,
            end = Color.Black,
            top = Color.Red,
            bottom = Color.Red
        )
    }
}