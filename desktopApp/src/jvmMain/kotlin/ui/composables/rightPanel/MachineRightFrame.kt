package ui.composables.rightPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import themes.MachineTheme

@Composable
fun MachineRightFrame(modifier: Modifier, content: @Composable ColumnScope.() -> Unit = {}) {
    MachineTheme {
        val brush = Brush.horizontalGradient(
            listOf(
                MaterialTheme.colors.primaryVariant,
                MaterialTheme.colors.surface,
                MaterialTheme.colors.primary,
            )
        )
        Column(
            modifier
//                .drawWithContent {
//                    //https://github.com/JetBrains/compose-jb/issues/2034#issue-1213265325
//                    drawContent()
//                    drawRect(
//                        brush = brush,
//                        size = size,
//                        blendMode = BlendMode.Darken
//                    )
//                }
                .background(brush = brush)
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content
        )
    }
}