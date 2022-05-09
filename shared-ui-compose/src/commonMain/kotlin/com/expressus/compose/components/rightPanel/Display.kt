package com.expressus.compose.components.rightPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.expressus.compose.themes.DisplayTheme

@Composable
fun Display(modifier: Modifier, text: String) {
    DisplayTheme {
        Box(
            modifier
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colors.onBackground,
                            MaterialTheme.colors.background,
                            MaterialTheme.colors.background,
                        )
                    ),
                    shape = RoundedCornerShape(6.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text.uppercase(),
                textAlign = TextAlign.Center, color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h5
            )
        }
    }
}