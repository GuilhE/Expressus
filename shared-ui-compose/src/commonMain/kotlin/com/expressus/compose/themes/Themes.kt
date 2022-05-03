package com.expressus.compose.themes

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MachineTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            primary = Color.Black,
            primaryVariant = Color.Black.copy(alpha = 0.7f),
            surface = Color.Black.copy(alpha = 0.9f),
            background = Color.Black,
            onBackground = whiteDirty,
        ),
        content = content
    )
}

@Composable
fun CoffeeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            primary = brown,
            secondary = brownDark,
            secondaryVariant = brownDark.copy(0.7f),
            background = Color.Black
        ),
        content = content
    )
}

@Composable
fun CoffeeSlotTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            primary = Color.Black,
            primaryVariant = Color.DarkGray,
            secondary = Color.DarkGray.copy(0.9f),
            secondaryVariant = Color.Gray,
        ),
        content = content
    )
}

@Composable
fun DisplayTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            background = greenDark,
            onBackground = greenLight
        ),
        typography = machineTypography,
        content = content
    )
}

@Composable
fun CoffeeSelectorsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            primary = redCandy.copy(0.5f),
            primaryVariant = yellow,
            secondary = redCandy,
            surface = whiteDirty,
            background = Color.DarkGray.copy(0.7f)
        ),
        content = content
    )
}

@Composable
fun GlossyOverlayTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            primary = Color.White,
            secondary = Color.Transparent
        ),
        content = content
    )
}

@Composable
fun PaymentSocketTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            primary = yellow,
            secondary = orange,
            background = Color.Black
        ),
        content = content
    )
}

@Composable
fun FanTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            primary = Color.Black,
        ),
        content = content
    )
}

@Composable
fun CupTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            primary = whiteDirty,
            secondary = Color.DarkGray,
            onPrimary = redCandy
        ),
        content = content
    )
}