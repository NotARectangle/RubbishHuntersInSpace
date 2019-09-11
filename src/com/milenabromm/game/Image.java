package com.milenabromm.game;

import org.gillius.jalleg.binding.AllegroLibrary.ALLEGRO_BITMAP;

/**
 * subclass of space shooter, class for images used in game, 
 * holds values for height and width in addition to superclass values
 * @author Milena Bromm 555851
 * @version 2.1
 */
public class Image extends SpaceShooterObject{
	
	//Image Fields
	private float height, width;

	/**
	 * Image constructor
	 * @param x - x position
	 * @param y - y position
	 * @param w - width of the image
	 * @param h - width of the image
	 * @param image - image appearance
	 */
	public Image(float x, float y, float w, float h, ALLEGRO_BITMAP image) {
		super(x, y, image);
		height = h;
		width = w;
	}//end constructor
	
	/**
	 * Image constructor, for sending x and y values after
	 * sets x and y with a default value of 0
	 * @param w - width of the image
	 * @param h - width of the image
	 * @param image - image appearance
	 */
	public Image(float w, float h, ALLEGRO_BITMAP image) {
		super(0, 0, image);
		height = h;
		width = w;
	}//end constructor
	
	/**
	 * Image moves + 1 on y axis if not overwritten
	 */
	public void move() {
		moveY(1f);
	}//end move
	
	/**
	 * @return height - heigth of the image
	 */
	public float getHeight() {
		return height;
	}//end getHeight
	
	/**
	 * @return width - width of the image
	 */
	public float getWidth() {
		return width;
	}//end getWidth
	
	/**
	 * @return bottomY - bottom y position of image
	 */
	public float getBottomY() {
		return getY() + height;
	}//end getBottomY
	
	/**
	 * @return rightX - right x position of image
	 */
	public float getRightX() {
		return getX() + width;
	}//end getRightX
	
	/**
	 * @return middleY - mid y position
	 */
	public float getMiddleY() {
		return getY() + height/2;
	}//end getMiddleY
	
	/**
	 * @return middleX - mid x position
	 */
	public float getMiddleX() {
		return getX() + width/2;
	}//end getMiddleX
		
}//end Image
