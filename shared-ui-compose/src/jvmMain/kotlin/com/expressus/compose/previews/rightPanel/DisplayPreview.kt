package com.expressus.compose.previews.rightPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.expressus.compose.components.rightPanel.Display

@Composable
@Preview
private fun DisplayPreview() {
    Display(Modifier, "Stand by")
}