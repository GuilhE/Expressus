package com.expressus.compose.previews.desktop.rightPanel

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.rightPanel.PlasticTile
import com.expressus.compose.themes.CoffeeSelectorsTheme

@Composable
@Preview
private fun PlasticTilePreview() {
    CoffeeSelectorsTheme {
        Column {
            PlasticTile(
                Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                backgroundColor = MaterialTheme.colorScheme.secondary,
            )
            Spacer(Modifier.size(10.dp))
            PlasticTile(
                Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                backgroundColor = MaterialTheme.colorScheme.secondary,
                withGlossy = false
            )
        }
    }
}