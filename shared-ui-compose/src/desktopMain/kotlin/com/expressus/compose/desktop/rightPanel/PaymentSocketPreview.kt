package com.expressus.compose.desktop.rightPanel

import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.expressus.compose.components.rightPanel.PaymentSocket

@Composable
@Preview
private fun PaymentSocketPreview() {
    PaymentSocket(Modifier.fillMaxWidth())
}
