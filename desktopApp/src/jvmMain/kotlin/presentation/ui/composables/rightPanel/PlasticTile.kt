package ui.composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import themes.CoffeeSelectorsTheme

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

@Composable
@Preview
private fun PlasticTilePreview() {
    CoffeeSelectorsTheme {
        Column {
            PlasticTile(
                Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                backgroundColor = MaterialTheme.colors.secondary,
            )
            Spacer(Modifier.size(10.dp))
            PlasticTile(
                Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                backgroundColor = MaterialTheme.colors.secondary,
                withGlossy = false
            )
        }
    }
}