package com.expressus.compose.components.leftPanel

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.Slot
import com.expressus.compose.themes.CoffeeSlotTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Composable
fun CoffeeSlot(
    size: Dp,
    faucetSize: Dp,
    cupSize: Dp,
    coffeeStreamSize: DpSize,
    coffeePouringSpeed: Long = 10L,
    isGrinding: Boolean = false,
    isPouring: Boolean = false
) {
    var cupVrtPadding: Dp by remember { mutableStateOf(0.dp) }
    var cupHrzPadding: Dp by remember { mutableStateOf(0.dp) }

    LaunchedEffect(isGrinding) {
        yield()
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
                CoffeeStream(coffeeStreamSize.width, coffeeStreamSize.height, PaddingValues(vertical = 15.dp), coffeePouringSpeed, isPouring)
                CoffeeFaucet(faucetSize)
            }
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp + cupVrtPadding, start = cupHrzPadding)
            ) {
                //shadow
                Canvas(Modifier.size(cupSize)) {
                    drawOval(
                        color = Color.Black.copy(0.2f),
                        topLeft = Offset(-2.5.dp.value, this.size.height - 2.dp.value),
                        size = Size(this.size.height + 5.dp.value, 5.dp.value)
                    )
                }
                Cup(cupSize)
            }

            //Overlay
            Slot(
                width = maxH,
                strokeWidth = 30.dp,
                topOffset = 10.dp,
                convexTop = false,
                convexBottom = true,
                flatBottom = true,
                top = Color.Transparent,
                bottom = Color.Transparent,
                start = MaterialTheme.colors.primary,
                end = MaterialTheme.colors.primary,
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