package com.milenabromm.game;

import java.util.ArrayList;

import org.gillius.jalleg.binding.AllegroLibrary.ALLEGRO_BITMAP;

/**
 * Checkpoint is a subclass of SpaceShooterObject<br>
 * Checkpoint is responsible of setting the score based on the player collected rubbish,<br>when the player ship enters the checkpoints range
 * @author Milena Bromm 555851
 * @version 2.1
 */
public class Checkpoint extends Image{
	//Checkpoint Fields
	private int score;
	private Sound shipSound;
	private Sound sellSound;
	private boolean tradeComplete;

	/**
	 * Checkpoint constructor
	 * @param x - x position on display
	 * @param y - y position on display
	 * @param w - width of the image
	 * @param h - width of the image
	 * @param image - image appearance
	 * @param shipSound - sound that the checkpoint is making on loop
	 * @param sellSound - sound the checkpoint is making each time the player sells rubbish to it
	 */
	public Checkpoint(float x, float y, float w, float h, ALLEGRO_BITMAP image, String shipSound, String sellSound) {
		super(x, y, w, h, image);
		this.shipSound = new Sound(shipSound);
		this.sellSound = new Sound(sellSound);
		tradeComplete = false;
	}//end Checkpoint

	/**
	 * returns the game score
	 * @return score - current game score
	 */
	public int getScore() {
		return score;
	}//end getScore

	public void resetTradeComplete() {
		tradeComplete = false;
	}
	
	public void setSoundOnOff(boolean on) {
		shipSound.setVolume(on);
		sellSound.setVolume(on);
	}
	
	/**
	 * Stops playing the ship sound
	 */
	public void quitShipSound(){
		shipSound.quit();
	}//end quitShipSound
	/**
	 * Checkpoint's move method. Moves the x and y position and call countPlayerRubbish()<br> depending on the player ship being in range
	 * @param player - game instance of PlayerShip 
	 * @param fieldRubbish - the rubbish array held in model
	 */
	public void move(PlayerShip player, ArrayList<Rubbish> fieldRubbish) {
		//if detectPlayer() return true
		if(detectPlayer(player)) {
			//move to left
			moveX(-7f);
			//if inRange() returns true call countPlayerRubbish
			if(inRange(player) && (!tradeComplete)) {
				countPlayerRubbish(fieldRubbish);
			}//end if
		}else {
			//else move downwards
			moveY(2f);
			//Once Checkpoint enters the display(y == 0) start playing the shipSound
			if (getY() > 0) {
				shipSound.play(true);
			//If checkpoint is located outside the display(y < 0), call quit on shipSound
		}else if (getY() < 0) {
				shipSound.quit();
			}//endIF
		}//end if
	}//end move

	/**
	 * Detects if player is in Y range of Checkpoint.<br>
	 * Returns true or false depending on if the players y value - 50 is less or equal Checkpoints position.
	 * <br>The value -50 is so that the Checkpoint does not touch the bottom of the screen when moving left
	 * or blocks out the player ship.
	 * @param player - game instance of PlayerShip 
	 * @return true or false
	 */
	public boolean detectPlayer(PlayerShip player) {
		return (player.getY() - 50 <= getY());
	}//end detectPlayer

	/**
	 * returns if the player is in range of the checkpoint,<br>
	 * Is called once the checkpoint is moving left along the x axis
	 * <br>returns true if checkpoint has passed player on the x axis and false if not
	 * @param player game instance of PlayerShip 
	 * @return true or false
	 */
	public boolean inRange(PlayerShip player) {
		return (player.getX() >= getX());
	}//end inRange

	/**
	 * Counts all collected rubbish and adds it to the score
	 * @param fieldRubbish - the rubbish array held in model
	 */
	public void countPlayerRubbish(ArrayList<Rubbish> fieldRubbish) {
		//Initialize count as 0
		int count = 0;

		ArrayList<Rubbish> rbhToRemove = new ArrayList<Rubbish>();

		//add to the count for each item of the fieldRubbish arrayList that is set to collected
		//remove all collected rubbish from the rubbish array, since the checkpoint is "picking them up from the player ship"
		for (int index = 0; index < fieldRubbish.size(); index++) {
			if(fieldRubbish.get(index).getCollected()) {
				count++;
				rbhToRemove.add(fieldRubbish.get(index));
			}//end if
		}//end for
		
		fieldRubbish.removeAll(rbhToRemove);

		//add the count to the score
		score += count;

		//if count is more than 0, play the sell sound, signaling that the player has sold rubbish to the Checkpoint
		if(count > 0)
		sellSound.play(false);
		
		tradeComplete = true;
	}//end countPlayerRubbish

}//end Checkpoint
