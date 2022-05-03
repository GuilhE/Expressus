package com.expressus.compose.components.leftPanel

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.expressus.compose.themes.CoffeeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Composable
fun CoffeeStream(width: Dp, height: Dp, paddingValues: PaddingValues = PaddingValues(0.dp), speed: Long = 10L, pouring: Boolean) {
    var finalHeight: Float by remember { mutableStateOf(0f) }
    var topLeft: Offset by remember { mutableStateOf(Offset(0f, 0f)) }
    var poured: Boolean by remember { mutableStateOf(false) }

    val step = 10.dp.value

    LaunchedEffect(pouring) {
        yield()
        if (pouring) {
            if (!poured) {
                while (finalHeight < height.value) {
                    finalHeight += step
                    delay(speed)
                }
                poured = true
            }
        } else {
            if (poured) {
                while (topLeft.y < height.value) {
                    topLeft += Offset(0f, step)
                    finalHeight -= step
                    delay(speed)
                }
            }
            finalHeight = 0f
            topLeft = Offset(0f, 0f)
            poured = false
        }
    }

    CoffeeTheme {
        val primary = MaterialTheme.colors.primary

        Canvas(
            Modifier
                .width(width)
                .height(height)
                .padding(paddingValues)
                .clipToBounds()
        ) {
            drawRoundRect(
                color = primary,
                size = Size(size.width, finalHeight),
                topLeft = topLeft,
                cornerRadius = CornerRadius(20f, 20f)
            )
        }
    }
}