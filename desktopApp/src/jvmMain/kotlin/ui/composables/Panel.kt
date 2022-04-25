package ui.composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
                        x1 = 0f,
                        y1 = if (convexTop) topOffset.value else 0f,
                        x2 = size.width / 2,
                        y2 = if (convexTop) -topOffset.value else topOffset.value,
                        x3 = size.width,
                        y3 = if (convexTop) topOffset.value else 0f
                    )
                    lineTo(size.width, size.height - if (convexBottom) bottomOffset.value else 0f)
                    cubicTo(
                        x1 = size.width,
                        y1 = size.height - if (convexBottom) bottomOffset.value else 0f,
                        x2 = size.width / 2 + size.width / 16f,
                        y2 = size.height + if (convexBottom) bottomOffset.value else -bottomOffset.value,
                        x3 = 0f,
                        y3 = size.height - if (convexBottom) bottomOffset.value else 0f
                    )
                    close()
                }
            )
            drawContent()
        }
    ) { content() }
}

@Composable
@Preview
private fun PanelPreview() {
    val gradient = Brush.horizontalGradient(colors = listOf(Color.Red, Color.Yellow))
    Column(Modifier.fillMaxSize().background(Color.Black).padding(10.dp)) {
        Panel(
            Modifier
                .width(400.dp)
                .height(400.dp)
                .border(2.dp, Color.Red),
            brush = gradient,
            topOffset = 20.dp
        )
        Spacer(Modifier.size(30.dp))
        Panel(
            Modifier
                .width(400.dp)
                .height(400.dp)
                .border(2.dp, Color.Red),
            brush = gradient,
            topOffset = 20.dp,
            convexTop = false,
            convexBottom = false
        )
    }
}