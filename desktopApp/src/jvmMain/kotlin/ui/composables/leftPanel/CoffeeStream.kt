package ui.composables.leftPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import themes.CoffeeTheme

@Composable
fun CoffeeStream(width: Dp, height: Dp, paddingValues: PaddingValues = PaddingValues(0.dp), pouring: Boolean) {
    var finalHeight: Float by remember { mutableStateOf(0f) }
    var topLeft: Offset by remember { mutableStateOf(Offset(0f, 0f)) }
    var poured: Boolean by remember { mutableStateOf(false) }

    val speed = 10L
    val step = 10.dp.value

    LaunchedEffect(pouring) {
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

        Canvas(Modifier
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

@Composable
@Preview
private fun CoffeeStreamPreview() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CoffeeStream(5.dp, 100.dp, pouring = true)
    }
}