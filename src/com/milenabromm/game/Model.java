package com.milenabromm.game;

import static org.gillius.jalleg.binding.AllegroLibrary.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.gillius.jalleg.binding.AllegroLibrary.ALLEGRO_BITMAP;
import org.gillius.jalleg.framework.AllegroException;
import org.gillius.jalleg.framework.io.AllegroLoader;

/**
 * Stores all of the objects and information of the game, which can be used by the view display.
 * @author Milena Bromm 555851
 * @version 2.1
 */
public class Model {
	
//	Model Fields:
	
	private Random random;
	private int arrayMax;
	private int highScore;
	
	//Bitmaps and Images
	private ALLEGRO_BITMAP standardBackground;
	private ALLEGRO_BITMAP gBackground;
	private ALLEGRO_BITMAP highScoreBackground;
	private ALLEGRO_BITMAP playerImg;
	private ALLEGRO_BITMAP enemyShipImg;
	private ALLEGRO_BITMAP rubbishImg;
	private ALLEGRO_BITMAP merchImg;	
	private ALLEGRO_BITMAP playB;
	private ALLEGRO_BITMAP exitB;
	private ALLEGRO_BITMAP setupB;
	private ALLEGRO_BITMAP highScoreB;
	private ALLEGRO_BITMAP menuB;
	private ALLEGRO_BITMAP helpB;
	private ALLEGRO_BITMAP head;
	private ALLEGRO_BITMAP setupBack;
	private ALLEGRO_BITMAP keyRect;
	private ALLEGRO_BITMAP keyRectClicked;
	private ALLEGRO_BITMAP check;
	private ALLEGRO_BITMAP unchecked;
	private ALLEGRO_BITMAP tipImg;
	private ALLEGRO_BITMAP xImg;
	
	private Image header;
	private Image playBtn;
	private Image setupBtn;
	private Image highScoreBtn;
	private Image exitBtn;
	private Image menuBtn;
	private Image helpBtn;
	private Image background1;
	private Image background2;
	private Image hSBackground;
	private Image setupBackground;
	private Image keyBtn1;
	private Image keyBtn2;
	private Image keyBtn3;
	private Image checkBox;
	private Image xBtn;
	private Image toolTip;
	
	//file paths for sounds
	private String gameMusicP;
	private String gameStartP;
	private String laser1P;
	private String laser2P;
	private String moneyP;
	private String collectP;
	private String playerShipP;
	private String damageP;
	private String merchantSP;
	private String gameOverP;
	
	//game music and game over sound
	private Sound gameMusic;
	private Sound gameOverSound;	
	private Sound gameStart;
	
	//Main game object declaration:
	private PlayerShip player;
	private Checkpoint merchant;
	private ArrayList<Ship> enemyShips = new ArrayList<Ship>();
	private ArrayList<Rubbish> rubbish = new ArrayList<Rubbish>();
	
	//reused float values for display size and border of displays
	private float displayX = 1200;
	private float displayY = 700;
	private float leftBorder = 15;
	private float rightBorder = -60;
	
	//booleans
	private boolean gameOver = false;
	private boolean typing = false;
	private boolean soundOn = false;
	
	//HighScore instance
	private HighScore highScoreFile;
	
	//keyBindings
	private int leftKey = ALLEGRO_KEY_LEFT;
	private int rightKey = ALLEGRO_KEY_RIGHT;
	private int shootKey = ALLEGRO_KEY_SPACE;
	private String left = "Left", right = "Rigth", shoot = "Space";
	
