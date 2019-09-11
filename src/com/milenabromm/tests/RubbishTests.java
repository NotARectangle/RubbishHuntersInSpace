package com.milenabromm.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.milenabromm.game.Model;
import com.milenabromm.game.PlayerShip;
import com.milenabromm.game.Rubbish;

/**
 * Test Set No: 3
 * @author Milena Bromm
 * @version 2
 */
class RubbishTests {

	/**
	 * Test No: 3.1
	 * Test if rubbish x and y values change to player position x + 10 & y + 85
	 * After player has collected rubbish
	 */
	@Test
	void testMove1() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		float playerX = player.getX();
		float playerY = player.getY();
		Rubbish rubbish = model.getRubbish().get(0);
		rubbish.setCollected();	
		rubbish.move(player);
		
		assertTrue(rubbish.getX() == playerX+10 && rubbish.getY() == playerY + 85);
		}//end testMove1
		
	
	/**
	 * Test No: 3.2
	 * Test 2: Test if rubbish y moves for 1 if not collected yet
	 */
	@Test
	void testMove2() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		Rubbish rubbish = model.getRubbish().get(0);
		float rubbishY = rubbish.getY();
		float rubbishX = rubbish.getX();

		rubbish.move(player);
		
		assertTrue(rubbish.getX() == rubbishX && rubbish.getY() == rubbishY + 1f);
		}//end testMove2

}//end rubbishTests
