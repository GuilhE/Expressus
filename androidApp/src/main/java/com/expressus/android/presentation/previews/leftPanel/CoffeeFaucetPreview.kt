package com.expressus.android.presentation.previews.leftPanel

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.leftPanel.CoffeeFaucet
import com.expressus.compose.components.leftPanel.FaucetOffsets
import com.expressus.compose.themes.CoffeeSlotTheme

@Composable
@Preview
fun CoffeeFaucetPreview() {
    CoffeeSlotTheme {
        BoxWithConstraints(contentAlignment = Alignment.Center) {
            CoffeeFaucet(Modifier.fillMaxWidth(), FaucetOffsets(50.dp, 50.dp))
        }
    }
}