package com.expressus.compose.components.leftPanel

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.expressus.compose.themes.CoffeeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Composable
fun CoffeeStream(modifier: Modifier, speed: Long, pouring: Boolean) {
    var maxHeight: Int by remember { mutableStateOf(0) }
    var finalHeight: Float by remember { mutableStateOf(0f) }
    var topLeft: Offset by remember { mutableStateOf(Offset(0f, 0f)) }
    var poured: Boolean by remember { mutableStateOf(false) }
    val step: Float = remember { 10.dp.value }

    Box(modifier.onGloballyPositioned { maxHeight = it.size.height }) {
        LaunchedEffect(pouring, maxHeight) {
            yield()
            if (pouring) {
                if (!poured) {
                    while (finalHeight < maxHeight) {
                        finalHeight += step
                        delay(speed)
                    }
                    poured = true
                }
            } else {
                if (poured) {
                    while (topLeft.y < maxHeight) {
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
            Canvas(Modifier.fillMaxSize()) {
                drawRoundRect(
                    color = primary,
                    size = Size(size.width, finalHeight),
                    topLeft = topLeft,
                    cornerRadius = CornerRadius(20f, 20f)
                )
            }
        }
    }
}