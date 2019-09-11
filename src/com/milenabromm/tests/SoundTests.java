package com.milenabromm.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.milenabromm.game.Sound;

/**
 * Test Set No: 7
 * @author Milena Bromm
 * @version 2
 */
class SoundTests {

/**
 * Test No: 7.1
 * Tests if sound is playing after clip length over, if loop value is true
 */
	@Test
	void testPlay1() {
	Sound sound = new Sound("src/com/milenabromm/sounds/money.wav");	
	sound.play(true);
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}//end tryCatch
	
	assertTrue(sound.isRunning());	
	
	sound.quit();
	}//end testPlay1
	
	/**
	 * Test No: 7.2
	 * Tests if sound stopped playing after clip length over, if loop value is false
	 */
		@Test
		void testPlay2() {
		Sound sound = new Sound("src/com/milenabromm/sounds/money.wav");	
		sound.play(false);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}//end tryCatch
		
		assertFalse(sound.isRunning());	
		}//end testPlay2
		
		/**
		 * Test No: 7.3
		 * Tests if quit stops playing looped song 
		 */
		@Test
		void testQuit1() {
		Sound sound = new Sound("src/com/milenabromm/sounds/money.wav");	
		sound.play(true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}//end tryCatch
		
		sound.quit();
			
		assertFalse(sound.isRunning());	

			}//end testQuit1
			
		/**
		 * Test No: 7.4
		 * Tests if song is still not running after quit is called on song that has not been played yet
		*/
		@Test
		void testQuit2() {
		Sound sound = new Sound("src/com/milenabromm/sounds/money.wav");	
		sound.quit();
				
		assertFalse(sound.isRunning());	

		}//end testQuit2
		
		/**
		 * Test No: 7.5
		 * Test if clip has stopped playing after sound has been turned off.
		 */
		@Test
		void testSetVolume1() {
			Sound sound = new Sound("src/com/milenabromm/sounds/gameMusic.wav");
			sound.setVolume(false);
			sound.play(true);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//end tryCatch
			
			assertFalse(sound.isRunning());	
		}//end testSetVolume1

		/**
		 * Test No: 7.6
		 * Test if clip is playing after sound has been turned first off and then on.
		 */
		@Test
		void testSetVolume2() {
			Sound sound = new Sound("src/com/milenabromm/sounds/gameMusic.wav");
			sound.setVolume(false);
			sound.play(true);
			sound.setVolume(true);
			sound.play(true);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//end tryCatch
			
			assertTrue(sound.isRunning());	
		}//end testSetVolume2
		
}//end SoundTests
