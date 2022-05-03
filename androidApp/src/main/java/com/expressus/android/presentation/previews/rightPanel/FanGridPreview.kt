package com.expressus.android.presentation.previews.rightPanel

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.rightPanel.FanGrid

@Composable
@Preview
private fun FanGridPreview() {
    FanGrid(Modifier.width(120.dp))
}