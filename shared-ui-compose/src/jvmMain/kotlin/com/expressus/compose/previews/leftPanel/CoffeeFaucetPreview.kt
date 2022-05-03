package com.expressus.compose.previews.leftPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.expressus.compose.themes.CoffeeSlotTheme
import com.expressus.compose.components.leftPanel.CoffeeFaucet

@Composable
@Preview
fun CoffeeFaucetPreview() {
    CoffeeSlotTheme {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CoffeeFaucet(250.dp)
        }
    }
}