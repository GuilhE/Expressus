package com.expressus.compose.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.expressus.compose.components.CoffeeSelectorsMobile
import com.expressus.compose.components.ExpressusMobile

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