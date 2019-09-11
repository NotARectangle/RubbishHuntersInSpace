package com.milenabromm.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.milenabromm.game.Model;
import com.milenabromm.game.Ship;

/**
 * Test Set No: 1
 * @author Milena Bromm
 * @version 2
 */
class SpaceShooterObjectTests {

	/**
	 * Test no: 1.1
	 * Test setY()
	 */
	@Test
	void testSetY() {
		Model model = new Model();
		Ship ship = model.getEnemyShips().get(0);
		ship.setY(7f);
		assertEquals(7f, ship.getY());
	}//end testSetY
	
	/**
	 * Test no: 1.2
	 * Test setX()
	 */
	@Test
	void testSetX() {
		Model model = new Model();
		Ship ship = model.getEnemyShips().get(0);
		ship.setX(7f);
		assertEquals(7f, ship.getX());
	}//end testSetX
	
	/**
	 * Test no: 1.3
	 * Test moveY()
	 */
	@Test
	void testMoveY() {
		Model model = new Model();
		Ship ship = model.getEnemyShips().get(0);
		float y = ship.getY();
		ship.moveY(7f);
		assertEquals(y + 7f, ship.getY());
	}//end testMoveY
	
	/**
	 * Test no: 1.4
	 * Test moveX()
	 */
	@Test
	void testMoveX() {
		Model model = new Model();
		Ship ship = model.getEnemyShips().get(0);
		float x = ship.getX();
		ship.moveX(7f);
		assertEquals(x + 7f, ship.getX());
	}//end testMoveX

}//end SpaceShooterObjectTests
