import java.io.File
import javax.sound.sampled.AudioSystem

object SoundPlayer {

    private val resources = File(System.getProperty("compose.application.resources.dir"))
    private val local = File("src/jvmMain/resources")

    private fun playSound(file: File) {
        try {
            val ais = AudioSystem.getAudioInputStream(file)
            val clip = AudioSystem.getClip()
            clip.open(ais)
            clip.stop()
            clip.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playGrindingSound() {
        with(local.resolve("grinding.wav")) {
            if (exists()) {
                playSound(this)
                return
            }
        }
        with(resources.resolve("grinding.wav")) {
            if (exists()) {
                playSound(this)
                return
            }
        }
        println("> playGrindingSound: file not found")
    }

    fun playPouringSound() {
        with(local.resolve("pouring.wav")) {
            if (exists()) {
                playSound(this)
                return
            }
        }
        with(resources.resolve("pouring.wav")) {
            if (exists()) {
                playSound(this)
                return
            }
        }
        println("> playPouringSound: file not found")
    }
}