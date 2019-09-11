package com.milenabromm.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.milenabromm.game.Missile;
import com.milenabromm.game.Model;
import com.milenabromm.game.PlayerShip;
import com.milenabromm.game.Rubbish;
import com.milenabromm.game.Ship;

/**
 * Test Set No: 5
 * @author Milena Bromm
 * @version 2
 */
class PlayerShipTests {

	
	/**
	 * Test No: 5.1
	 * Test inFrame leftborder x is 16 direction is left
	 * Seems to work in playtest as border is still there but in theory,
	 * could result in player displayed directly at frame edge  
	 */
	@Test
	void testInFrame1() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		float x = 16, y = 100, moveLeft = -15, displayX = 300;
		player.setX(x);
		player.setY(y);
		
		assertTrue(player.inFrame(moveLeft, displayX));
		
	}//end testInFrame1

	/**
	 * Test No: 5.2
	 * Test inFrame leftborder x is leftboarder direction is left
	 */
	@Test
	void testInFrame2() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		float x = 15, y = 100, moveLeft = -15, displayX = 300;
		player.setX(x);
		player.setY(y);
		
		assertFalse(player.inFrame(moveLeft, displayX));
		
	}//end testInFrame2
	
	/**
	 * Test No: 5.3
	 * Test inFrame right border x is rightboarder - 1 direction is right
	 */
	@Test
	void testInFrame3() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		float displayX = 300, x = displayX - 61, y = 100, moveRight = 15;
		player.setX(x);
		player.setY(y);
		
		assertTrue(player.inFrame(moveRight, displayX));
		
	}//end testInFrame3
	
	/**
	 * Test No: 5.4
	 * Test inFrame right border x is rightboarder direction is right
	 */
	@Test
	void testInFrame4() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		float displayX = 300, x = displayX - 60, y = 100, moveRight = 15;
		player.setX(x);
		player.setY(y);
		
		assertFalse(player.inFrame(moveRight, displayX));
		
	}//end testInFrame3
	
	/**
	 * Test No: 5.5
	 * Test move, check if player ship moves upwards if move is called once
	 */
	@Test
	void testMove1() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		float x = 60, y = 100;
		player.setX(x);
		player.setY(y);
		
		player.move();
		assertTrue(player.getY() == y - 0.4f);
	}//end testMove1
	
	/**
	 * Test No: 5.6
	 * Test move, check if player ship moves downwarts if move is called twice
	 */
	@Test
	void testMove2() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		float x = 60, y = 100;
		player.setX(x);
		player.setY(y);
		
		//moves up
		player.move();
		player.move();
		//moves back to initial y position
		player.move();
		player.move();
		assertTrue(player.getY() == y);
	}//end testMove1
	
	/**
	 * Test No: 5.7
	 * Test move with input parameters, move equals 1 results in move x value to left
	 */
	@Test
	void testMoveWithInput1() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		float x = 60, y = 100, displayX = 500, moveLeft = -15;
		player.setX(x);
		player.setY(y);
		player.move(1, displayX);
		
		assertTrue(player.getX() == x + moveLeft);
		
	}//end testMoveWithInput1
	
	/**
	 * Test No: 5.8
	 * Test move with input parameters, move equals 1 results in move x value to right
	 */
	@Test
	void testMoveWithInput2() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		float x = 60, y = 100, displayX = 500, moveRight = 15;
		player.setX(x);
		player.setY(y);
		player.move(2, displayX);
		
		assertTrue(player.getX() == x + moveRight);
		
	}//end testMoveWithInput2
	
	/**
	 * Test No: 5.9
	 * Test move with input parameters, move equals 3 results shooting missile
	 */
	@Test
	void testMoveWithInput3() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		float x = 60, y = 100, displayX = 500;
		player.setX(x);
		player.setY(y);
		player.move(3, displayX);
		
		//if missile is shoot missileS should be true
		assertTrue(player.getMissileS());
		
	}//end testMoveWithInput3
	
	/**
	 * Test No: 5.10
	 * Test moveMissile, tests if all missiles have been moved
	 */
	@Test
	void testMoveMissiles1() {
		Model model = new Model();
		ArrayList<Ship> ships = model.getEnemyShips();
		PlayerShip player = model.getPlayer();
		float x = 300, y = 100;
		ships.get(0).setX(x);
		ships.get(0).setY(y);
		ships.get(1).setX(x);
		ships.get(1).setY(y);
		player.setX(x);
		player.setY(y);
		player.setMissiles(new Missile(x, y, 5, 20));
		player.setMissiles(new Missile(x+10, y, 5, 20));
		player.setMissileS();
		player.moveMissiles(-10, ships);
		
		assertTrue(y-10 == player.getMissile(0).y && y-10 == player.getMissile(1).y);
	}//end testMoveMissiles1
	
	/**
	 * Test No: 5.11
	 * Test moveMissile, tests if health was deducted for the number of in range missiles to target ship (Ship ship)
	 */
	@Test
	void testMoveMissiles2() {
		Model model = new Model();
		ArrayList<Ship> ships = model.getEnemyShips();
		PlayerShip player = model.getPlayer();
		float x = 300, y = 100;
		ships.get(0).setX(x);
		ships.get(0).setY(y-10);
		ships.get(1).setX(x+100);
		ships.get(1).setY(y-10);
		int sHealth1 = ships.get(0).getHealth();
		int sHealth2 = ships.get(1).getHealth();
		player.setX(x);
		player.setY(y);
		player.setMissiles(new Missile(x, y, 5, 20));
		player.setMissiles(new Missile(x+100, y, 5, 20));
		player.setMissileS();
		player.moveMissiles(-10, ships);
		
		assertTrue(sHealth1 - 20 == ships.get(0).getHealth() && sHealth2 - 20 == ships.get(1).getHealth());
	}//end testMoveMissile2
	
	/**
	 * Test No: 5.12
	 * Test detect Rubbish: test if correct number of rubbish is set to collected
	 */
	@Test
	void testDetectRubbish() {
		Model model = new Model();
		ArrayList<Rubbish> rubbish = model.getRubbish();
		PlayerShip player = model.getPlayer();
		float x = 300, y = 40;
		
		player.setX(x);
		player.setY(y);
		rubbish.get(0).setX(x);
		rubbish.get(0).setY(y);
		rubbish.get(1).setX(x);
		rubbish.get(1).setY(y);
		
		player.detectRubbish(rubbish);
		
		int count = 0;
		for(Rubbish rbh: rubbish) {
			if(rbh.getCollected()) {
				count++;
			}//end if
		}//end for
		
		assertEquals(2, count);
	}//end detectRubbish
	
	/**
	 * Test No: 5.13
	 */
	@Test
	void testSetSoundOnOff() {
		Model model = new Model();
		Ship ship = model.getEnemyShips().get(0);
	}//test SetSoundOnOff
}
