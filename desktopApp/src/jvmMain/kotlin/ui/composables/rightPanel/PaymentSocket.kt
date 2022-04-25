package ui.composables.rightPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import themes.PaymentSocketTheme
import ui.composables.PlasticTile
import ui.composables.Slot

@Composable
fun PaymentSocket(padding: PaddingValues = PaddingValues(0.dp)) {
    PaymentSocketTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PlasticTile(
                Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .graphicsLayer {
                        shape = RoundedCornerShape(4.dp)
                        clip = true
                    }
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                MaterialTheme.colors.secondary,
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.secondary,
                                MaterialTheme.colors.secondary
                            )
                        )
                    ),
                shape = RoundedCornerShape(4.dp),
                withGlossy = false
            )
            Spacer(Modifier.size(6.dp))
            Slot(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .background(Color.Black),
                strokeWidth = 6.dp,
                top = Color.DarkGray,
                start= Color.DarkGray,
                end = Color.DarkGray,
                bottom = Color.Gray
            )
        }
    }
}

@Composable
@Preview
private fun PaymentSocketPreview() {
    PaymentSocket()
}
