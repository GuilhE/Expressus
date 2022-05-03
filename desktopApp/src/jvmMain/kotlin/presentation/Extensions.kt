package presentation.ui.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import java.io.File

/**
 * To load a image do be drawn in canvas
 *
 * Example:
 * drawImage(
 *     image = File("src/jvmMain/resources/icon.png").toImageBitmap(),
 *     dstOffset = IntOffset((size.width / 2 - 25 / 2).toInt(), (size.height / 2 - 25 / 2).toInt()),
 *     dstSize = IntSize(25, 25)
 * )
 *
 */
fun File.toImageBitmap(): ImageBitmap = org.jetbrains.skia.Image.makeFromEncoded(readBytes()).toComposeImageBitmap()
