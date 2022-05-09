package com.expressus.compose.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
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
    modifier: Modifier,
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
    Canvas(modifier = modifier) {
        background?.let {
            drawPath(
                brush = it,
                path = Path().apply {
                    moveTo(0f, if (convexTop) topOffset.value else 0f)
                    quadraticBezierTo(
                        x1 = size.center.x, y1 = if (convexTop) -topOffset.value else topOffset.value,
                        x2 = size.width, y2 = if (convexTop) topOffset.value else 0f
                    )
                    lineTo(size.width, size.height - if (convexBottom) bottomOffset.value else 0f)
                    quadraticBezierTo(
                        x1 = size.center.x, y1 = size.height + if (convexBottom) bottomOffset.value else -bottomOffset.value,
                        x2 = 0f, y2 = size.height - if (convexBottom) bottomOffset.value else 0f
                    )
                }
            )
        }

        drawPath(
            brush = SolidColor(start),
            path = Path().apply {
                moveTo(0f, if (convexTop) topOffset.value else 0f)
                lineTo(strokeWidth.value / 2, strokeWidth.value / 2 + if (convexTop) topOffset.value else 0f)
                lineTo(strokeWidth.value / 2, size.height - strokeWidth.value / 2 - if (convexBottom) bottomOffset.value else 0f)
                lineTo(0f, size.height - if (convexBottom) bottomOffset.value else 0f)
            }
        )

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
            brush = SolidColor(top),
            path = Path().apply {
                moveTo(0f, if (convexTop) topOffset.value else 0f)
                quadraticBezierTo(
                    x1 = size.center.x, y1 = if (convexTop) -topOffset.value else topOffset.value,
                    x2 = size.width, y2 = if (convexTop) topOffset.value else 0f
                )
                lineTo(size.width - strokeWidth.value / 2, strokeWidth.value / 2 + if (convexTop) topOffset.value else 0f)
                if (flatTop) {
                    lineTo(strokeWidth.value / 2, strokeWidth.value / 2 + if (convexTop) topOffset.value else 0f)
                } else {
                    quadraticBezierTo(
                        x1 = size.center.x, y1 = strokeWidth.value / 2 - if (convexTop) topOffset.value else -topOffset.value,
                        x2 = strokeWidth.value / 2, y2 = strokeWidth.value / 2 + if (convexTop) topOffset.value else 0f
                    )
                }
            })

        drawPath(
            brush = SolidColor(bottom),
            path = Path().apply {
                moveTo(strokeWidth.value / 2, size.height - strokeWidth.value / 2 - if (convexBottom) bottomOffset.value else 0f)
                if (flatBottom) {
                    lineTo(size.width - strokeWidth.value / 2, y = size.height - strokeWidth.value / 2 - if (convexBottom) bottomOffset.value else 0f)
                } else {
                    quadraticBezierTo(
                        x1 = size.center.x, y1 = size.height - strokeWidth.value / 2 - if (convexBottom) -bottomOffset.value else bottomOffset.value,
                        x2 = size.width - strokeWidth.value / 2, y2 = size.height - strokeWidth.value / 2 - if (convexBottom) bottomOffset.value else 0f
                    )
                }
                lineTo(size.width, size.height - if (convexBottom) bottomOffset.value else 0f)
                quadraticBezierTo(
                    x1 = size.center.x, y1 = size.height - if (convexBottom) -bottomOffset.value else bottomOffset.value,
                    x2 = 0f, y2 = size.height - if (convexBottom) bottomOffset.value else 0f
                )
            }
        )
    }
}