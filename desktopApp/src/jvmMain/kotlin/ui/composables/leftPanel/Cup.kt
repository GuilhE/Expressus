package ui.composables.leftPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import themes.CupTheme

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
                    cubicTo(
                        x1 = bottomRight.x + bezierOffset2, y1 = bottomRight.y - bezierOffset,
                        x2 = bottomRight.x, y2 = bottomRight.y,
                        x3 = bottomRight.x - bezierOffset, y3 = bottomRight.y
                    )
                    lineTo(bottomRight.x - bezierOffset, bottomRight.y)

                    lineTo(bottomLeft.x + bezierOffset, bottomLeft.y)
                    cubicTo(
                        x1 = bottomLeft.x + bezierOffset, y1 = bottomLeft.y,
                        x2 = bottomLeft.x, y2 = bottomLeft.y,
                        x3 = bottomLeft.x - bezierOffset2, y3 = bottomRight.y - bezierOffset
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
                    cubicTo(
                        x1 = bottomLeft.x + bezierOffset, y1 = bottomLeft.y,
                        x2 = bottomLeft.x, y2 = bottomLeft.y,
                        x3 = bottomLeft.x - bezierOffset2, y3 = bottomRight.y - bezierOffset
                    )
                    lineTo(bottomLeft.x - bezierOffset2, bottomRight.y - bezierOffset)
                }
            )
        }
    }
}

@Composable
@Preview
private fun CupPreview() {
    Box(
        Modifier.fillMaxSize().background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Cup(50.dp)
    }
}