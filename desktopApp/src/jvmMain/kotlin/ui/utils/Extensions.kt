package ui.composables

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import java.io.File

/**
 * To load a image do be drawn in canvas
 * @param file File with image uri
 *
 * Example:
 * drawImage(
 *     image = imageFromFile(File("src/jvmMain/resources/icon.png")),
 *     dstOffset = IntOffset((size.width / 2 - 25 / 2).toInt(), (size.height / 2 - 25 / 2).toInt()),
 *     dstSize = IntSize(25, 25)
 * )
 *
 */
fun imageFromFile(file: File): ImageBitmap = org.jetbrains.skia.Image.makeFromEncoded(file.readBytes()).toComposeImageBitmap()
