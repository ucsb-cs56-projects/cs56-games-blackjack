package edu.ucsb.cs56.projects.game.blackjack;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.net.*;

/** sound class, plays background music from menu bar
 *  @author John Lau
 *  @author Ryan Lorica
 *  @author Ryan Kirkpatrick
 *  @version 11/28/2017
 */
public class Sound {
    public Clip clip;

    public Sound(String fileName) {
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
        try {
                File file = new File(fileName);
                if (file.exists()) {
                        AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                        // load the sound into memory (a Clip)
                        clip = AudioSystem.getClip();
                        clip.open(sound);
                }
                else {
                        throw new RuntimeException("Sound: file not found: " + fileName);
                }
        }
        catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
                throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
                e.printStackTrace();
                throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }
    }

    public void play(){
        clip.setFramePosition(0); // Must always rewind!
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
