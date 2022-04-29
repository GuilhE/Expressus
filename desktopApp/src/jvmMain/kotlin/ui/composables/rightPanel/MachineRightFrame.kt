package ui.composables.rightPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import themes.MachineTheme

@Composable
fun MachineRightFrame(modifier: Modifier, content: @Composable ColumnScope.() -> Unit = {}) {
    MachineTheme {
        val brush = Brush.horizontalGradient(
            0.0f to MaterialTheme.colors.primary,
            0.05f to MaterialTheme.colors.primaryVariant,
            0.5f to MaterialTheme.colors.surface,
            1.0f to MaterialTheme.colors.primary,
        )
        Box(modifier) {
            Column(
                Modifier
//                .drawWithContent {
//                    //https://github.com/JetBrains/compose-jb/issues/2034#issue-1213265325
//                    drawContent()
//                    drawRect(
//                        brush = brush,
//                        size = size,
//                        blendMode = BlendMode.Darken
//                    )
//                }
                    .background(brush)
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = content
            )
            //TODO when issue-1213265325 becomes fixed, remove this and the outer Box and uncomment the blendMode logic
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            0.0f to Color.Black.copy(0.85f),
                            0.13f to Color.Transparent,
                            0.5f to Color.Transparent,
                            1.0f to Color.Black.copy(0.85f)
                        )
                    )
            )
        }
    }
}