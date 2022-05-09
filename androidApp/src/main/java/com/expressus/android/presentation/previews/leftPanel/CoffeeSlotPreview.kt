package com.expressus.android.presentation.previews.leftPanel

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.leftPanel.CoffeeSlot
import com.expressus.compose.components.leftPanel.FaucetOffsets

@Composable
@Preview
private fun CoffeeSlotPreview() {
    var pouring: Boolean by remember { mutableStateOf(false) }
    var grinding: Boolean by remember { mutableStateOf(false) }

    BoxWithConstraints(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CoffeeSlot(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(10.dp),
                isGrinding = grinding,
                isPouring = pouring,
                pouringSpeed = 2L,
                slotOffset = 50.dp,
                faucetOffsets = FaucetOffsets(10.dp, 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button({ pouring = !pouring }) {
                    Text("Pour")
                }
                Button({ grinding = !grinding }) {
                    Text("Grind")
                }
            }
        }
    }
}