import java.net.URL
import javax.sound.sampled.AudioSystem

object SoundPlayer {

    private fun playSound(url: URL) {
        try {
            val ais = AudioSystem.getAudioInputStream(url)
            val clip = AudioSystem.getClip()
            clip.open(ais)
            clip.stop()
            clip.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playGrindingSound() {
        javaClass.getResource("grinding.wav")?.let { playSound(it) }
    }

    fun playPouringSound() {
        javaClass.getResource("pouring.wav")?.let { playSound(it) }
    }
}