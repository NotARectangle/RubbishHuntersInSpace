package com.milenabromm.game;

import org.gillius.jalleg.binding.AllegroLibrary.ALLEGRO_BITMAP;

/**
 * The class for the collectible rubbish element. Has methods to retrieve and modify the boolean collected
 * and a overwritten move method. Rubbish is a subclass of SpaceShooterObject
 * @author Milena Bromm 555851
 * @version 2.1
 */
public class Rubbish extends Image{
	//Rubbish field collected
	private Boolean collected;
	
	/**
	 * Rubbish constructor, 
	 * calls SpaceShooterObject constructor collected is set to false when first initialized
	 * @param x - x position
	 * @param y - y position
	 * @param w - width of the image
	 * @param h - width of the image
	 * @param image - Rubbish appearance
	 */
	public Rubbish(float x, float y, float w, float h, ALLEGRO_BITMAP image) {
		super(x, y, w, h, image);
		collected = false;
	}//end constructor
	
	/**
	 * 
	 * @return collected - if collected by player or not
	 */
	public boolean getCollected() {
		return collected;
	}//end collected
	
	/**
	 * Set collected to true
	 */
	public void setCollected() {
		collected = true;
	}//end setCollected

	/**
	 * moves rubbish downwards if not collected and if collected moves it to a position behind the playership
	 * @param player- PlayerShip instance held in model
	 */
	public void move(PlayerShip player) {
		if (collected) {
			setX(player.getX() + 10);
			setY((player.getY() + 85));
		}else {
			moveY((float) 1);
		}//end IF
	}//end move

}//end Rubbish
