package com.milenabromm.game;

/**
 * holds the main method and<br>mediates between the Model and the View class<br>
 * @author Milena Bromm 555851
 * @version 2.1
 */
public class Controller {

// Controller Global variables:
	
	//Instance of model which holds the main data of the game
	private Model gameModel;
	//Instance of view which holds the GUI elements of the game
	private View view;
	//Initialization of the menu options using constant values
	private final int MENU = 0, PLAY = 1, GAME_OVER = 2, SETUP = 3, HIGH_SCORE = 4, EXIT = 5;
	private final int [] MENU_OPTIONS = {MENU, PLAY, GAME_OVER, SETUP, HIGH_SCORE, EXIT};
	//Choice is instantiated with menu option 0 at controller instantiation
	private int choice = MENU_OPTIONS[0];
	private boolean soundOn = true;

	/** 
	 *Starts the application.
	 *<br>Displays the different game windows until the player chooses to exit.
	 */
	public void start(){

		//Display the view until the player input is Exit
		do {
			//IF choice is equal to play game
			if (choice == MENU_OPTIONS[1]) {
				//call resetModel
				resetModel();				
			}//endIF
			//start playing the background music
			gameModel.playMusic();
			
			//SET choice equal to views displayView function using choice as the parameter 
			choice = view.displayView(choice);
			//Stop playing the player and checkpoint ship sound
			gameModel.getPlayer().quitShipSound();
			gameModel.getCheckpoint().quitShipSound();
		}while (choice != EXIT);

		//Destroy display
		view.destroyDisplay();
		//Exit the program
		System.exit(0);
	}//end start()

	/**
	 * @return MENU_OPTIONS - final int array of the different menu options
	 */
	public int [] getMenuOptions() {
		return MENU_OPTIONS;
	}//end getMenuOptions()
	
	/**
	 * Initializes the gameModel as a new instance of Model
	 * <br>and passes it to the view
	 */
	public void resetModel() {
		gameModel.resetModel();
		gameModel.setSound(soundOn);
		view.updatePlayingField(gameModel);
	}//end resetModel

	/**
	 *Calls updatePositions() on model and passes the updated model to the view
	 */
	public void updatePlayingField() {
		gameModel = gameModel.updatePositions();
		view.updatePlayingField(gameModel);
	}//end updatePlayingField

	/**
	 * Takes input for int move and calls controllPlayerShip() on Model using move, 
	 * <br>then passes the updated model to the view
	 * @param move - defines the move action in PlayerShip move(int, float) method
	 */
	public void controlPlayerShip(int move) {
		gameModel = gameModel.controlPlayerShip(move);
		view.updatePlayingField(gameModel);
	}//end controlPlayerShip
	
	/**
	 * Calls setHighScore() on Model, using String input
	 * @param input - String input is set by the keyboard inside view
	 */
	public void updateHighScore(String input) {
		gameModel.setHighScore(input);
	}
	
	/**
	 * sets game sound on or off
	 */
	public void setSound() {
		if(soundOn) {
			soundOn = false;
		}else {
			soundOn = true;
		}//end if
		gameModel.setSound(soundOn);
		gameModel.setCheckBox(soundOn);
		view.updatePlayingField(gameModel);
	}//end setSound
	
	/**
	 * calls models setKeyRect(boolean on, int btnNum), to make the keyBtn image respond to user interaction
	 * @param on - determines if key is active
	 * @param btnNum - determines which key has been interacted with
	 */
	public void setKeyActive(boolean on, int btnNum) {
		gameModel.setKeyBtn(on, btnNum);
	}//end setKeyActive
	
	/**
	 * 
	 * calls models setKeyBinding(int move, int key, String keyName)
	 * calls setKeyActive(boolean on, int btnNum) to set key interacted with back to default image
	 * @param move - determines which key binding is changes
	 * @param key - key id: numeric value of a keyboard key
	 * @param keyName - String value of key
	 */
	public void setKeyBinding(int move, int key, String keyName) {
		gameModel.setKeyBinding(move, key, keyName);
		setKeyActive(false, move);
	}//end setKeyBinding
	
	/**
	 * Controllers main method. Initializes the instances of the controller, model and view and then starts the game
	 * @param args
	 */
	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.gameModel = new Model();
		controller.view = new View(controller.gameModel, controller);
		controller.start();
	}//end main

}//end Controller
