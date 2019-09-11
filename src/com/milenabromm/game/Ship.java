package com.milenabromm.game;

import java.util.ArrayList;
import java.util.Random;

import org.gillius.jalleg.binding.AllegroLibrary.ALLEGRO_BITMAP;

/**
 * Ship class is a subclass of SpaceShooterObject.
 * holds an array list of missiles and methods to shoot and move missiles and move the ship object.
 * @author Milena Bromm 555851
 * @version 2.1
 */
public class Ship extends Image{
	//Ship Fields:
	private int health;
	private ArrayList<Missile> missiles = new ArrayList<Missile>();
	private boolean missileShoot;
	private Sound laser;
	
	/**
	 * Ship constructor, calls SpaceShooterObject constructor, 
	 * sets missileShoot to false when initialized and gets the laser sound
	 * @param x - x position
	 * @param y - y position
	 * @param w - width of the image
	 * @param h - width of the image
	 * @param health - health value
	 * @param image - Ship appearance
	 * @param soundPath - laser sound
	 */
	public Ship(float x, float y, float w, float h, int health, ALLEGRO_BITMAP image, String soundPath) {
		super(x, y, w, h, image);
		this.health = health;
		missileShoot = false;
		laser = new Sound(soundPath);
	}//end constructor
	
	/**
	 * 
	 * @return health - current health value
	 */
	public int getHealth() {
		return health;
	}//end getHealth
	
	/**
	 * 
	 * @return missileShoot - set to true when missile is added to missiles array list for first time
	 */
	public boolean getMissileS() {
		return missileShoot;
	}//end getMissileS
	
	/**
	 * set missileShoot to true
	 */
	public void setMissileS() {
		missileShoot = true;
	}//end setMissileS
	
	/**
	 * returns array list missiles
	 * @return missiles - Array List of Missile held in Ship
	 */
	public ArrayList<Missile> getMissiles() {
		return missiles;
	}//end getMissiles
	
	/**
	 * returns missile at index of missiles array list
	 * @param index - position in missiles array list 
	 * @return missile - Missile at index
	 */
	public Missile getMissile(int index) {
		return missiles.get(index);
	}//getMissile
	
	/**
	 * adds a missile to missiles array list
	 * @param missile - Missile added to array list
	 */
	public void setMissiles(Missile missile) {
		this.missiles.add(missile);
	}//end setMissiles
	
	/**
	 * plays the laser sound
	 */
	public void playLaserSound() {
		laser.play(false);
	}//end playLaserSound
	
	/**
	 * sets sound on or off
	 * @param on - true or false
	 */
	public void setSoundOnOff(boolean on) {
		laser.setVolume(on);
	}//end setSoundOnOff
	
	/**
	 * if y value is more than 0, returns if collision with parsed in rubbish element occurs
	 * @param rubbish - Rubbish Array List held in model
	 * @return true or false
	 */
	public boolean getCollision(Rubbish rubbish) {
		if (getY() > 0) {
		return (getY() + 60 >= (rubbish.getY()) && 
				getY() <= (rubbish.getY())+ 20) &&
				(getX() + 52 >= (rubbish.getX()) &&
				getX() <= (rubbish.getX()));
		}else {
			return false;
		}//end if
	}//end getCollision

	/**
	 * calls shoot missile and moves y downwards
	 */
	public void move() {
		shootMissile();
		//update positions 
		moveY((float) 1.4);
	}//end move
	
	/**
	 * generates a random number to determine if a new missile is added to missiles.
	 * if yes missile is added and missile shoot is set to true and then the laser sound is played.
	 */
	public void shootMissile() {
		Random rand = new Random();
		int num = rand.nextInt(300);
		if (num > 30 && num < 32) {
			missiles.add(new Missile((getX()+24), (getY()+20), 5, 20));
			missileShoot = true;
			playLaserSound();
		}//end if
	}//end shootMissile
	
	/**
	 * if missiles have been shoot, moves all missiles and calls detect hit on the target ship
	 * @param direction - negative(left) or positive(right) float value
	 * @param ship - ship instance taken from enemyShips Array List or PlayerShip instance 
	 */
	public void moveMissiles(float direction, Ship ship) {
		if (missileShoot) {
		for (int count = 0; count < missiles.size(); count++) {
			missiles.get(count).move(direction);
			missiles.get(count).detectHit(ship);	
			}//end for
		}//end if
	}//end move Missiles
	

	/**
	 * if missiles have been shoot, checks if any missiles have hit a ship or exited the game display and 
	 * if yes removes them from the missiles array list
	 * @param displayX - display width
	 * @param displayY - display height
	 */
	public void updateMissiles(float displayX, float displayY) {
		if (missileShoot) {
			
			ArrayList<Missile> missilesToRemove = new ArrayList<Missile>();
		for (int count = 0; count < missiles.size(); count++) {
			if (missiles.get(count).top() < 0||
					missiles.get(count).bottom() > displayY ||
					missiles.get(count).getHit()) {
				missilesToRemove.add(missiles.get(count));
				}//end if
			}//end for
			missiles.removeAll(missilesToRemove);
		
		}//end if
		
	}//end updateMissiles
	
	/**
	 * takes in a parameter to deduct damage from the health value
	 * @param damage - damage to be deducted from health
	 */
	public void deductHealth(int damage) {
		health -= damage;
	}//end deductHealth
	

}//end Ship
