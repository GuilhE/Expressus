package com.expressus.android.presentation.previews.leftPanel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.leftPanel.CoffeeSlot

@Composable
@Preview
private fun CoffeeSlotPreview() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CoffeeSlot(350.dp, 100.dp, 100.dp, DpSize(15.dp, 150.dp))
    }
}