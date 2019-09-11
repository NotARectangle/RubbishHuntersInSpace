package com.milenabromm.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.milenabromm.game.Image;
import com.milenabromm.game.Model;

/**
 * Test Set No: 9
 * @author Milena Bromm 555851
 * @version 2
 */
class ImageTest {

	/**
	 * Test No: 9.1
	 * test if movement of image on y axis changes by calling move.
	 */
	@Test
	void testMove() {
		Model model = new Model();
		Image img = model.getMenuBtn();
		
		float y = img.getY();
		float x = img.getX();
		
		assertTrue(x == img.getX() && y < img.getBottomY());
	}//end testMove

}//end ImageTest
