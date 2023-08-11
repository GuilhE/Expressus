package com.expressus.compose.components.rightPanel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.expressus.compose.themes.CoffeeSelectorsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Composable
fun CoffeeSelectors(modifier: Modifier, count: Int, isMakingCoffee: Boolean = false, onClick: (Int) -> Unit) {
    CoffeeSelectors(modifier, List(count) { "" }, isMakingCoffee, onClick)
}

@Composable
fun CoffeeSelectors(modifier: Modifier, options: List<String>, isMakingCoffee: Boolean = false, onClick: (Int) -> Unit) {
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
            if (animateIndex == options.size) {
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
        for (i in options.indices) {
            item {
                CoffeeTile(options[i], !isMakingCoffee && animateIndex == i, if (toggle) i % 2 != 0 else i % 2 == 0) { onClick(i) }
            }
        }
    }
}

@Composable
private fun CoffeeTile(title: String, animateIdle: Boolean = false, toggle: Boolean = false, onClick: () -> Unit) {
    CoffeeSelectorsTheme {
        Row(
            Modifier
                .fillMaxWidth()
                .height(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .fillMaxSize(0.8f)
                    .fillMaxHeight()
            ) {
                PlasticTile(
                    Modifier.fillMaxSize(),
                    withGlossy = false,
                    backgroundColor =
                    if (animateIdle) MaterialTheme.colors.primaryVariant else if (toggle) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
                )
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 15.dp),
                    text = title,
                    textAlign = TextAlign.Right,
                    color = MaterialTheme.colors.surface,
                    style = MaterialTheme.typography.h6
                )
            }
            Spacer(Modifier.size(10.dp))
            CircularButton(25.dp) { onClick() }
        }
    }
}