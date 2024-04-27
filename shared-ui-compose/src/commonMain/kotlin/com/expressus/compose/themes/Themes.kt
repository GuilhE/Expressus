package com.expressus.compose.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MachineTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = Color.Black,
            primaryContainer = Color.Black.copy(alpha = 0.7f),
            surface = Color.Black.copy(alpha = 0.9f),
            background = Color.Black,
            onBackground = ExpressusColorPallete.whiteDirty,
        ),
        content = content
    )
}

@Composable
fun CoffeeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = ExpressusColorPallete.brown,
            secondary = ExpressusColorPallete.brownDark,
            secondaryContainer = ExpressusColorPallete.brownDark.copy(0.7f),
            background = Color.Black
        ),
        content = content
    )
}

@Composable
fun CoffeeSlotTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = Color.Black,
            primaryContainer = Color.DarkGray,
            secondary = Color.DarkGray.copy(0.9f),
            secondaryContainer = Color.Gray,
        ),
        content = content
    )
}

@Composable
fun DisplayTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            background = ExpressusColorPallete.greenDark,
            onBackground = ExpressusColorPallete.greenLight
        ),
        typography = machineTypography,
        content = content
    )
}

@Composable
fun CoffeeSelectorsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = ExpressusColorPallete.redCandy.copy(0.5f),
            primaryContainer = ExpressusColorPallete.yellow,
            secondary = ExpressusColorPallete.redCandy,
            surface = ExpressusColorPallete.whiteDirty,
            background = Color.DarkGray.copy(0.7f)
        ),
        typography = machineTypography,
        content = content
    )
}

@Composable
fun GlossyOverlayTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = Color.White,
            secondary = Color.Transparent
        ),
        content = content
    )
}

@Composable
fun PaymentSocketTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = ExpressusColorPallete.yellow,
            secondary = ExpressusColorPallete.orange,
            background = Color.Black
        ),
        content = content
    )
}

@Composable
fun FanTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = Color.Black,
        ),
        content = content
    )
}

@Composable
fun CupTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = ExpressusColorPallete.whiteDirty,
            secondary = Color.DarkGray,
            onPrimary = ExpressusColorPallete.redCandy
        ),
        content = content
    )
}