	/**
	 * Model constructor, sets initial values
	 */
	public Model() {
		
		//loads allegro addons, to use allegro methods
		al_install_system(ALLEGRO_VERSION_INT, null);
		al_init_primitives_addon();
		al_init_image_addon();
		
		//creates the bitmaps from source location
		try {
			standardBackground = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/Background.jpg");
			gBackground = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/gameBackground.png");
			highScoreBackground = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/HSB.png");
			playerImg = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/player2.png");
			enemyShipImg = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/enemy.png");
			rubbishImg = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/rubbish.png");
			merchImg = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/MerchantX.png");
			playB = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/PlayB.png");
			exitB = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/ExitB.png");
			setupB = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/SetupB.png");
			highScoreB = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/HighScoreB.png");
			menuB = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/Rectangle_2.png");
			helpB = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/HelpB.png");
			head = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/Heading.png");
			setupBack = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/SetupBackground.png");
			keyRect = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/keyBinding.png");
			keyRectClicked = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/keyBindingClicked.png");
			check = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/Checked.png");
			unchecked = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/Unchecked.png");
			tipImg = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/ToolTip.png");
			xImg = AllegroLoader.loadBitmapFromClasspath("/com/milenabromm/img/XBtn.png");
			
		} catch (IOException e) {
			System.out.println("Image could not be located");
			System.exit(0);
			e.printStackTrace();
		} catch (AllegroException a) {
			System.out.println("Allegro image addon not installed");
			System.exit(0);
		}//end tryCatch
		
		//Initialise sound paths
		gameMusicP = "src/com/milenabromm/sounds/gameMusic.wav";
		laser1P = "src/com/milenabromm/sounds/laser1.wav";
		laser2P = "src/com/milenabromm/sounds/laser2.wav";
		moneyP = "src/com/milenabromm/sounds/money.wav";
		collectP = "src/com/milenabromm/sounds/collect.wav";
		playerShipP = "src/com/milenabromm/sounds/motherShip.wav";
		damageP = "src/com/milenabromm/sounds/damage.wav";
		merchantSP = "src/com/milenabromm/sounds/pShip.wav";
		gameOverP = "src/com/milenabromm/sounds/gameOver.wav";
		gameStartP = "src/com/milenabromm/sounds/GameStart.wav";
		
		//populate main game objects
		
		merchant = new Checkpoint(displayX-250, -displayY, 152, 110, merchImg, merchantSP, moneyP);
		player = new PlayerShip((displayX/2), displayY-120, 52, 78, 100, playerImg, laser2P,playerShipP, collectP, damageP);
				
		random = new Random(10);
		
		arrayMax = 10;

		populateArrayL(50, displayX-50, 5, 90, "Ship");
	
		arrayMax = 20;
		
		populateArrayL(50, displayX-20, 110, displayY-40, "Rubbish");
		
		//populates images
		background1 = new Image(-5, -(displayY *2), 1210, 1400, gBackground);
		background2 = new Image(-5, -2, 1210, 1400, gBackground);
		header = new Image((displayX/2)-302, 150, 608, 72, head);
		playBtn = new Image((displayX/2)-112, header.getBottomY() + 30, 224, 54, playB);
		setupBtn = new Image((displayX/2)-112, playBtn.getBottomY() + 20, 224, 54, setupB);
		highScoreBtn = new Image((displayX/2)-112, setupBtn.getBottomY() + 20, 224, 54, highScoreB);
		exitBtn = new Image((displayX/2)-112, highScoreBtn.getBottomY() + 20, 224, 54, exitB);
		menuBtn = new Image(69, 26, menuB);
		helpBtn = new Image(displayX - 60, 20, 48, 48, helpB);
		hSBackground = new Image(300, 10, 278, 283, highScoreBackground);
		setupBackground = new Image(100, 100, 375, 156, setupBack);
		keyBtn1 = new Image(setupBackground.getX() + 10, setupBackground.getMiddleY() - 34, 113, 34, keyRect);
		keyBtn2 = new Image(keyBtn1.getRightX() + 10, keyBtn1.getY(), 113, 34, keyRect);
		keyBtn3 = new Image(keyBtn2.getRightX() + 10, keyBtn2.getY(), 113, 34, keyRect);
		checkBox = new Image(keyBtn1.getMiddleX(), keyBtn1.getBottomY() + 20, 22, 22, check);
		toolTip = new Image(100, 50, 1002, 602, tipImg);
		xBtn = new Image(toolTip.getRightX() - 40, toolTip.getY() + 10, 22, 25, xImg);
		
		//High Score 
		highScoreFile = new HighScore();
		
		//game sounds
		gameMusic = new Sound(gameMusicP);
		gameOverSound = new Sound(gameOverP);
		gameStart = new Sound(gameStartP);
		
	}//end constructor
	
	//GETTERS AND SETTERS------------------------------
	
	/**
	 * returns rubbish array list
	 * @return rubbish - Model array list of Rubbish instances
	 */
	public ArrayList<Rubbish> getRubbish() {
		return rubbish;
	}//end getRubbish
	
	/**
	 * returns the enemyShips array list
	 * @return enemyShips  - Model array list of Ship instances
	 */
	public ArrayList<Ship> getEnemyShips() {
		return enemyShips;
	}//edn getEnemyShips

	/**
	 *@return player - Model instance of PlayerShip
	 */
	public PlayerShip getPlayer() {
		return player;
	}//end getPlayer
	
