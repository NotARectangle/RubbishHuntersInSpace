package com.milenabromm.game;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Sound class makes clip from a path and includes methods to play the sound or quit playing the sound
 * @author Milena Bromm 555851
 * @version 2.1
 */
public class Sound {
	
	private Clip clip;
	private boolean on = true;
	
	/**
	 * Sound constructor, creates a clip from a file path
	 * @param path - path where the file is located
	 */
	public Sound(String path) {
		try {
			   File file = new File(path);
			   clip = AudioSystem.getClip();
			   clip.open(AudioSystem.getAudioInputStream(file));
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to create clip");
			}//end tryCatch
	}//end constructor
	
	/**
	 * sets the volume on or off
	 * @param soundOn - true or false, determines if sound is played
	 */
	public void setVolume(boolean soundOn) {
		this.on = soundOn;
	}

	/**
	 * Takes in a parameter to determine if clip should be played on loop or just once
	 * @param loop - true if should play on loop, false if not
	 */
	public void play(boolean loop) {
		if (on) {
		  try {
			//if loop is true, and clip is not already running, plays a loop of the clip
		   if (loop) {
			   if (!clip.isRunning())
				   clip.loop(Clip.LOOP_CONTINUOUSLY);  
		   }else {
			   //if clip is not running
			   if (!clip.isRunning()) {
				  // rewind to the beginning
		         clip.setFramePosition(0);
		         //start playing the clip
		         clip.start(); 
			   }//end if
		   }//end if
		  } catch (Exception e) {
			  e.printStackTrace();
			  System.out.println("Failed when trying to play clip");
		  }//end tryCatch
		}
		 }//end play
	
	/**
	 * if it is running quits playing the sound
	 */
	public void quit() {
		// Mute the clip
		if (clip.isRunning())
			clip.stop();
	}//end quit
	
	/**
	 * For testing purposes only. Returns if clip is running
	 * @return true or false
	 */
	public boolean isRunning() {
		return clip.isRunning();
	}//end isRunning
	
	
}//end Sound
