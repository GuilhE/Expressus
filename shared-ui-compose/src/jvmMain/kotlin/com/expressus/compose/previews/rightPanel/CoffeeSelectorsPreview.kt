package com.expressus.compose.previews.rightPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.expressus.compose.components.rightPanel.CoffeeSelectors
import com.expressus.compose.themes.MachineTheme

@Composable
@Preview
fun CoffeeTypeListPreview() {
    MachineTheme {
        Box(Modifier.background(MaterialTheme.colors.background)) {
            CoffeeSelectors(Modifier, 2) {}
        }
    }
}