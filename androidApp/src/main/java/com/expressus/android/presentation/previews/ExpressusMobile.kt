package com.expressus.android.presentation.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.expressus.compose.components.ExpressusMobile

//Note, to be able to Preview this composable we must add the font to resources. Otherwise use the JVM preview in shared-ui-module
@Composable
@Preview
private fun ExpressusMobilePreview() {
    ExpressusMobile(isGrinding = false, isPouring = false, isMakingCoffee = false, status = "Stand by") {}
}