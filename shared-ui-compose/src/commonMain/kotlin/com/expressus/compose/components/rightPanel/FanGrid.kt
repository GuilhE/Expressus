@file:OptIn(ExperimentalFoundationApi::class)

package com.expressus.compose.components.rightPanel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.expressus.compose.themes.FanTheme

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