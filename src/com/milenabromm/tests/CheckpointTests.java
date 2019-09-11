package com.milenabromm.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.milenabromm.game.Checkpoint;
import com.milenabromm.game.Model;
import com.milenabromm.game.PlayerShip;
import com.milenabromm.game.Rubbish;

/**
 * Test Set No: 2
 * @author Milena Bromm
 * @version 2
 */
class CheckpointTests {

	/**
	 * Test No: 2.1
	 * Tests if score value increases when collected rubbish is 
	 * parsed into countPlayerRubbish method.  
	 */
	@Test
	void testCountPlayerRubbish1() {
		int count = 0; 
		Model model = new Model();
		ArrayList<Rubbish> rubbish = model.getRubbish();
		for (int index = 0; index < 2; index++) {
			rubbish.get(index).setCollected();
			count++;
		}//end for
		Checkpoint checkpoint = model.getCheckpoint();
		checkpoint.countPlayerRubbish(rubbish);
		
		assertEquals(count, checkpoint.getScore());
	}//end testCountPlayerRubbish1
	
	/**
	 * Test No 2.2
	 * Tests if score value stays 0 when no rubbish is 
	 * parsed into countPlayerRubbish method.  
	 */
	@Test
	void testCountPlayerRubbish2() {
		Model model = new Model();
		ArrayList<Rubbish> rubbish = model.getRubbish();
		Checkpoint checkpoint = model.getCheckpoint();
		checkpoint.countPlayerRubbish(rubbish);
		
		assertEquals(0, checkpoint.getScore());
	}//end testCountPlayerRubbish2
	
	/**
	 * Test No 2.3
	 * Tests if collected rubbish is removed from rubbish array
	 */
	@Test
	void testCountPlayerRubbish3() {
		int count = 0; 
		int size;
		Model model = new Model();
		ArrayList<Rubbish> rubbish = model.getRubbish();
		size = rubbish.size();
		for (int index = 0; index < 2; index++) {
			rubbish.get(index).setCollected();
			count++;
		}//end for
		Checkpoint checkpoint = model.getCheckpoint();
		checkpoint.countPlayerRubbish(rubbish);
		
		assertEquals(size - count, rubbish.size());
	}//end testCountPlayerRubbish3
	
	/**
	 * Test No 2.4
	 * Tests if inRange returns true if players x value is greater than checkpoint 
	 */
	@Test
	void testInRangeTrue() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		player.setX(300);
		Checkpoint checkpoint = model.getCheckpoint();
		checkpoint.setX(30);
		
		assertTrue(checkpoint.inRange(player));
	}//end testInRangeTrue
	
	/**
	 * Test No 2.5
	 * Tests if inRange returns false if players x value is lesser than checkpoint 
	 */
	@Test
	void testInRangeFalse() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		player.setX(299);
		Checkpoint checkpoint = model.getCheckpoint();
		checkpoint.setX(300);
		
		assertFalse(checkpoint.inRange(player));
	}//end testInRangeFalse
	
	/**
	 * Test No 2.6
	 * Tests if inRange returns true if players x value is equal checkpoint x 
	 */
	@Test
	void testInRangeBoundary() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		player.setX(300);
		Checkpoint checkpoint = model.getCheckpoint();
		checkpoint.setX(300);
		
		assertTrue(checkpoint.inRange(player));
	}//end testInRangeFalse
	
	/**
	 * Test No 2.7
	 * Tests if detectPlayer returns true if player y - 50 is less than Checkpoints y
	 */
	@Test
	void testDetectPlayerTrue() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		player.setY(300);
		Checkpoint checkpoint = model.getCheckpoint();
		checkpoint.setY(300);
		
		assertTrue(checkpoint.detectPlayer(player));
	}//end testDetectPlayerTrue
	
	/**
	 * Test No 2.8
	 * Tests if detectPlayer returns false if player y - 50 is greater than Checkpoints y
	 */
	@Test
	void testDetectPlayerFalse() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		player.setY(400);
		Checkpoint checkpoint = model.getCheckpoint();
		checkpoint.setY(300);
		
		assertFalse(checkpoint.detectPlayer(player));
	}//end testDetectPlayerFalse
	
	/**
	 * Test No 2.9
	 * Tests if detectPlayer returns true if player y - 50 equals Checkpoints y
	 */
	@Test
	void testDetectPlayerBoundary() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		player.setY(350);
		Checkpoint checkpoint = model.getCheckpoint();
		checkpoint.setY(300);
		
		assertTrue(checkpoint.detectPlayer(player));
	}//end testDetectPlayerBoundary
	
	/**
	 * Test No 2.10
	 * Test move method, test if x changes when player detected
	 */
	@Test
	void testMove1() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		ArrayList<Rubbish> rubbish = model.getRubbish();
		player.setX(100);
		player.setY(300);
		Checkpoint checkpoint = model.getCheckpoint();
		checkpoint.setX(70);
		checkpoint.setY(300);
		
		checkpoint.move(player, rubbish);
		
		//since the player should be detected x should have changed from 70 to 70 - 7 (63)
		assertEquals(63, checkpoint.getX());	
	}//end testMove1
	
	/**
	 * Test No 2.11
	 * Test move method: test if countPlayerRubbish is called when player in detected and in range
	 */
	@Test
	void testMove2() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		player.setX(100);
		player.setY(300);
		Checkpoint checkpoint = model.getCheckpoint();
		checkpoint.setX(70);
		checkpoint.setY(300);
		ArrayList<Rubbish> rubbish = model.getRubbish();
		for (int index = 0; index < 2; index++) {
			rubbish.get(index).setCollected();
		}//end for
		checkpoint.move(player, rubbish);
		
		//checks if count player rubbish has been called, since it would have changed the score
		assertTrue(checkpoint.getScore() > 0);	
	}//end testMove2
	
	/**
	 * Test No 2.12
	 * Test move method: test if y changes when player is not detected
	 */
	@Test
	void testMove3() {
		Model model = new Model();
		PlayerShip player = model.getPlayer();
		player.setX(100);
		player.setY(300);
		Checkpoint checkpoint = model.getCheckpoint();
		checkpoint.setX(70);
		//detect range should not be called as long as detectPlayer returns false
		checkpoint.setY(200);
		ArrayList<Rubbish> rubbish = model.getRubbish();
		
		checkpoint.move(player, rubbish);
		
		assertEquals(202, checkpoint.getY());
	}//end testMove3
	

}//end checkpointTests
