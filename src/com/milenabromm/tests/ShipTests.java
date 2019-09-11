package com.milenabromm.tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import com.milenabromm.game.Missile;
import com.milenabromm.game.Model;
import com.milenabromm.game.PlayerShip;
import com.milenabromm.game.Rubbish;
import com.milenabromm.game.Ship;
/**
 * Test Set No: 4
 * @author Milena Bromm
 * @version 2
 */
class ShipTests {
	
	/*No test for shoot missile since the random number is generated inside the method and 
	 * can't be manipulated from the outside.
	 */

/**
 * Test No: 4.1
 * Test getCollision() y is 10
 */
	@Test
	void testGetCollision1() {
		Model model = new Model();
		Rubbish rubbish = model.getRubbish().get(0);
		Ship ship =  model.getEnemyShips().get(0);
		ship.setY(10);
		
		assertFalse(ship.getCollision(rubbish));
	}//end test Colllision1
	
/**
 * Test No: 4.2
 * Test getCollision2 y is 0 rubbish not in range
 */
	@Test
	void testGetCollision2() {
		Model model = new Model();
		Rubbish rubbish = model.getRubbish().get(0);
		Ship ship =  model.getEnemyShips().get(0);
		ship.setY(0);
				
		assertFalse(ship.getCollision(rubbish));
	}//end test Collision2	
	
/**
 * Test No: 4.3
 * Test getCollision3 y is 1 rubbish not in range
 */
	@Test
	void testGetCollision3() {
		Model model = new Model();
		Rubbish rubbish = model.getRubbish().get(0);
		Ship ship =  model.getEnemyShips().get(0);
		ship.setY(1);
			
		assertFalse(ship.getCollision(rubbish));
	}//end testCollision3
	
	/**
	 * Test No: 4.4
	 * Test getCollisionTest y is 1 and rubbish in range
	 */
	@Test
	void testGetCollision4() {
		Model model = new Model();
		Rubbish rubbish = model.getRubbish().get(0);
		Ship ship =  model.getEnemyShips().get(0);
		ship.setY(1);
		rubbish.setY(ship.getY());
		rubbish.setX(ship.getX());
			
		assertTrue(ship.getCollision(rubbish));
	}//end testCollision4
	
	/**
	 * Test No: 4.5
	 * Test getCollision upper border of y collision ship y is 1 and rubbish y is ships y + 60
	 */
	@Test
	void testGetCollision5() {
		Model model = new Model();
		Rubbish rubbish = model.getRubbish().get(0);
		Ship ship =  model.getEnemyShips().get(0);
		ship.setY(1);
		rubbish.setY(ship.getY()+60);
		rubbish.setX(ship.getX());
			
		assertTrue(ship.getCollision(rubbish));
	}//end testCollision5
	
	/**
	 * Test No: 4.6
	 * Test getCollision lower border of y collision ship y is 1 and rubbish y is ships y -20
	 */
	@Test
	void testGetCollision6() {
		Model model = new Model();
		Rubbish rubbish = model.getRubbish().get(0);
		Ship ship =  model.getEnemyShips().get(0);
		ship.setY(1);
		rubbish.setY(ship.getY()-20);
		rubbish.setX(ship.getX());
			
		assertTrue(ship.getCollision(rubbish));
	}//end testCollision6

	/**
	 * Test No: 4.7
	 * Test getCollision lower border of y collision ship y is 1 and rubbish y is ships y -21
	 */
	@Test
	void testGetCollision7() {
		Model model = new Model();
		Rubbish rubbish = model.getRubbish().get(0);
		Ship ship =  model.getEnemyShips().get(0);
		ship.setY(1);
		rubbish.setY(ship.getY()-21);
		rubbish.setX(ship.getX());
			
		assertFalse(ship.getCollision(rubbish));
	}//end testCollision7
	
	/**
	 * Test No: 4.8
	 * Test getCollision upper border of x collision ship y is 1 and x is 0 and rubbish x is ships x + 52
	 */
	@Test
	void testGetCollision8() {
		Model model = new Model();
		Rubbish rubbish = model.getRubbish().get(0);
		Ship ship =  model.getEnemyShips().get(0);
		ship.setY(1);
		ship.setX(0);
		rubbish.setY(ship.getY());
		rubbish.setX(ship.getX()+ 52);
			
		assertTrue(ship.getCollision(rubbish));
	}//end testCollision8
	
