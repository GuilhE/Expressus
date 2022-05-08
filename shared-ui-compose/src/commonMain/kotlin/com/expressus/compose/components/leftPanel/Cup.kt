package com.expressus.compose.components.leftPanel

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import com.expressus.compose.themes.CupTheme

@Composable
fun Cup(height: Dp) {
    CupTheme {
        val primary = MaterialTheme.colors.primary
        val secondary = MaterialTheme.colors.secondary
        val onPrimary = MaterialTheme.colors.onPrimary

        Canvas(Modifier.size(height)) {
            val w = size.width
            val h = size.height
            val borderHeight = h / 7
            val bodyOffset = borderHeight / 3
            val bezierOffset = h / 80
            val bezierOffset2 = h / 200

            val topLeft = Offset(bodyOffset, borderHeight)
            val topRight = Offset(w - bodyOffset, borderHeight)
            val bottomRight = Offset(w - borderHeight * 3 / 2, h)
            val bottomLeft = Offset(borderHeight * 3 / 2, h)

            drawRoundRect(color = primary, size = Size(size.width, borderHeight), cornerRadius = CornerRadius(20f, 20f))
            drawLine(color = secondary, alpha = 0.5f, start = topLeft, end = topRight)
            drawPath(
                color = primary,
                path = Path().apply {
                    moveTo(topLeft.x, topLeft.y)
                    lineTo(topRight.x, topRight.y)

                    lineTo(bottomRight.x + bezierOffset2, bottomRight.y - bezierOffset)
                    quadraticBezierTo(
                        x1 = bottomRight.x, y1 = bottomRight.y,
                        x2 = bottomRight.x - bezierOffset, y2 = bottomRight.y
                    )
                    lineTo(bottomRight.x - bezierOffset, bottomRight.y)

                    lineTo(bottomLeft.x + bezierOffset, bottomLeft.y)
                    quadraticBezierTo(
                        x1 = bottomLeft.x, y1 = bottomLeft.y,
                        x2 = bottomLeft.x - bezierOffset2, y2 = bottomRight.y - bezierOffset
                    )
                    lineTo(bottomLeft.x - bezierOffset2, bottomRight.y - bezierOffset)
                }
            )
            drawPath(
                color = onPrimary,
                path = Path().apply {
                    moveTo((bottomLeft.x + topLeft.x) / 2, (bottomLeft.y + topLeft.y) / 2)
                    lineTo(bottomRight.x - bezierOffset, bottomRight.y)
                    lineTo(bottomLeft.x + bezierOffset, bottomLeft.y)
                    quadraticBezierTo(
                        x1 = bottomLeft.x, y1 = bottomLeft.y,
                        x2 = bottomLeft.x - bezierOffset2, y2 = bottomRight.y - bezierOffset
                    )
                    lineTo(bottomLeft.x - bezierOffset2, bottomRight.y - bezierOffset)
                }
            )
        }
    }
}