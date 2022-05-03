package com.expressus.android.presentation.previews.rightPanel

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                backgroundColor = MaterialTheme.colors.secondary,
            )
            Spacer(Modifier.size(10.dp))
            PlasticTile(
                Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                backgroundColor = MaterialTheme.colors.secondary,
                withGlossy = false
            )
        }
    }
}