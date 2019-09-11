package com.milenabromm.game;

import java.util.ArrayList;

import org.gillius.jalleg.binding.AllegroLibrary.ALLEGRO_BITMAP;

/**
 * PlayerShip is a subclass of Ship, it behaves similarly but the player ship has more sounds
 * and methods to translate the user input into left and right movement as well as a few modifications to superclass methods
 * @author Milena Bromm 555851
 * @version 2.1
 */
public class PlayerShip extends Ship{
	
	//PlayerShip Fields:
	private boolean up = true;
	private int vibCount;
	private Sound collectSound;
	private Sound shipSound;
	private Sound damageSound;
	
	/**
	 * PlayerShip constructor, takes in the parameters for the superclass constructor and initializes the player ship sounds
	 * @param x - x value
	 * @param y - y value
	 * @param w - width of the image
	 * @param h - width of the image
	 * @param health - health value 
	 * @param image - PlayerShip appearance
	 * @param sound1 - laser sound
	 * @param sound2 - ship sound
	 * @param sound3 - collection sound
	 * @param sound4 - damage sound
	 */
	public PlayerShip(float x, float y, float w, float h, int health, ALLEGRO_BITMAP image, 
			String sound1, String sound2, String sound3, String sound4) {
		super(x, y, w, h, health, image, sound1);
		shipSound = new Sound(sound2);
		collectSound = new Sound(sound3);
		damageSound = new Sound(sound4);
		vibCount = 0;
	}//end constructor
	
	/**
	 * sets sound on or off
	 * @param on - true or false
	 */
	public void setSoundOnOff(boolean on) {
		super.setSoundOnOff(on);
		shipSound.setVolume(on);
		collectSound.setVolume(on);
		damageSound.setVolume(on);
	}//end setSoundOnOff
	
	/**
	 * Starts playing the ship sound
	 */
	public void playShipSound(){
		shipSound.play(true);
	}//end playShipSound
	
	/**
	 * Stops playing the ship sound
	 */
	public void quitShipSound(){
		shipSound.quit();
	}//end quitShipSound
	
	/**
	 * Return if moving x to parsed in direction would still be inside the game display frame
	 * @param direction - negative(left) or positive(right) float value
	 * @param displayX - width of the screen
	 * @return true or false
	 */
	public boolean inFrame(float direction, float displayX) {
		//Initializing left and right border, so that the full player image is always displayed within the frame.
		float leftBorder = 15;
		float rightBorder = (displayX - 60);
		
		//returns true if direction is less than 0 (moving left) and x is still greater than leftBorder
		// or direction is more than 0 (moving right) and x is still less than rightBorder 
		if ((direction < 0 && getX() > leftBorder) || (direction > 0 && getX() < rightBorder)){
			return true;
			}else {
				return false;
		}//end if
		
	}//end inFrame
	
	/**
	 * moves spaceship up and down to simulate moving forward.
	 */
	public void move() {
		if (up) {
			moveY(-0.4f);
			vibCount++;
			if (vibCount >= 2) {
			up = false;
			vibCount = 0;
			}//end If
			
		}else {
			moveY(0.4f);
			vibCount++;
			if (vibCount >= 2) {
				up = true;
				vibCount = 0;
				}//end If
		}//end if
	}// end move
	
	/**
	 * Translates the move input to three different move options, moving x left, moving x right and shooting a missile
	 * @param move - type of movement
	 * @param displayX - width of screen
	 */
	public void move(int move, float displayX) {
		//values for moving the player ship
		float moveLeft = -15;
		float moveRight = 15;
		//call first move method regardless of move input
	//	move();
		//switch statement on move
		switch(move) {
		//if move is 1
		case 1:
			//check if there is space on the left to move ship
			//if yes
			//move ship to left
			if (inFrame(moveLeft, displayX))
			moveX(moveLeft);
			break;
		//if move is 2
		case 2:
			//check if there is space on the right to move ship
			//if yes
			//move ship to right
			if (inFrame(moveRight, displayX))
			moveX(moveRight);
			break;
		//if move is 3	
		case 3:
			//This code is implemented this way to avoid the player shooting to many missiles at the same time
			//which resulted in missiles looking like one straight line instead of individual missiles
			
			//IF there are no missiles yet, add a new missile to the missile array, set missileShoot to true 
			//and play the laser sound
			if (getMissiles().size() == 0) {
				setMissiles((new Missile(getX() + 24, getY(), 5, 15)));
				setMissileS();
				playLaserSound();
			//If missile array is not empty only add a new missile if previous missiles y has some distance to new missile
			}else if (getMissiles().get(getMissiles().size()-1).centerY() < getY() -100){
			setMissiles((new Missile(getX() + 24, getY(), 5, 15)));
			setMissileS();
			playLaserSound();
			}//end if
			break;
		}//end switch
			
	}//end move
	
	/**
	 * returns player ship collision with a rubbish element
	 * @return true or false
	 */
	public boolean getCollision(Rubbish rubbish) {
		return (getY() + 80 >= (rubbish.getY()) && 
				getY() <= (rubbish.getY())+ 20) &&
				(getX() + 52 >= (rubbish.getX()) &&
				getX() <= (rubbish.getX()));
	}//end getCollision
	
	/**
	 * iterates through the rubbish array to detect if collision with any rubbish object appears
	 *	and sets those rubbish elements to collected
	 * @param rubbish - rubbish array list held in model
	 */
	public void detectRubbish(ArrayList<Rubbish> rubbish){
		for (int count = 0; count < rubbish.size(); count++) {			
//			if in range
			if (getCollision(rubbish.get(count))){
				rubbish.get(count).setCollected();
				//plays collection sound if an element is collected
				collectSound.play(false);
			}//end if
		}//end for
	}//end detect Rubbish

	/**
	 * Iterates through the missiles array and moves each missile in direction, 
	 * also checks if any missile collide with an element of the enemyShips array
	 * and if yes deduct health from that ship.
	 * @param direction - negative(left) or positive(right) float value
	 * @param enemyShips - array list of Ship held in model
	 */
	public void moveMissiles(float direction, ArrayList<Ship> enemyShips) {
		//if a missile has been shoot
		if (getMissileS()) {
		//iterate through missiles array list to move each object	
		for (int count = 0; count < getMissiles().size(); count++) {
			getMissiles().get(count).move(direction);
		// for each missile object iterate through the enemyShips array list to check if collision occurs
			//and if yes deduct health from that ship
			for (int index = 0; index < enemyShips.size(); index++) {
					getMissiles().get(count).detectHit(enemyShips.get(index)); 	
					if(getMissiles().get(count).getHit()) {
						break;
					}
				}//end for
			}//end for
		}//end if
	}//end moveMissiles
	
	
	/**
	 * Takes in input for damage and deducts the damage from the health value
	 */
	public void deductHealth(int damage) {
		super.deductHealth(damage);
		//play damage sound after deducting health
		damageSound.play(false);
	}//end deductHealth

}//end PlayerShip
