package src.main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
  Clip clip;
  URL soundURL[] = new URL[30]; // Uniform Resource Locator, Use this to store different sounds

  public Sound() {
    // Initialize sound objects at array positions
    soundURL[0] = getClass().getResource("/res/sound/FootSteps.wav");
    soundURL[1] = getClass().getResource("/res/sound/Error.wav");
    soundURL[2] = getClass().getResource("/res/sound/Key.wav");
    soundURL[3] = getClass().getResource("/res/sound/DoorOpen.wav");
    soundURL[4] = getClass().getResource("/res/sound/EndLevel.wav");
    soundURL[5] = getClass().getResource("/res/sound/MenuSong.wav");
  }

  public void setFile(int i) {
    // Import sound clips
    try {
      AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
      clip = AudioSystem.getClip();
      clip.open(ais);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }

  public void play() {
    clip.start();
  }

  public void loop() {
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }

  public void stop() {
    clip.stop();
  }
}
