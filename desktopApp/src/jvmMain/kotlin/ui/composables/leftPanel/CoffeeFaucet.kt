package ui.composables.leftPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import themes.CoffeeSlotTheme
import ui.composables.Panel

@Composable
@Preview
fun CoffeeFauler(width: Dp) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colors.primary,
            MaterialTheme.colors.primaryVariant
        )
    )

    val height = width.value / 3
    Box(Modifier, contentAlignment = Alignment.Center) {
        Box(Modifier.padding(top = height.dp)) {
            Panel(
                Modifier
                    .width(width / 3)
                    .height(width / 4),
                brush = gradient,
                bottomOffset = width / 10,
                convexTop = false
            )
        }
        Panel(
            Modifier
                .width(width)
                .height(height.dp),
            brush = gradient,
            bottomOffset = width / 10,
            convexTop = false
        )
    }
}

@Composable
@Preview
fun CoffeeFaulerPreview() {
    CoffeeSlotTheme {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CoffeeFauler(250.dp)
        }
    }
}

