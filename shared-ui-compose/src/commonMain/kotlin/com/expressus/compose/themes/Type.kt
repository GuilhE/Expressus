package com.expressus.compose.themes

import PlatformFont
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val ledFontFamily
    @Composable
    get() = FontFamily(
        PlatformFont(
            name = "led",
            weight = FontWeight.W400,
            style = FontStyle.Normal
        )
    )

val machineTypography
    @Composable
    get() = Typography(
        h1 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 96.sp,
            letterSpacing = (-1.5).sp,
            fontFamily = ledFontFamily
        ),
        h2 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 60.sp,
            letterSpacing = (-0.5).sp,
            fontFamily = ledFontFamily
        ),
        h3 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
            letterSpacing = 0.sp,
            fontFamily = ledFontFamily
        ),
        h4 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            letterSpacing = 0.25.sp,
            fontFamily = ledFontFamily
        ),
        h5 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            letterSpacing = 0.sp,
            fontFamily = ledFontFamily
        ),
        h6 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            letterSpacing = 0.15.sp,
            fontFamily = ledFontFamily
        ),
        subtitle1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.15.sp,
            fontFamily = ledFontFamily
        ),
        subtitle2 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.1.sp,
            fontFamily = ledFontFamily
        ),
        body1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
            fontFamily = ledFontFamily
        ),
        body2 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.25.sp,
            fontFamily = ledFontFamily
        ),
        button = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 1.25.sp,
            fontFamily = ledFontFamily
        ),
        caption = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            letterSpacing = 0.4.sp,
            fontFamily = ledFontFamily
        ),
        overline = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            letterSpacing = 1.5.sp,
            fontFamily = ledFontFamily
        )
    )