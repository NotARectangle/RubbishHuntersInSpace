package com.milenabromm.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.milenabromm.game.HighScore;

/**
 * Test Set No: 8
 * @author Milena Bromm
 * @version 2
 */
class HighScoreTests {

/**
 * Test No: 8.1
 * Test writeScore by adding a new score. Score will always be passed in by the checkpoint which is why 
 * writing different test-cases with different values for the int value should not be very helpful, since it is always accessed 
 * through the model. This first will just test if a new score is added at the top of the document
 */
	@Test
	void testWriteScore1() {
		HighScore highScore = new HighScore();
		//The value for int should not matter for this test since the if statement to check if
		//the score is higher than high score is not in this class
		highScore.writeScore("Lisa", 0);
		highScore.writeScore("Magda", 2);
		String firstName = "Melissa";
		int score = 10;
		highScore.writeScore(firstName, score);
		
		assertEquals("Name: " + firstName + " Score: " + score, highScore.firstLine());
	}//end testWriteScore
	
	/**
	 * Test No: 8.2
	 * Test if writeScore accepts an empty string value as name, since name is taken from user input
	 * Maybe should add a check to view, for input to be "Anonymous" if no user input is added to input string
	 */
	@Test
	void testWriteScore2() {
		HighScore highScore = new HighScore();
		//The value for int should not matter for this test since the if statement to check if
		//the score is higher than high score is not in this class
		highScore.writeScore("Lisa", 0);
		highScore.writeScore("Magda", 2);
		String firstName = "";
		int score = 10;
		highScore.writeScore(firstName, score);
		
		assertEquals("Name: " + firstName + " Score: " + score, highScore.firstLine());
	}//end testWriteScore2

	/**
	 * Test No: 8.3
	 * Test getHighScore before high score file has been initialised, File must be deleted if it exists to run this test
	 * tests if getHighScore returns 0 when file is still empty
	 */
	@Test
	void testGetScore1() {
		HighScore highScore = new HighScore();
		highScore.resetHighScore();
		
		assertEquals("0", highScore.getHighScore());
	}//end testGetScore
	
	/**
	 * Test No: 8.4
	 * Tests if high score value is extracted properly
	 */
	@Test
	void testGetScore2() {
		HighScore highScore = new HighScore();
		highScore.resetHighScore();
		highScore.writeScore("Lisa", 0);
		highScore.writeScore("Magda", 2);
		
		assertEquals("2", highScore.getHighScore());
	}//end testGetScore

}//end HighScoreTests