	/**
	 * returns the bitmap of the applications standard background
	 * @return standartBackground - used almost everywhere in the application but the game 
	 */
	public ALLEGRO_BITMAP getStdBackground() {
		return standardBackground;
	}//end getStdBackground
	
	/**
	 * returns background 1 of the game
	 * @return background1 - background image of game alternates between background1 and background2
	 */
	public Image getBackground1() {
		return background1;
	}// end getBackground1

	/**
	 * returns background 2 of the game
	 * @return background2 - background image of game alternates between background1 and background2
	 */
	public Image getBackground2() {
		return background2;
	}//end getBackground

	/**
	 * Returns the header shown in menu
	 * @return header - title of game: Rubbish Hunters In Space
	 */
	public Image getHeader() {
		return header;
	}//end getHeader
	
	/**
	 * @return toolTip - game manual stored as image
	 */
	public Image getToolTip() {
		return toolTip;
	}//end getToolTip

	/**
	 * @return playBtn - button image with text: "Play Game"
	 */
	public Image getPlayBtn() {
		return playBtn;
	}//end getPlayBtn

	/**
	 * @return setupBtn - button image with text: "Setup"
	 */
	public Image getSetupBtn() {
		return setupBtn;
	}//end setupBtn

	/**
	 * @return HighScoreBtn - button image with text: "View High Score"
	 */
	public Image getHighScoreBtn() {
		return highScoreBtn;
	}//end getHighScoreBtn

	/**
	 * @return exitBtn - button image with text: "View High Score"
	 */
	public Image getExitBtn() {
		return exitBtn;
	}//end getExitBtn

	/**
	 * @return menuBtn - button image no text
	 */
	public Image getMenuBtn() {
		return menuBtn;
	}//end getMenuBtn

	/**
	 * @return helpBtn - button image displaying a question mark
	 */
	public Image getHelpBtn() {
		return helpBtn;
	}//end getHelpBtn
	
	/**
	 * @return xBtn - button image displaying an x
	 */
	public Image getXBtn() {
		return xBtn;
	}//end getXBtn
	
	/**
	 * @return getKeyBtn1 - button image used in setup for key binding left
	 */
	public Image getKeyBtn1() {
		return keyBtn1;
	}//end getKeyBtn1
	
	/**
	 * @return getKeyBtn2 - button image used in setup for key binding right
	 */
	public Image getKeyBtn2() {
		return keyBtn2;
	}//end getKeyBtn2
	
	/**
	 * @return getKeyBtn3 - button image used in setup for key binding shoot
	 */
	public Image getKeyBtn3() {
		return keyBtn3;
	}//end getKeyBtn3
	
	/**
	 * @return checkBox - button image displaying as checkbox unchecked or checked
	 */
	public Image getCheckBox() {
		return checkBox;
	}//end getCheckBox
	
	/**
	 * if on is true checkbox appears checked, else checkbox appears unchecked
	 * @param on - determines if checkbox has been checked or not
	 */
	public void setCheckBox(boolean on) {
		if(on) {
			checkBox.setImage(check);
		}else {
			checkBox.setImage(unchecked);
		}//end if
	}//end setCheckBox
	
	/**
	 * Used in Setup. Changes the image of a keybtn. If a keybtn is active(on) the button colour changes
	 * if a keybtn is not active the image is set to the default colour
	 * @param on - determines if a keybtn has been clicked
	 * @param btnNum - determines which keyBtn was clicked
	 */
	public void setKeyBtn(boolean on, int btnNum) {
		ALLEGRO_BITMAP img = keyRect;
		
		if(on) {
			img = keyRectClicked;
		}//end if
		
		switch(btnNum) {
		case 1:
			keyBtn1.setImage(img);
			break;
		case 2:
			keyBtn2.setImage(img);
			break;
		case 3: 
			keyBtn3.setImage(img);
			break;
		}//end switch
		
	}//end setKeyBtn
	
	/**
	 * sets the key and key name based on move value:
	 * 1 changes key binding for left, 2 changes key binding for right, 3 changes the key binding for shoot 
	 * @param move - determines which key binding is changes
	 * @param key - key id: numeric value of a keyboard key
	 * @param keyName - String value of key
	 */
	public void setKeyBinding(int move, int key, String keyName) {
		switch(move) {
		case 1:
			left = keyName;
			leftKey = key;
			break;
		case 2:
			right = keyName;
			rightKey = key;
			break;
		case 3:
			shoot = keyName;
			shootKey = key;
			break;
		}//end switch
	}//end setKeyBinding
	
