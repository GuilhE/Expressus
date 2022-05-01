package ui.composables.leftPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import themes.CoffeeSlotTheme
import ui.composables.Slot

@Composable
fun CoffeeSlot(size: Dp, isGrinding: Boolean = false, isPouring: Boolean = false) {
    var cupVrtPadding: Dp by remember { mutableStateOf(0.dp) }
    var cupHrzPadding: Dp by remember { mutableStateOf(0.dp) }

    LaunchedEffect(isGrinding) {
        cupVrtPadding = 0.dp
        cupHrzPadding = 0.dp
        while (isGrinding) {
            cupVrtPadding = (-1..1).random().dp
            cupHrzPadding = (0..1).random().dp
            delay(50)
        }
    }

    CoffeeSlotTheme {
        BoxWithConstraints(
            Modifier.size(size),
            contentAlignment = Alignment.Center
        ) {
            val maxH = maxHeight
            Slot(
                width = maxH,
                strokeWidth = 30.dp,
                topOffset = 10.dp,
                convexTop = false,
                convexBottom = true,
                flatBottom = true,
                top = MaterialTheme.colors.primary,
                start = MaterialTheme.colors.primary,
                end = MaterialTheme.colors.primary,
                bottom = MaterialTheme.colors.primaryVariant,
                background = Brush.verticalGradient(listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary))
            )
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                CoffeeStream(5.dp, maxH, PaddingValues(vertical = 15.dp), isPouring)
                CoffeeFaucet(40.dp)
            }
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp + cupVrtPadding, start = cupHrzPadding)
            ) { Cup(50.dp) }

            Slot(
                width = maxH,
                strokeWidth = 30.dp,
                topOffset = 10.dp,
                convexTop = false,
                convexBottom = true,
                flatBottom = true,
                top = MaterialTheme.colors.primary,
                start = MaterialTheme.colors.primary,
                end = MaterialTheme.colors.primary,
                bottom = Color.Transparent,
                background = Brush.horizontalGradient(
                    0.0f to Color.Black.copy(0.85f),
                    0.4f to Color.Transparent,
                    0.5f to Color.Transparent,
                    1.0f to Color.Black.copy(0.85f)
                )
            )
        }
    }
}

@Composable
@Preview
private fun CoffeeSlotPreview() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CoffeeSlot(150.dp)
    }
}