	/**
	 * Test No: 4.9
	 * Test getCollision upper border of x collision ship y is 1 and x is 0 and rubbish x is ships x + 53
	 */
	@Test
	void testGetCollision9() {
		Model model = new Model();
		Rubbish rubbish = model.getRubbish().get(0);
		Ship ship =  model.getEnemyShips().get(0);
		ship.setY(1);
		ship.setX(0);
		rubbish.setY(ship.getY());
		rubbish.setX(ship.getX()+ 53);
			
		assertFalse(ship.getCollision(rubbish));
	}//end testCollision9
	
	/**
	 * Test No: 4.10
	 * Test getCollision lower border of x collision ship y is 1 and x is 0 and rubbish x is ships x 
	 */
	@Test
	void testGetCollision10() {
		Model model = new Model();
		Rubbish rubbish = model.getRubbish().get(0);
		Ship ship =  model.getEnemyShips().get(0);
		ship.setY(1);
		ship.setX(0);
		rubbish.setY(ship.getY());
		rubbish.setX(ship.getX());
			
		assertTrue(ship.getCollision(rubbish));
	}//end testCollision10
	
	/**
	 * Test No: 4.11
	 * Test getCollision lower border of x collision ship y is 1 and x is 0 and rubbish x is ships x -1
	 */
	@Test
	void testGetCollision11() {
		Model model = new Model();
		Rubbish rubbish = model.getRubbish().get(0);
		Ship ship =  model.getEnemyShips().get(0);
		ship.setY(1);
		ship.setX(0);
		rubbish.setY(ship.getY());
		rubbish.setX(ship.getX()-1);
			
		assertFalse(ship.getCollision(rubbish));
	}//end testCollision11
	
	/**
	 * Test No: 4.12
	 * Test Move y value should have increased by 1.4 x value should not change
	 */
	@Test
	void testMove() {
		Model model = new Model();
		Ship ship =  model.getEnemyShips().get(0);
		float shipY = ship.getY();
		float shipX = ship.getX();
		ship.move();
		assertTrue(shipX == ship.getX() && shipY + 1.4f == ship.getY());
	}//end testMove
	
	/**
	 * Test No: 4.13
	 * Test moveMissiles, tests if all missiles have been moved
	 */
	@Test
	void testMoveMissiles1() {
		Model model = new Model();
		Ship ship = model.getEnemyShips().get(0);
		PlayerShip player = model.getPlayer();
		float x = 300, y = 100;
		ship.setX(x);
		ship.setY(y);
		player.setX(x);
		player.setY(y);
		ship.setMissiles(new Missile(x, y, 5, 20));
		ship.setMissiles(new Missile(x+20, y+20, 5, 20));
		ship.setMissileS();
		ship.moveMissiles(10, ship);
		
		assertTrue(y + 10 == ship.getMissile(0).y && y + 30 == ship.getMissile(1).y);
	}//end testMoveMissiles1
	
	/**
	 * Test No: 4.14
	 * Test moveMissiles, tests if health was deducted for the number of in range missiles to target ship (player ship)
	 */
	@Test
	void testMoveMissiles2() {
		Model model = new Model();
		Ship ship = model.getEnemyShips().get(0);
		PlayerShip player = model.getPlayer();
		float x = 300, y = 100;
		ship.setX(x);
		ship.setY(y);
		player.setX(x);
		player.setY(y);
		int phealth = player.getHealth();
		ship.setMissiles(new Missile(x, y, 5, 20));
		ship.setMissiles(new Missile(x+20, y+20, 5, 20));
		ship.setMissileS();
		ship.moveMissiles(10, player);
		
		assertEquals(phealth - 40, player.getHealth());
	}//end testMoveMissiles2
	
	
	/**
	 * Test No: 4.15
	 * Test moveMissile, tests that missile don't move if missileS is set to false
	 */
	@Test
	void testMoveMissiles3() {
		Model model = new Model();
		Ship ship = model.getEnemyShips().get(0);
		PlayerShip player = model.getPlayer();
		float x = 300, y = 100;
		ship.setX(x);
		ship.setY(y);
		player.setX(x);
		player.setY(y);
		ship.setMissiles(new Missile(x, y, 5, 20));
		ship.setMissiles(new Missile(x+20, y+20, 5, 20));
		ship.moveMissiles(10, ship);
		
		assertFalse(y + 10 == ship.getMissile(0).y && y + 30 == ship.getMissile(1).y);
	}//end testMoveMissiles3
	
