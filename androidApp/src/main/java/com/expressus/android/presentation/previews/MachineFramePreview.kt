package com.expressus.android.presentation.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.expressus.compose.components.centerPanel.MachineFrame
@Composable
@Preview
private fun MachineFramePreview() {
    MachineFrame(modifier = Modifier.fillMaxSize()) {}
}