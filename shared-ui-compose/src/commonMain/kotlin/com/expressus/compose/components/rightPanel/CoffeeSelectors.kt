package com.expressus.compose.components.rightPanel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import com.expressus.compose.themes.CoffeeSelectorsTheme

@Composable
fun CoffeeSelectors(modifier: Modifier, count: Int, isMakingCoffee: Boolean = false, onClick: () -> Unit) {
    var animateIndex by remember { mutableStateOf(-1) }
    var toggle by remember { mutableStateOf(false) }

    LaunchedEffect(isMakingCoffee) {
        yield()
        animateIndex = -1
        while (isMakingCoffee) {
            toggle = !toggle
            delay(250)
        }
        delay(1000)
        while (!isMakingCoffee) {
            if (animateIndex == count) {
                animateIndex = -1
                delay(1000)
            } else {
                animateIndex++
                delay(100)
            }
        }
    }

    LazyColumn(
        modifier.padding(start = 10.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        for (i in 0 until count) {
            item {
                CoffeeTile(!isMakingCoffee && animateIndex == i, if (toggle) i % 2 != 0 else i % 2 == 0, onClick)
            }
        }
    }
}

@Composable
private fun CoffeeTile(animateIdle: Boolean = false, toggle: Boolean = false, onClick: () -> Unit) {
    CoffeeSelectorsTheme {
        Row(
            Modifier
                .fillMaxWidth()
                .height(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlasticTile(
                Modifier
                    .fillMaxSize(0.8f)
                    .fillMaxHeight(),
                withGlossy = false,
                backgroundColor =
                if (animateIdle) MaterialTheme.colors.primaryVariant else if (toggle) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
            )
            Spacer(Modifier.size(10.dp))
            CircularButton(25.dp, onClick)
        }
    }
}