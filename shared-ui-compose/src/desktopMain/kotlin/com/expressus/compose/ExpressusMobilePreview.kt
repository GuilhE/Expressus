package com.expressus.compose

import androidx.compose.runtime.Composable
import com.expressus.compose.components.CoffeeSelectorsMobile
import com.expressus.compose.components.ExpressusMobile
import org.jetbrains.compose.ui.tooling.preview.Preview

//Only way to preview it for iOS for now
@Composable
@Preview
private fun ExpressusMobilePreview() {
    ExpressusMobile(isGrinding = false, isPouring = false, isMakingCoffee = false, status = "Stand by") {}
}

@Composable
@Preview
private fun CoffeeSelectorsPreview() {
    CoffeeSelectorsMobile(false, {}, {}, {})
}