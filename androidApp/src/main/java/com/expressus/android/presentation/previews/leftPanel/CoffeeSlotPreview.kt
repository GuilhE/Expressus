package com.expressus.android.presentation.previews.leftPanel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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