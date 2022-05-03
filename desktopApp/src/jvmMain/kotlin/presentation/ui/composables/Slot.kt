package ui.composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Slot(
    width: Dp,
    height: Dp = width,
    strokeWidth: Dp,
    topOffset: Dp = 0.dp,
    bottomOffset: Dp = topOffset,
    convexTop: Boolean = true,
    flatTop: Boolean = false,
    convexBottom: Boolean = convexTop,
    flatBottom: Boolean = false,
    top: Color,
    end: Color,
    bottom: Color,
    start: Color,
    background: Brush? = null
) {
    Canvas(modifier = Modifier.size(width, height)) {
        background?.let {
            drawPath(
                brush = it,
                path = Path().apply {
                    moveTo(0f, if (convexTop) topOffset.value else 0f)
                    cubicTo(
                        x1 = 0f, y1 = if (convexTop) topOffset.value else 0f,
                        x2 = size.center.x, y2 = if (convexTop) -topOffset.value else topOffset.value,
                        x3 = size.width, y3 = if (convexTop) topOffset.value else 0f
                    )
                    lineTo(size.width, size.height - if (convexBottom) bottomOffset.value else 0f)
                    cubicTo(
                        x1 = size.width, y1 = size.height - if (convexBottom) bottomOffset.value else 0f,
                        x2 = size.center.x + size.width / 16f, y2 = size.height + if (convexBottom) bottomOffset.value else -bottomOffset.value,
                        x3 = 0f, y3 = size.height - if (convexBottom) bottomOffset.value else 0f
                    )
                }
            )
        }

        drawPath(
            brush = SolidColor(top),
            path = Path().apply {
                moveTo(0f, if (convexTop) topOffset.value else 0f)
                cubicTo(
                    x1 = 0f, y1 = if (convexTop) topOffset.value else 0f,
                    x2 = size.center.x, y2 = if (convexTop) -topOffset.value else topOffset.value,
                    x3 = size.width, y3 = if (convexTop) topOffset.value else 0f
                )
                lineTo(size.width - strokeWidth.value / 2, strokeWidth.value / 2 + if (convexTop) topOffset.value else 0f)
                if (flatTop) {
                    lineTo(strokeWidth.value / 2, strokeWidth.value / 2 + if (convexTop) topOffset.value else 0f)
                } else {
                    cubicTo(
                        x1 = size.width - strokeWidth.value / 2, y1 = strokeWidth.value / 2 + if (convexTop) topOffset.value else 0f,
                        x2 = size.center.x, y2 = strokeWidth.value / 2 - if (convexTop) topOffset.value else -topOffset.value,
                        x3 = strokeWidth.value / 2, y3 = strokeWidth.value / 2 + if (convexTop) topOffset.value else 0f
                    )
                }
            })

        drawPath(
            brush = SolidColor(end),
            path = Path().apply {
                moveTo(size.width, if (convexTop) topOffset.value else 0f)
                lineTo(size.width, size.height - if (convexBottom) bottomOffset.value else 0f)
                lineTo(size.width - strokeWidth.value / 2, size.height - strokeWidth.value / 2 - if (convexBottom) bottomOffset.value else 0f)
                lineTo(size.width - strokeWidth.value / 2, strokeWidth.value / 2 + if (convexTop) topOffset.value else 0f)
            }
        )

        drawPath(
            brush = SolidColor(bottom),
            path = Path().apply {
                moveTo(strokeWidth.value / 2, size.height - strokeWidth.value / 2 - if (convexBottom) bottomOffset.value else 0f)
                if (flatBottom) {
                    lineTo(size.width - strokeWidth.value / 2, y = size.height - strokeWidth.value / 2 - if (convexBottom) bottomOffset.value else 0f)
                } else {
                    cubicTo(
                        x1 = strokeWidth.value / 2,
                        y1 = size.height - strokeWidth.value / 2 - if (convexBottom) bottomOffset.value else 0f,
                        x2 = size.center.x,
                        y2 = size.height - strokeWidth.value / 2 - if (convexBottom) -bottomOffset.value else bottomOffset.value,
                        x3 = size.width - strokeWidth.value / 2,
                        y3 = size.height - strokeWidth.value / 2 - if (convexBottom) bottomOffset.value else 0f
                    )
                }
                lineTo(size.width, size.height - if (convexBottom) bottomOffset.value else 0f)
                cubicTo(
                    x1 = size.width, y1 = size.height - if (convexBottom) bottomOffset.value else 0f,
                    x2 = size.center.x, y2 = size.height - if (convexBottom) -bottomOffset.value else bottomOffset.value,
                    x3 = 0f, y3 = size.height - if (convexBottom) bottomOffset.value else 0f
                )
            }
        )

        drawPath(
            brush = SolidColor(start),
            path = Path().apply {
                moveTo(0f, if (convexTop) topOffset.value else 0f)
                lineTo(strokeWidth.value / 2, strokeWidth.value / 2 + if (convexTop) topOffset.value else 0f)
                lineTo(strokeWidth.value / 2, size.height - strokeWidth.value / 2 - if (convexBottom) bottomOffset.value else 0f)
                lineTo(0f, size.height - if (convexBottom) bottomOffset.value else 0f)
            }
        )
    }
}

@Composable
@Preview
private fun SlotPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Slot(
            width = 400.dp,
            strokeWidth = 50.dp,
            topOffset = 20.dp,
            convexTop = true,
            convexBottom = true,
            flatTop = true,
            flatBottom = true,
            start = Color.Black,
            end = Color.Black,
            top = Color.Red,
            bottom = Color.Red
        )
    }
}