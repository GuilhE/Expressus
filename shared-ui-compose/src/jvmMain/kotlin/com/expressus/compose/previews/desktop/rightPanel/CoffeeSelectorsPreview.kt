package com.expressus.compose.previews.desktop.rightPanel

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.expressus.compose.components.rightPanel.CoffeeSelectors
import com.expressus.compose.themes.MachineTheme

@Composable
@Preview
fun CoffeeTypeListPreview() {
    MachineTheme {
        Box(Modifier.background(MaterialTheme.colorScheme.background)) {
            CoffeeSelectors(Modifier, 2) {}
        }
    }
}