	/**
	 * @return leftKey - numeric value of keyboard key used for left movement
	 */
	public int getMove1Key() {
		return leftKey;
	}//end getMove1Key
	
	/**
	 * @return left - name of keyboard key used for left movement
	 */
	public String getMove1Name() {
		return left;
	}//end getMove1Name
	
	/**
	 * @return rightKey - numeric value of keyboard key used for right movement
	 */
	public int getMove2Key() {
		return rightKey;
	}//end getMove2Key
	
	/**
	 * @return right - name of keyboard key used for right movement
	 */
	public String getMove2Name() {
		return right;
	}//end getMove2Name
	
	/**
	 * @return shootKey - numeric value of keyboard key used for shoot movement
	 */
	public int getMove3Key() {
		return shootKey;
	}//end getMove3Key
	
	/**
	 * @return shoot - name of keyboard key used for shoot movement
	 */
	public String getMove3Name() {
		return shoot;
	}//end getMove3Name
		
	/**
	 * @return setupBackground - filled Rectangle on which setup components are placed
	 */
	public Image getSetupBackground() {
		return setupBackground;
	}//end getSetupBackground

	/**
	 * @return hSBackground - filled Rectangle on which high score components are placed
	 */
	public Image getHSBackground() {
		return hSBackground;
	}

	/**
	 * @return merchant - Model instance of Checkpoint
	 */
	public Checkpoint getCheckpoint() {
		return merchant;
	}//end getCheckpoint
	
	/**
	 * @return gameOver - true or false, is set to true when player health greater or equal to 0
	 */
	public Boolean getGameOver() {
		return gameOver;
	}//end getGameOver
	
	/**
	 * @return displayX - display width
	 */
	public float getDisplayX() {
		return displayX;
	}//end getDisplayX

	/**
	 * @return displayY - display height
	 */
	public float getDisplayY() {
		return displayY;
	}//end getDisplay
	
	/**
	 * @return typing - determines if the keyboard should be used to get String input of user
	 */
	public boolean getTyping() {
		return typing;
	}//end getTyping
	
	/**
	 * set typing to true or false based on value
	 * @param value - true or false
	 */
	public void setTyping(boolean value) {
		typing = value;
	}//end setTyping
	
	/**
	 * returns the high score from the high score file
	 * @return highScore - current high score
	 */
	public int getHighScore() {
		highScore = Integer.parseInt(highScoreFile.getHighScore());
		return highScore;
	}//end getHighScore
	
	/**
	 * puts a new high score at the top of the high score file
	 * @param name - name of new high score holder
	 */
	public void setHighScore(String name) {
		highScoreFile.writeScore(name, merchant.getScore());
	}//end setHighScore
	
	/**
	 * returns string containing text that is displayed in game, or in the game over screen
	 * @return String - text value displayed in game or game over
	 */
	public String getText() {
		if (!gameOver) {
		 return "SCORE: " + merchant.getScore() + " HIGHSCORE: " + getHighScore() + "  HEALTH:  " + player.getHealth();
		}else {
			return "YOUR SCORE:   " + merchant.getScore() + "  HIGHSCORE:  " + getHighScore();
		}//end if
	}//end getText
	
	
	//Model methods:
	
	/**
	 * sets all sounds used in the model on or off
	 * @param on - true or false
	 */
	public void setSound(boolean on) {
		soundOn = on;
		
		merchant.setSoundOnOff(on);
		player.setSoundOnOff(on);
		gameMusic.setVolume(on);
		gameOverSound.setVolume(on);
		gameStart.setVolume(on);
		
		for (int count = 0; count < enemyShips.size(); count++) {
			enemyShips.get(count).setSoundOnOff(on);
		}//end for
		
		if(!on) {
			gameMusic.quit();
		}else {
			gameMusic.play(on);
		}//end if
		
	}//end setSound
	
	
	//OTHER METHODS----------------------------------
	
	/**
	 * plays the game music on loop
	 */
	public void playMusic() {
		gameMusic.play(true);
	}//end playMusic
	
	
	
	/**
	 * quits playing the player and checkpoint ship sound and plays game over sound
	 */
	public void playGameOverScore() {
		player.quitShipSound();
		merchant.quitShipSound();
		gameOverSound.play(false);
	}//end playGameOverScore
	
	/**
	 * plays game start sound once
	 */
	public void playGameStartScore() {
		gameStart.play(false);
	}//end playGameStartScore
	
