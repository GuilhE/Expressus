package com.expressus.compose.previews.rightPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.rightPanel.CoffeeTile
import com.expressus.compose.components.rightPanel.PlasticTile
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