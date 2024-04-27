package com.expressus.compose.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import expressus.shared_ui_compose.generated.resources.Res
import expressus.shared_ui_compose.generated.resources.led_panel_panels_station_on
import org.jetbrains.compose.resources.Font

private val ledFontFamily
    @Composable
    get() = FontFamily(Font(Res.font.led_panel_panels_station_on))

internal val machineTypography
    @Composable
    get() = Typography(
        displayLarge = MaterialTheme.typography.displayLarge.copy(fontFamily = ledFontFamily),
        displayMedium = MaterialTheme.typography.displayMedium.copy(fontFamily = ledFontFamily),
        displaySmall = MaterialTheme.typography.displaySmall.copy(fontFamily = ledFontFamily),
        headlineLarge = MaterialTheme.typography.headlineLarge.copy(fontFamily = ledFontFamily),
        headlineMedium = MaterialTheme.typography.headlineMedium.copy(fontFamily = ledFontFamily),
        headlineSmall = MaterialTheme.typography.headlineSmall.copy(fontFamily = ledFontFamily),
        titleLarge = MaterialTheme.typography.titleLarge.copy(fontFamily = ledFontFamily),
        titleMedium = MaterialTheme.typography.titleMedium.copy(fontFamily = ledFontFamily),
        titleSmall = MaterialTheme.typography.titleSmall.copy(fontFamily = ledFontFamily),
        bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = ledFontFamily),
        bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = ledFontFamily),
        bodySmall = MaterialTheme.typography.bodySmall.copy(fontFamily = ledFontFamily),
        labelLarge = MaterialTheme.typography.labelLarge.copy(fontFamily = ledFontFamily),
        labelMedium = MaterialTheme.typography.labelMedium.copy(fontFamily = ledFontFamily),
        labelSmall = MaterialTheme.typography.labelSmall.copy(fontFamily = ledFontFamily),
    )