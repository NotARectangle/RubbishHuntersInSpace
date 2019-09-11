package com.milenabromm.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.milenabromm.game.Missile;
import com.milenabromm.game.Model;
import com.milenabromm.game.PlayerShip;

/**
 * Test Set No: 6
 * @author Milena Bromm
 * @version 2.1
 */
class MissileTests {

/**
 * Test No: 6.1
 * Test move method1: pass if x is not changing
 */
	@Test
	void testMove1() {
		float x = 3;
		float y = 10;
		Missile missile = new Missile(x, y, 10, 10);
		missile.move(3);
		
		assertEquals(x, missile.x);
	}//end testMove1

	/**
	 * Test No: 6.2
	 * Test move method 2: pass if y is increased as per positive input
	 */
	@Test
	void testMove2() {
		float x = 3;
		float y = 10;
		Missile missile = new Missile(x, y, 10, 10);
		missile.move(3);
		
		assertEquals(y + 3, missile.y);
	}//end testMove2
	
	
	/**
	 * Test No: 6.3
	 * Test move method 3: pass if y is decreased as per negative input
	 */
	@Test
	void testMove3() {
		float x = 3;
		float y = 10;
		Missile missile = new Missile(x, y, 10, 10);
		missile.move(-3);
		
		assertEquals(y - 3, missile.y);
	}//end testMove3
	
	/**
	 * Test No: 6.4
	 * Test move method 3: pass if y is unchanged if passed in 0
	 */
	@Test
	void testMove4() {
		float x = 3;
		float y = 10;
		Missile missile = new Missile(x, y, 10, 10);
		missile.move(0);
		
		assertEquals(y, missile.y);
	}//end testMove4
	
	/**
	 * Test No: 6.5
	 * Test detectHit when ships x and y are same as missiles x and y
	 */
	@Test
	void testDetectHit1() {
		float x = 100;
		float y = 100;
		Model model = new Model();
		Missile missile = new Missile(x, y, 10, 10);
		PlayerShip player = model.getPlayer();
		player.setX(x);
		player.setY(y);
		missile.detectHit(player);
		
		assertTrue(missile.getHit());
	}//end testDetectHit1
	
	/**
	 * Test No: 6.6
	 * Test detectHit with bad data for x and y values, not within range
	 */
	@Test
	void testDetectHit2() {
		float x = 100;
		float y = 100;
		Model model = new Model();
		Missile missile = new Missile(x, y, 10, 10);
		PlayerShip player = model.getPlayer();
		player.setX(x+100);
		player.setY(y+100);
		missile.detectHit(player);
		
		assertFalse(missile.getHit());
	}//end testDetectHit2
	
	/**
	 * Test No: 6.7
	 * Test if hit was detected if ship x is missile x - 50  and y is the same as missiles y - 10
	 * upper x boundary
	 */
	@Test
	void testDetectHit3() {
		float x = 100;
		float y = 100;
		Model model = new Model();
		Missile missile = new Missile(x, y, 10, 10);
		PlayerShip player = model.getPlayer();
		player.setX(x-50);
		player.setY(y-10);
		missile.detectHit(player);
		
		assertTrue(missile.getHit());
	}//end testDetectHit3
	
	/**
	 * Test No: 6.8
	 * Test if hit was detected if ship x is missile x + 5  and  y is the same as missiles y - 10
	 * lower x boundary
	 */
	@Test
	void testDetectHit4() {
		float x = 100;
		float y = 100;
		Model model = new Model();
		Missile missile = new Missile(x, y, 10, 10);
		PlayerShip player = model.getPlayer();
		player.setX(x+5);
		player.setY(y-10);
		missile.detectHit(player);
		
		assertTrue(missile.getHit());
	}//end testDetectHit4
	
	/**
	 * Test No: 6.9
	 * Test if hit was detected if ship y is y is missile y - 30 and  x is the same as missiles x - 10
	 * upper y boundary
	 */
	@Test
	void testDetectHit5() {
		float x = 100;
		float y = 100;
		Model model = new Model();
		Missile missile = new Missile(x, y, 10, 10);
		PlayerShip player = model.getPlayer();
		player.setX(x-10);
		player.setY(y-30);
		missile.detectHit(player);
		
		assertTrue(missile.getHit());
	}//end testDetectHit5
	
	/**
	 * Test No: 6.10
	 * Test if hit was detected if ship y is y is missile y and x is the same as missiles x - 10
	 * lower y boundary
	 */
	@Test
	void testDetectHit6() {
		float x = 100;
		float y = 100;
		Model model = new Model();
		Missile missile = new Missile(x, y, 10, 10);
		PlayerShip player = model.getPlayer();
		player.setX(x-10);
		player.setY(y);
		missile.detectHit(player);
		
		assertTrue(missile.getHit());
	}//end testDetectHit6
	
	
}//end MissileTests
