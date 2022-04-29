package ui.composables.leftPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
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
fun CoffeeSlot(modifier: Modifier, isGrinding: Boolean = false, isPouring: Boolean = false) {
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
        BoxWithConstraints(modifier, contentAlignment = Alignment.Center) {
            val maxH = maxHeight
            Slot(
                Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.secondary,
                                MaterialTheme.colors.secondaryVariant
                            )
                        )
                    ),
                strokeWidth = 30.dp,
                top = MaterialTheme.colors.primary,
                start = MaterialTheme.colors.primary,
                end = MaterialTheme.colors.primary,
                bottom = MaterialTheme.colors.primaryVariant
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
        }
    }
}

@Composable
@Preview
private fun CoffeeSlotPreview() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CoffeeSlot(Modifier.size(150.dp))
    }
}