package ui.composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Slot(modifier: Modifier, strokeWidth: Dp, top: Color, end: Color, bottom: Color, start: Color) {
    Canvas(modifier = modifier) {
        drawPath(
            brush = SolidColor(top),
            path = Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width, 0f)
                lineTo(size.width - strokeWidth.value / 2, strokeWidth.value / 2)
                lineTo(strokeWidth.value / 2, strokeWidth.value / 2)
                lineTo(0f, 0f)
                close()
            })

        drawPath(
            brush = SolidColor(end),
            path = Path().apply {
                moveTo(size.width, 0f)
                lineTo(size.width, size.height)
                lineTo(size.width - strokeWidth.value / 2, size.height - strokeWidth.value / 2)
                lineTo(size.width - strokeWidth.value / 2, strokeWidth.value / 2)
                lineTo(size.width, 0f)
                close()
            }
        )

        drawPath(
            brush = SolidColor(bottom),
            path = Path().apply {
                moveTo(size.width, size.height)
                lineTo(0f, size.height)
                lineTo(strokeWidth.value / 2, size.height - strokeWidth.value / 2)
                lineTo(size.width - strokeWidth.value / 2, size.height - strokeWidth.value / 2)
                lineTo(size.width, size.height)
                close()
            }
        )

        drawPath(
            brush = SolidColor(start),
            path = Path().apply {
                moveTo(0f, 0f)
                lineTo(strokeWidth.value / 2, strokeWidth.value / 2)
                lineTo(strokeWidth.value / 2, size.height - strokeWidth.value / 2)
                lineTo(0f, size.height)
                lineTo(0f, 0f)
                close()
            }
        )
    }
}

@Composable
@Preview
private fun SlotPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Slot(
            modifier = Modifier
                .size(400.dp)
                .background(Color.Yellow),
            strokeWidth = 50.dp,
            start = Color.Black,
            end = Color.Black,
            top = Color.Black,
            bottom = Color.DarkGray
        )
    }
}