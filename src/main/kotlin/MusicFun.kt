import java.io.File
import kotlinx.coroutines.*
import javax.sound.sampled.AudioSystem


suspend fun playBeats(beats: String, file: String) {
    val parts = beats.split("x")
    var count = 0
    for (part in parts) {
        count += part.length + 1
        if (part == "") {
            playSound(file)
        }
        else {
            delay (100 * (part.length + 1L))
            if (count < beats.length) {
                playSound(file)
            }
        }
    }
}
fun playSound (file: String){
    val clip = AudioSystem.getClip()
    val audioInputStream = AudioSystem.getAudioInputStream(
        File(
            file
        )
    )
    clip.open(audioInputStream)
    clip.start()
}
suspend fun main (){
   runBlocking { launch {  playBeats("x-", "toms.wav")}
   }
    playBeats("x-----x-----x", "crash_cymbal.wav")
}