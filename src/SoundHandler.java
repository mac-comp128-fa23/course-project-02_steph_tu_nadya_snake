import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundHandler {
    
    public static void runMusic (String s) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(s));

            //we want to make a new clip
            Clip clip = AudioSystem.getClip();

            //tell algorithm to open the clip
            clip.open(inputStream);

            clip.loop(3);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
