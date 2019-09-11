package com.milenabromm.game;

import org.gillius.jalleg.binding.AllegroLibrary.ALLEGRO_BITMAP;

/**
 * Superclass of Checkpoint, Ship and Rubbish Classes. Initially not on design but was added to counteract repetitive code. 
 * @author Milena Bromm
 * @version 2.1
 */
public abstract class SpaceShooterObject {
	//SpaceShooterObject fields
	//x and y positions on the display
	private float x, y;
	//appearance of the object
	private ALLEGRO_BITMAP img;
	
	/**
	 * SpaceShooterObject Constructor
	 * @param x - x position
	 * @param y - y position 
	 * @param image - Appearance
	 */
	public SpaceShooterObject(float x, float y, ALLEGRO_BITMAP image){
		this.x = x;
		this.y = y;
		this.img = image;
	}//end constructor
	
	/**
	 * 
	 * @return img - appearance
	 */
	public ALLEGRO_BITMAP getImage() {
		return img;
	}//end getImage
	
	/**
	 * sets a new image appearance
	 * @param img - appearance
	 */
	public void setImage(ALLEGRO_BITMAP img) {
		this.img = img;
	}//end getImage
	
	/**
	 * returns y position
	 * @return y
	 */
	public float getY() {
		return y;
	}//end getY
	
	/**
	 * returns x position
	 * @return x
	 */
	public float getX() {
		return x;
	}//end getX
	
	/**
	 * sets y to a new value
	 * @param newY - new position of Y
	 */
	public void setY(float newY) {
		y = newY;
	}//end setY
	
	/**
	 * sets x to a new value
	 * @param newX - new position of X
	 */
	public void setX(float newX) {
		x = newX;
	}//end setX
	
	/**
	 * adds direction to y value
	 * @param direction - float value negative or positive
	 */
	public void moveY(float direction) {
		y += direction;
	}//end moveY
	
	/**
	 * adds direction to x value
	 * @param direction - float value negative or positive
	 */
	public void moveX(float direction) {
		x += direction;
	}
	
	/**
	 * move method, that is used to move all SpaceShooterObjects on the screen
	 */
	public void move() {
		
	}//end move

}//end SpaceShooterObject
