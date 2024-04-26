package com.expressus.compose.desktop.rightPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.rightPanel.FanGrid

@Composable
@Preview
private fun FanGridPreview() {
    FanGrid(Modifier.width(120.dp))
}