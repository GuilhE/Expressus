import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
actual fun PlatformFont(name: String, weight: FontWeight, style: FontStyle): Font {
    return with(LocalContext.current) {
        Font(resources.getIdentifier(name, "font", packageName), weight, style)
    }
}