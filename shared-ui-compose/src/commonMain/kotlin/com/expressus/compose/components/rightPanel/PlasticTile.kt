package com.expressus.compose.components.rightPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.Glossy

@Composable
fun PlasticTile(
    modifier: Modifier,
    shape: Shape = RoundedCornerShape(6.dp),
    backgroundColor: Color = Color.Transparent,
    withGlossy: Boolean = true
) {
    Box(
        modifier
            .graphicsLayer {
                this.shape = shape
                clip = true
            }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(backgroundColor)
        )
        if (withGlossy) {
            Glossy()
        }
    }
}