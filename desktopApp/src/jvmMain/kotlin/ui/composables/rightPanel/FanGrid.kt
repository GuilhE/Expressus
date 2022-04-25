@file:OptIn(ExperimentalFoundationApi::class)

package ui.composables.rightPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import themes.FanTheme

@Composable
fun FanGrid(modifier: Modifier) {
    FanTheme {
        LazyVerticalGrid(
            modifier = modifier,
            cells = GridCells.Fixed(3),
            contentPadding = PaddingValues(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (i in 0..50) {
                item {
                    Box(
                        Modifier
                            .height(4.dp)
                            .background(
                                color = MaterialTheme.colors.primary,
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {}
                }
            }
        }
    }
}

@Composable
@Preview
private fun FanGridPreview() {
    FanGrid(Modifier.width(120.dp))
}