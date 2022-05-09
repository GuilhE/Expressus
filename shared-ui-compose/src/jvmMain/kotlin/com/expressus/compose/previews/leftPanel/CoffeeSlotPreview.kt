package com.expressus.compose.previews.leftPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.leftPanel.CoffeeSlot
import com.expressus.compose.components.leftPanel.FaucetOffsets

@Composable
@Preview
private fun CoffeeSlotPreview() {
    BoxWithConstraints(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CoffeeSlot(
            modifier = Modifier
                .aspectRatio(1f)
                .padding(10.dp),
            pouringSpeed = 10L,
            slotOffset = 10.dp,
            faucetOffsets = FaucetOffsets(10.dp, 10.dp)
        )
    }
}