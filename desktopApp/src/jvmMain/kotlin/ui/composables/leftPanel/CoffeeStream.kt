package ui.composables.leftPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import themes.CoffeeTheme

@Composable
fun CoffeeStream(width: Dp, height: Dp, pouring: Boolean) {
    var finalHeight: Float by remember { mutableStateOf(0f) }
    var topLeft: Offset by remember { mutableStateOf(Offset(0f, 0f)) }
    var poured: Boolean by remember { mutableStateOf(false) }

    LaunchedEffect(pouring) {
        if (pouring) {
            if (!poured) {
                while (finalHeight < height.value) {
                    finalHeight += 10.dp.value
                    delay(25)
                }
                poured = true
            }
        } else {
            if (poured) {
                while (topLeft.y < height.value) {
                    val step = 10.dp.value
                    topLeft += Offset(0f, step)
                    finalHeight -= step
                    delay(25)
                }
            }
            finalHeight = 0f
            topLeft = Offset(0f,0f)
            poured = false
        }
    }

    CoffeeTheme {
        val primary = MaterialTheme.colors.primary

        Canvas(Modifier.width(width).height(height)) {
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
        CoffeeStream(5.dp, 100.dp, true)
    }
}