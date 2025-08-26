package com.expressus.compose.desktop.rightPanel

import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.rightPanel.CoffeeTile
import com.expressus.compose.themes.CoffeeSelectorsTheme

@Composable
@Preview
private fun PlasticTilePreview() {
    CoffeeSelectorsTheme {
        Column {
            CoffeeTile(title = "Coffee A") {}
            Spacer(Modifier.size(10.dp))
            CoffeeTile(title = "Coffee B") {}
        }
    }
}