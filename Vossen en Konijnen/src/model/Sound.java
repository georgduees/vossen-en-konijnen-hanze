package model;


import java.applet.*;
import java.net.*;

/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 * @version 2012.11.13
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