	/**
	 * Test No: 4.16
	 * Test updateMissile: tests if missiles are removed when they left playing field
	 */
	@Test
	void testUpdateMissiles1() {
		Model model = new Model();
		float maxY = 70;
		float x = 300, y = 100;
		Ship ship = model.getEnemyShips().get(0);
		ship.setX(x);
		ship.setY(y);
		ship.setMissiles(new Missile(x, y, 5, 20));
		ship.setMissiles(new Missile(x, y+20, 5, 20));
		
		ship.setMissileS();
		ship.updateMissiles(0, maxY);
		
		assertEquals(0, ship.getMissiles().size());
	}
	
	/**
	 * Test No: 4.17
	 * Test updateMissile: No change if missile shoot is still false
	 */
	@Test
	void testUpdateMissiles2() {
		Model model = new Model();
		float minY = 0;
		float x = 300, y = 100;
		Ship ship = model.getEnemyShips().get(0);
		ship.setX(x);
		ship.setY(y);
		ship.setMissiles(new Missile(x, minY-1, 5, 20));
		ship.setMissiles(new Missile(x, minY-30, 5, 20));
		
		ship.updateMissiles(0, minY);
		
		assertEquals(2, ship.getMissiles().size());
	}//end testUpdateMissile
	
	
	/**
	 * Test No: 4.18
	 * Test updateMissile: tests if missiles are removed when they have detected a hit, with one missile
	 */
	@Test
	void testUpdateMissile3() {
		Model model = new Model();
		Ship ship = model.getEnemyShips().get(0);
		PlayerShip player = model.getPlayer();
		float x = 300, y = 100;
		ship.setX(x);
		ship.setY(y);
		player.setX(x);
		player.setY(y);
		ship.setMissiles(new Missile(x, y, 5, 20));
		ship.setMissileS();
		ship.moveMissiles(10, player);
		
		ship.updateMissiles(x+200, y+400);
		assertEquals(0, ship.getMissiles().size());
	}//end testUpdateMissile4
	
	/**
	 * Test No: 4.19
	 * Test updateMissile: tests if missiles are removed when they have detected a hit, opposite direction
	 */
	@Test
	void testUpdateMissile4() {
		Model model = new Model();
		Ship ship = model.getEnemyShips().get(0);
		PlayerShip player = model.getPlayer();
		float x = 300, y = 100;
		ship.setX(x);
		ship.setY(y);
		player.setX(x);
		player.setY(y);

		player.setMissiles(new Missile(x, y+30, 5, 20));
		player.setMissileS();
		player.moveMissiles(-10, ship);
		
		System.out.println("Hit succesfull: " + player.getMissile(0).getHit());
		player.updateMissiles(x+200, y+400);
		assertEquals(0, player.getMissiles().size());
	}//end testUpdateMissile4
	
	/**
	 * Test No: 4.20
	 * Test updateMissile: tests if missiles are removed when they have detected a hit, with two missiles
	 */
	@Test
	void testUpdateMissile5() {
		Model model = new Model();
		Ship ship = model.getEnemyShips().get(0);
		PlayerShip player = model.getPlayer();
		float x = 300, y = 100;
		ship.setX(x);
		ship.setY(y);
		player.setX(x);
		player.setY(y);
		ship.setMissiles(new Missile(x, y, 5, 20));
		ship.setMissiles(new Missile(x+20, y+20, 5, 20));
		ship.setMissileS();
		ship.moveMissiles(10, player);
		
		ship.updateMissiles(x+200, y+400);
		assertEquals(0, ship.getMissiles().size());
	}//end testUpdateMissile5
	
}//end ShipTests
