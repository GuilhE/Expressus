package ui.composables.leftPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import themes.CoffeeSlotTheme
import ui.composables.Slot

@Composable
fun CoffeeSlot(modifier: Modifier, isBrewing: Boolean = false, isPouring: Boolean = false) {
    CoffeeSlotTheme {
        Box(contentAlignment = Alignment.Center) {
            Slot(
                modifier.background(
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
                Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp)
            ) {
                CoffeeFauler(40.dp)
            }
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp)
            ) {
                Cup(50.dp)
            }
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