	/**
	 * Prepares the model for a new game, sets all in game used objects back to their state at game start
	 */
	public void resetModel() {
		gameOver = false;
		
		merchant = new Checkpoint(displayX-250, -displayY, 152, 110, merchImg, merchantSP, moneyP);
		player = new PlayerShip((displayX/2), displayY-120, 52, 78, 100, playerImg, laser2P,playerShipP, collectP, damageP);
				
		random = new Random(10);

		enemyShips.clear();
		
		arrayMax = 10;
		
		populateArrayL(50, displayX-50, 5, 90, "Ship");
	
		arrayMax = 20;
		
		rubbish.clear();
		
		populateArrayL(50, displayX-20, 110, displayY-40, "Rubbish");
		
		background1.setY(-(displayY *2));
		background2.setY(-2);
		
	}//end resetModel
	
	
	/**
	 * Populates a SpaceShooterObject array list of either Rubbish or Ships
	 * taking in parameters for min and max x position, min and max y positions and the type of object(ship/rubbish)
	 * @param minX - minimum x value
	 * @param maxX - maximum x value
	 * @param minY - minimum y value 
	 * @param maxY - maximum y value
	 * @param type - type of array list to be populated either "Ship" or "Rubbish"
	 */
	public void populateArrayL(float minX, float maxX, float minY, float maxY, String type) {
		//Array list to hold new array list objects before adding them to the list
		ArrayList<SpaceShooterObject> list = new ArrayList<SpaceShooterObject>();
		int arraySize = 0;
		
		if(type.equals("Ship")) {
			list.addAll(enemyShips);
			arraySize = list.size();
			enemyShips.clear();
		}else {
				list.addAll(rubbish);
				arraySize = list.size();
				rubbish.clear();
		}
		//x and y positions of new object
		float x = 20;
		float y = 20;
		//repeat value to identify if loop must be repeated
		boolean repeat = true;
		//boolean to tell if an x and y position already in the list
		boolean positionFound = false;
		arrayMax = arrayMax + arraySize;
		
		//Iterates through the list positions until the set arrayMax value and initialises a new object at each position
		for (int count = arraySize; count < arrayMax; count ++) {
			
			//set a random value for x position and y position
			//repeats finding a these values if x and y positions already exist in the list
			while(repeat) {
				//generates random number up to 1500 (size of display)
				x = (100 * random.nextInt(15));
				//ships should be more together than rubbish, which is why the y position of Ship is only multiplied by 100
				//since rubbish should be more spread apart on the playing field the random number for y is multiplied by more
				if(type.equals("Ship")) {
					y = (150 * random.nextFloat());
				}else {
				y = (1000 * random.nextFloat());
				}//end if
				//y is minimum y value plus y to ensure it is always more than the parsed in minimum value
				y = minY + y;
				
				//if x and y values are in range and the list position is greater than 0, checks if the x and y values already exist in list.
				//if yes sets repeat to true and generates values again. if values are not within range, repeat is also set to true
				if ((x > minX && x < maxX) && (y > minY && y < maxY)){
					if (count > 0) {
						for (int position = 0; position < count; position++) {
							if (x >= list.get(position).getX()-70 && x <= list.get(position).getX() + 70 
									&& y >= list.get(position).getY()-70 && y <= list.get(position).getY()+ 90){
								positionFound = true;
								}//end if
							}//end for 
						
						if (!positionFound) {
							repeat = false;
						}//end if
						positionFound = false;
					}else {
							repeat = false;
					}//end if
				}//end if
			}//end while

			//sets repeat to true again for the next position and adds ship or rubbish objects to list depending on set type
			repeat = true;
			
			if (type.equals("Ship")) {
				list.add(new Ship(x, y, 42, 58, 50, enemyShipImg, laser1P));
			}else if(type.equals("Rubbish")){
				list.add(new Rubbish(x, y, 22, 18, rubbishImg));
			}//end if
			
		}//end for 
		
		//adds the new list to the model lists depending on type
		for(SpaceShooterObject ob: list) {
			if(ob instanceof Ship) {
				enemyShips.add((Ship) ob);
			}else {
				rubbish.add((Rubbish) ob);
			}//end if
			

		}//end for each
		
	}//end populateArrayList
	
