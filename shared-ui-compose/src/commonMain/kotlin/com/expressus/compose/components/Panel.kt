package com.expressus.compose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Panel(
    modifier: Modifier,
    brush: Brush,
    topOffset: Dp = 0.dp,
    bottomOffset: Dp = topOffset,
    convexTop: Boolean = true,
    convexBottom: Boolean = true,
    content: @Composable () -> Unit = {}
) {
    Box(modifier = modifier
        .drawWithContent {
            drawPath(
                brush = brush,
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
                    close()
                }
            )
            drawContent()
        }
    ) { content() }
}