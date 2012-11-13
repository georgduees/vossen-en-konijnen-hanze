package model;


import java.applet.*;
import java.net.*;

/**
 * Class that gets a sound file and enables it to play or stop the audio.
 * @author Tycho Marinus, Cor Sloot, Mark Visscher, Luuk Blom
 *
 */
public class Sound
{
  private AudioClip song; // Sound player
  
  /**
   * Constructor needs file location
   * @param filename
   */
  public Sound(URL filename)
  {
	  try
      {
   // songPath = new URL(filename); // Get the Sound URL
    song = Applet.newAudioClip(filename); // Load the Sound
      }
      catch(Exception e){} // Satisfy the catch
  }
    
  /**
   * Method to start playing a sound loop
   */
  public void playSound()
  {
      song.loop(); // Play
  }
  
  /**
   * Method to stop playing sound
   */
  public void stopSound()
  {
      song.stop(); // Stop
  }
  
  /**
   * Method to play sound just once.
   */
  public void playSoundOnce()
  {
      song.play(); // Play only once
  }
 
}