	/**
	 * 	repopulates an out of range board which has the dimensions x: displayX and y: -displayY
	 *	Out of range field is repopulated and updatePositions() will slowly move objects into the view display
	 *	since Y position will slowly get positive
	 */
	public void repopulateEmptySpace() {
		
		//If no merchant is in out of bound fields or on the display
		//set the merchant position into out of range field
		if (!(merchant.getY() < 0 || (merchant.getX() > 0 && merchant.getX() < displayX))) {
			merchant.setY(-displayY);
			merchant.setX(displayX-250);
			merchant.resetTradeComplete();
		}
		
		//remove objects that left the field
	for (int count = 0; count < enemyShips.size(); count++) {
			if (enemyShips.get(count).getY() > displayY) {
			enemyShips.remove(count);
			}//end if
	
		//removes all rubbish which has left the field or has been collected by an enemy ship	
		for(int index = 0; index < rubbish.size(); index++) {
			if (rubbish.get(index).getY() > displayY || 
					enemyShips.get(count).getCollision(rubbish.get(index))) {
				rubbish.remove(index);
			}//end if
		}//end for
		
		}//end outer for

		//Populates a the rubbish that is out of range, by subtracting the number of out of range rubbish from the
	// arrayMax of rubbish elements that can be out of range.
	//then repopulates the out of range field with amount of rubbish still allowed to be out of range
		arrayMax = 35;
		int outOfRangeRbh = 0;
		for (Rubbish rbh: rubbish) {
			if(rbh.getY() < 0) {
				outOfRangeRbh++;
			}//end if
		}//end for
		
		if(outOfRangeRbh < arrayMax) {
			arrayMax -= outOfRangeRbh;
			populateArrayL(leftBorder, (displayX - rightBorder), -displayY, 0, "Rubbish");
		}//end if
		
		//populates a new wave of enemyShips in the out of range field
		//logic is the same as described above for the rubbish
		arrayMax = 12;
		int outOfRangeShips = 0;
		for (Ship enemy: enemyShips) {
			if(enemy.getY() < 0) {
				outOfRangeShips++;
			}//end if
		}//end for
		
		if(outOfRangeShips < arrayMax) {
			arrayMax -= outOfRangeShips;
				populateArrayL(leftBorder, (displayX - rightBorder), -displayY, (displayY-60), "Ship");
		}//end if
	}//end repopulateModel
	
/**
 * calls repopulateModel, and updates the positions of the moving game objects which are triggered by the timer, player up and down movement,
 * enemy, checkpoint, missiles and rubbish movement. It sets the game over variable if the player lost.
 * @return this 
 */
	public Model updatePositions() {
		repopulateEmptySpace();
		
		if(background1.getY() > 1390) {
			background1.setY(-1390);
		}else {
			background1.move();
		}//end if

		if(background2.getY() > 1390) {
			background2.setY(-1390);
		}else {
			background2.move();	
		}//end if
		
		//moves all enemy ships and their missiles, removes enemy ships if they have lost all health
		ArrayList<Ship> shipsToRemove = new ArrayList<Ship>();
		
		for (int count = 0; count < enemyShips.size(); count++) {
			if(enemyShips.get(count).getHealth() > 0) {
			enemyShips.get(count).setSoundOnOff(soundOn);
			enemyShips.get(count).move();
			enemyShips.get(count).updateMissiles(displayX, displayY);
			enemyShips.get(count).moveMissiles(3, player);
			}else {
				shipsToRemove.add(enemyShips.get(count));
			}//end if
		}//end for
		
		enemyShips.removeAll(shipsToRemove);
		
		//moves rubbish
		for (int count = 0; count < rubbish.size(); count++) {
			rubbish.get(count).move(player);
		}//end for
		
		//moves merchant 
		merchant.move(player, rubbish);
		
		//updates player missiles, sets rubbish that the player has collided with to collected
		player.updateMissiles(displayX, displayY);
		player.moveMissiles(-15, enemyShips);
		player.detectRubbish(rubbish);
		player.move();
		//if the players health is 0 or less sets gameOver to true and
		//sets the typing variable to true of current game score is more than the high score. 
		//The typing variable is used by the view to check if it should take in a name and set an new high score
		if (player.getHealth() <= 0) {
			gameOver = true;
			playGameOverScore();
			player.quitShipSound();
			if (merchant.getScore() > getHighScore()) {
				typing = true;
			}//end if			
		}//end if		
		return this;
	}//end updatePositions

	/**
	 * takes input and moves the player ship
	 * @param move - movement input for PlayerShip move method
	 * @return this 
	 */
	public Model controlPlayerShip(int move) {
		player.move(move, displayX);
		return this;
	}//end controlPlayerShip
	
}//end Model
