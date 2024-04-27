package com.expressus.android.presentation.previews.rightPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.expressus.compose.components.rightPanel.CoffeeSelectors
import com.expressus.compose.themes.MachineTheme

@Composable
@Preview
fun CoffeeTypeListPreview() {
    MachineTheme {
        Column(Modifier.background(MaterialTheme.colorScheme.background)) {
            CoffeeSelectors(Modifier, 2) {}
        }
    }
}