package com.milenabromm.game;

import org.gillius.jalleg.framework.math.Rect;

/**
 * Missile objects are initialized from ship object and have methods to move, detect collision with a ship and deduct hit ships health.
 * Missile is a subclass of the allegro Rect with gives the advantage of it being easily colored and filled using allegro drawing methods.
 * It also inherits the methods for getting the x and y positions as well as the objects top, bottom, start and end position of the rectangle
 * @author Milena Bromm
 * @version 2.1
 */
public class Missile extends Rect{
	
	//stores the value of whether or not the missile has landed a hit on a ship
	private boolean hit;

	/**
	 * Uses the superclass constructor to create a rectangle using the parameters given
	 * @param x - x position
	 * @param y - y position
	 * @param h - height of Missile
	 * @param w - width of Missile
	 */
	public Missile(float x, float y, float h, float w) {
		super(x, y, h, w);
		hit = false;
	}//end constructor
		
	/**
	 * Uses the superclass method, to move the object only along the y axis
	 * @param direction - direction in which missile is moving(up/down)
	 */
	public void move(float direction) {
		this.move(0, direction);
	}//end move
	
	/**
	 * Detects if there is collision between the rectangle and a ship,
	 * by comparing the y and x positions
	 * @param ship - a ship instance, either from enemy ship array list or player
	 * @return true or false
	 */
	public boolean detectCollision(Ship ship) {
		return 	(y >= (ship.getY() ) && y <= (ship.getY() + 30)) &&
					(x >= (ship.getX() -5) && x <= (ship.getX() + 50));
		}//end detectCollision
	
	/**
	 * Detects if passed in ship object has been hit and sets the hit value, if yes deducts health from the hit ship
	 * @param ship - a ship instance, either from enemy ship array list or player
	 */
	public void detectHit(Ship ship) {

		hit = detectCollision(ship);
		
		if (hit) {
			ship.deductHealth(20);
		}//end if
	}//end detectHit
	
	/**
	 * returns if the missile hit a ship
	 * @return hit - true or false, determines if missile has landed a hit on a ship
	 */
	public boolean getHit() {
		return hit;
	}//end getHit
	
}//end Missile
