package utils

import java.io.File
import javax.sound.sampled.AudioSystem

object SoundPlayer {

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

    fun playBrewingSound() {
        playSound(File("src/jvmMain/resources/brewing.wav"))
    }

    fun playPouringSound() {
        playSound(File("src/jvmMain/resources/pouring.wav"))
    }
}