package ui.composables.rightPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import themes.CoffeeSelectorsTheme
import themes.MachineTheme
import ui.composables.PlasticTile

@Composable
fun CoffeeSelectors(modifier: Modifier, count: Int, toggle: Boolean = false, onClick: () -> Unit) {
    LazyColumn(
        modifier,
        contentPadding = PaddingValues(4.dp)
    ) {
        for (i in 0 until count) {
            item {
                CoffeeTile(if (toggle) i % 2 != 0 else i % 2 == 0, onClick)
            }
        }
    }
}

@Composable
private fun CoffeeTile(toggle: Boolean = false, onClick: () -> Unit) {
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
                backgroundColor = if (toggle) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
            )
            Spacer(Modifier.size(10.dp))
            CircularButton(25.dp, onClick)
        }
    }
}

@Composable
@Preview
fun CoffeeTypeListPreview() {
    MachineTheme {
        Box(Modifier.background(MaterialTheme.colors.background)) {
            CoffeeSelectors(Modifier, 2) {}
        }
    }
}