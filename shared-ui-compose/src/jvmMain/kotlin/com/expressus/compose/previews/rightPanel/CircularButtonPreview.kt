package com.expressus.compose.previews.rightPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.rightPanel.CircularButton
import com.expressus.compose.themes.CoffeeSelectorsTheme

@Composable
@Preview
private fun CircularButtonPreview() {
    CoffeeSelectorsTheme {
        CircularButton(25.dp) {}
    }
}