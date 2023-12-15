package snake;

//Authors: Steph Miles, Tu Tran, Nadya Konadu

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Handles the audio that is to be played within the Game class.
 */
public class SoundHandler {
    
    /**
     * Creates a new Audio clip with the given string path and plays it 10 times.
     * @param s file path as a string object
     */
    public static void runMusic (String s) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(s));

            //we want to make a new clip
            Clip clip = AudioSystem.getClip();

            //tell algorithm to open the clip
            clip.open(inputStream);

            //song loops 10 times!
            clip.loop(10);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
