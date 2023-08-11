package com.expressus.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
expect fun platformFont(resourceId: String, weight: FontWeight, style: FontStyle): Font