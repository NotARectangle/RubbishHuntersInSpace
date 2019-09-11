package com.milenabromm.game;

import static org.gillius.jalleg.binding.AllegroLibrary.*;

import org.gillius.jalleg.binding.ALLEGRO_EVENT;
import org.gillius.jalleg.binding.ALLEGRO_FONT;
import org.gillius.jalleg.binding.ALLEGRO_KEYBOARD_STATE;
import org.gillius.jalleg.binding.ALLEGRO_MOUSE_STATE;
import org.gillius.jalleg.binding.ALLEGRO_TRANSFORM;

/**
 * Display the User Interface. View replaces the display functions from the game
 * and playing field CRC cards.
 * 
 * @author Milena Bromm
 * @version 2.1
 */
public class View {

	private Model model;
	private Controller controller;

	// Allegro variables:
	private ALLEGRO_DISPLAY display;
	private ALLEGRO_KEYBOARD_STATE keys;
	private ALLEGRO_FONT font;
	private ALLEGRO_MOUSE_STATE mouse;
	private ALLEGRO_EVENT_QUEUE queue;
	private ALLEGRO_TIMER timer;
	private ALLEGRO_EVENT event;
	private ALLEGRO_TRANSFORM trans;

	// variables for user menu choices
	private final int[] MENU_OPTIONS;
	private int choice;

	// booleans for main game loop
	private boolean redraw = true;
	private boolean run = true;

	// variables to allow for typing
	private Keyboard keyboard;
	private String input = "";

	int buttonNum = 0;

	private boolean error = false;
	private String errorMessage;

	private boolean toolTipActive = false;
	
	private int left = 0, right = 0, shoot = 0; 

	/**
	 * creates a new view instance using an instance of model and controller
	 * 
	 * @param model      - Instance of Model held in controller
	 * @param controller - Controller class of project
	 */
	public View(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
		initView();
		MENU_OPTIONS = controller.getMenuOptions();
		choice = MENU_OPTIONS[0];
	}// end View

	/**
	 * loads allegro addons to use allegro and sets up the display and view fields.
	 */
	public void initView() {

		al_install_system(ALLEGRO_VERSION_INT, null);
		al_init_primitives_addon();
		al_init_image_addon();
		al_install_keyboard();
		al_install_mouse();

		// creates an event queue
		queue = al_create_event_queue();
		// creates the game timer
		timer = al_create_timer(1.0 / 60.0);

		// creates the display
		display = al_create_display((int) model.getDisplayX(), (int) model.getDisplayY());
		// sets the button positions based of the display size values

		// sets up the queue for which events to register(timer, display, keyboard and
		// mouse)
		al_register_event_source(queue, al_get_timer_event_source(timer));
		al_register_event_source(queue, al_get_display_event_source(display));
		al_register_event_source(queue, al_get_keyboard_event_source());
		al_register_event_source(queue, al_get_mouse_event_source());

		event = new ALLEGRO_EVENT();
		keys = new ALLEGRO_KEYBOARD_STATE();
		mouse = new ALLEGRO_MOUSE_STATE();
		font = al_create_builtin_font();
		trans = new ALLEGRO_TRANSFORM();
		keyboard = new Keyboard();
	}// end initView

	/**
	 * displays the display content based on the current menu choice and then return
	 * the new user menu choice to the controller after it is finished with the
	 * current display
	 * 
	 * @param c - choice parameter
	 * @return choice - new user choice
	 */
	public int displayView(int c) {
		// sets choice to c(current user choice
		this.choice = c;

		// sets run to true after previous call of this method set run to false to exit
		// display loop
		run = true;

		// if choice equals "PLAY" starts to play the player ship sound
		if (choice == MENU_OPTIONS[1]) {
			model.getPlayer().playShipSound();
			model.playGameStartScore();
			left = model.getMove1Key();
			right = model.getMove2Key();
			shoot = model.getMove3Key();
		} // end if

		// starts timer
		al_start_timer(timer);

		// while run is true displays the current menu option
		while (run) {

			// sets event to the current event
			event.setType(Integer.TYPE);
			al_wait_for_event(queue, event);

			switch (event.type) {
			case ALLEGRO_EVENT_TIMER:
				// if even is the timer, and PLAY is being displayed, updates the playing field
				// until game over is true
				if (choice == MENU_OPTIONS[1] && model.getGameOver() == false && (!toolTipActive)) {
					controller.updatePlayingField();
				} else if (choice == MENU_OPTIONS[1] && model.getGameOver() == true) {
					choice = MENU_OPTIONS[2];
					run = false;
				} // end if
					// sets redraw to true whenever the timer runs out
				redraw = true;
				break;
			// For keyboard and mouse input outside of the game
			case ALLEGRO_EVENT_KEY_CHAR:
			case ALLEGRO_EVENT_MOUSE_BUTTON_DOWN:
				if (choice != MENU_OPTIONS[1])
					calculateMovement();
				break;
			// returns to menu when display is closed, if already in menu, closes game
			case ALLEGRO_EVENT_DISPLAY_CLOSE:
				run = false;
				if (choice != MENU_OPTIONS[0] && (!error)) {
					choice = MENU_OPTIONS[0];
				} else {
					choice = MENU_OPTIONS[5];
				} // end IF
				break;
			}// end switch

			if (redraw && al_is_event_queue_empty(queue)) {
				if (choice == MENU_OPTIONS[1]) {
					calculateMovement();
				} // end if
					// render called each time after timer runs out
				render();
				redraw = false;
			} // end if

		} // end while

		keys = new ALLEGRO_KEYBOARD_STATE();
		mouse = new ALLEGRO_MOUSE_STATE();

		return choice;

	}// end displayView

	/**
	 * destroys the display, for when application is closed
	 */
	public void destroyDisplay() {
		al_destroy_display(display);
		al_destroy_timer(timer);
		al_destroy_event_queue(queue);
	}// end destroyDisplay

	/**
	 * renders the display content based on choice input
	 */
	public void render() {

		// displays menu if choice = "MENU"
		if (choice == MENU_OPTIONS[0]) {
			renderMenu();
		} // end if

		// displays game if choice = "PLAY"
		if (choice == MENU_OPTIONS[1])
			renderGame();

		// displays setup options if choice = "Setup"
		if (choice == MENU_OPTIONS[3])
			renderSetup();

		// displays high score if choice = "High Score"
		if (choice == MENU_OPTIONS[4])
			renderHighScore();

		// displays game over screen if choice = "GAME_OVER"
		if (choice == MENU_OPTIONS[2])
			renderGameOver();

		// updates the display with the rendered content
		al_flip_display();
	}// end render

	/**
	 * draws components of the game onto the display
	 */
	public void renderGame() {

		// draws background
		al_draw_bitmap(model.getBackground1().getImage(), model.getBackground1().getX(), model.getBackground1().getY(),
				0);

		al_draw_bitmap(model.getBackground2().getImage(), model.getBackground2().getX(), model.getBackground2().getY(),
				0);

		// draws rubbish elements
		for (int count = 0; count < model.getRubbish().size(); count++) {
			al_draw_bitmap(model.getRubbish().get(count).getImage(), model.getRubbish().get(count).getX(),
					model.getRubbish().get(count).getY(), 0);
		} // end for

		// draws enemy ships and missiles
		for (int count = 0; count < model.getEnemyShips().size(); count++) {
			al_draw_bitmap(model.getEnemyShips().get(count).getImage(), model.getEnemyShips().get(count).getX(),
					model.getEnemyShips().get(count).getY(), 0);
			if (model.getEnemyShips().get(count).getMissileS()) {
				for (int index = 0; index < model.getEnemyShips().get(count).getMissiles().size(); index++)
					al_draw_filled_rectangle(model.getEnemyShips().get(count).getMissile(index).left(),
							model.getEnemyShips().get(count).getMissile(index).top(),
							model.getEnemyShips().get(count).getMissile(index).right(),
							model.getEnemyShips().get(count).getMissile(index).bottom(), al_map_rgb_f(1f, 0f, 0f));
			} // end if
		} // end for

		// draws player
		al_draw_bitmap(model.getPlayer().getImage(), model.getPlayer().getX(), model.getPlayer().getY(), 0);

		// draws player missiles
		if (model.getPlayer().getMissileS()) {
			for (int count = 0; count < model.getPlayer().getMissiles().size(); count++) {
				al_draw_filled_rectangle(model.getPlayer().getMissile(count).left(),
						model.getPlayer().getMissile(count).top(), model.getPlayer().getMissile(count).right(),
						model.getPlayer().getMissile(count).bottom(), al_map_rgb_f(1f, 0f, 0f));
			} // end for
		} // end if

		// draws checkpoint
		al_draw_bitmap(model.getCheckpoint().getImage(), model.getCheckpoint().getX(), model.getCheckpoint().getY(), 0);

		// draws the in game text
		al_draw_text(font, al_map_rgb_f(1f, 1f, 1f), 80f, 40f, ALLEGRO_ALIGN_LEFT, model.getText());

		al_draw_bitmap(model.getHelpBtn().getImage(), model.getHelpBtn().getX(), model.getHelpBtn().getY(), 0);

		if (toolTipActive) {
			renderToolTip();
		} // end if

	}// end gameScreen

	/**
	 * draws components on the game over screen
	 */
	public void renderGameOver() {

//	scales items on game over screen bigger		
		al_identity_transform(trans);
		al_scale_transform(trans, 2, 2);
		al_use_transform(trans);
		// draws background
		al_draw_scaled_bitmap(model.getStdBackground(), 0f, 0f, 1920f, 1080f, 0f, 0f, model.getDisplayX() / 2,
				model.getDisplayY() / 2, 0);
		float textY = 120;
		float textX = 400;
		// draws text
		al_draw_text(font, al_map_rgb_f(1f, 1f, 1f), textX, textY, ALLEGRO_ALIGN_CENTER, "GAME OVER!");

		// displays prompt to enter name and user input for the name if player beat high
		// score
		if (model.getCheckpoint().getScore() > model.getHighScore()) {
			al_draw_text(font, al_map_rgb_f(1f, 1f, 1f), textX, textY + 20, ALLEGRO_ALIGN_CENTER,
					"NEW HIGHSCORE! Please enter your name: ");
			al_draw_text(font, al_map_rgb_f(1f, 1f, 1f), textX, textY + 40, ALLEGRO_ALIGN_CENTER,
					"Press ENTER when done ");
			al_draw_text(font, al_map_rgb_f(1f, 1f, 1f), textX, textY + 60, ALLEGRO_ALIGN_CENTER, input);
			if (error) {
				al_draw_text(font, al_map_rgb_f(1f, 1f, 1f), textX, textY + 80, ALLEGRO_ALIGN_CENTER, errorMessage);
			}
			// else if score is lower or name input taken already display score and high
			// score
		} else if (model.getCheckpoint().getScore() <= model.getHighScore() || (!model.getTyping())) {
			al_draw_text(font, al_map_rgb_f(1f, 1f, 1f), textX, textY + 20, ALLEGRO_ALIGN_CENTER, model.getText());

			model.getMenuBtn().setX(textX - model.getMenuBtn().getWidth() / 2);
			model.getMenuBtn().setY(model.getDisplayY() / 3);
			al_draw_bitmap(model.getMenuBtn().getImage(), model.getMenuBtn().getX(), model.getMenuBtn().getY(), 0);
			al_draw_text(font, al_map_rgb_f(0f, 0f, 0f), model.getMenuBtn().getMiddleX(),
					model.getMenuBtn().getY() + 10, ALLEGRO_ALIGN_CENTER, "Menu");
		} // end if

	}// end gameOverScreen

	/**
	 * draws components on the setup screen
	 */
	public void renderSetup() {
		// scales components
		al_identity_transform(trans);
		al_scale_transform(trans, 2, 2);
		al_use_transform(trans);

		// sets menu button position
		model.getMenuBtn().setX(model.getSetupBackground().getMiddleX() - (model.getMenuBtn().getWidth() / 2));
		model.getMenuBtn().setY(model.getSetupBackground().getBottomY() + 10);

		al_draw_scaled_bitmap(model.getStdBackground(), 0f, 0f, 1920f, 1080f, 0f, 0f, model.getDisplayX() / 2,
				model.getDisplayY() / 2, 0);
		al_draw_bitmap(model.getSetupBackground().getImage(), model.getSetupBackground().getX(),
				model.getSetupBackground().getY(), 0);

		// draws buttons
		al_draw_bitmap(model.getMenuBtn().getImage(), model.getMenuBtn().getX(), model.getMenuBtn().getY(), 0);

		al_draw_bitmap(model.getKeyBtn1().getImage(), model.getKeyBtn1().getX(), model.getKeyBtn1().getY(), 0);
		al_draw_bitmap(model.getKeyBtn2().getImage(), model.getKeyBtn2().getX(), model.getKeyBtn2().getY(), 0);
		al_draw_bitmap(model.getKeyBtn3().getImage(), model.getKeyBtn3().getX(), model.getKeyBtn3().getY(), 0);
		al_draw_bitmap(model.getCheckBox().getImage(), model.getCheckBox().getX(), model.getCheckBox().getY(), 0);

		// draws setup text
		al_draw_text(font, al_map_rgb_f(0f, 0f, 0f), model.getSetupBackground().getMiddleX(),
				model.getSetupBackground().getY() + 10, ALLEGRO_ALIGN_CENTER, "SETUP");
		al_draw_text(font, al_map_rgb_f(0f, 0f, 0f), model.getKeyBtn1().getX(), model.getKeyBtn1().getY() - 10,
				ALLEGRO_ALIGN_LEFT, "Key Bindings:");
		al_draw_text(font, al_map_rgb_f(0f, 0f, 0f), model.getKeyBtn1().getX(), model.getKeyBtn1().getBottomY() + 20,
				ALLEGRO_ALIGN_LEFT, "Sound:");
		al_draw_text(font, al_map_rgb_f(0f, 0f, 0f), model.getMenuBtn().getMiddleX(), model.getMenuBtn().getY() + 10,
				ALLEGRO_ALIGN_CENTER, "Menu");

		// draw text on key binding buttons
		al_draw_text(font, al_map_rgb_f(0f, 0f, 0f), model.getKeyBtn1().getX() + 5,
				model.getKeyBtn1().getMiddleY() - 10, ALLEGRO_ALIGN_LEFT, "Left:" + model.getMove1Name());
		al_draw_text(font, al_map_rgb_f(0f, 0f, 0f), model.getKeyBtn2().getX() + 5,
				model.getKeyBtn2().getMiddleY() - 10, ALLEGRO_ALIGN_LEFT, "Right:" + model.getMove2Name());
		al_draw_text(font, al_map_rgb_f(0f, 0f, 0f), model.getKeyBtn3().getX() + 5,
				model.getKeyBtn3().getMiddleY() - 10, ALLEGRO_ALIGN_LEFT, "Shoot:" + model.getMove3Name());

		if (error) {
			al_draw_text(font, al_map_rgb_f(0f, 0f, 0f), model.getKeyBtn2().getMiddleX(),
					model.getKeyBtn2().getBottomY() + 5, ALLEGRO_ALIGN_CENTER, errorMessage);
		} // end if

	}// end renderSetup

	/**
	 * draws components of view high score screen
	 */
	public void renderHighScore() {
//		scales items on screen bigger		
		al_identity_transform(trans);
		al_scale_transform(trans, 2, 2);
		al_use_transform(trans);

		// sets menu button position
		model.getMenuBtn().setX(model.getHSBackground().getMiddleX() - (model.getMenuBtn().getWidth() / 2));
		model.getMenuBtn().setY(model.getHSBackground().getBottomY() + 10);

		al_draw_scaled_bitmap(model.getStdBackground(), 0f, 0f, 1920f, 1080f, 0f, 0f, model.getDisplayX() / 2,
				model.getDisplayY() / 2, 0);
		al_draw_bitmap(model.getHSBackground().getImage(), model.getHSBackground().getX(),
				model.getHSBackground().getY(), 0);
		al_draw_bitmap(model.getMenuBtn().getImage(), model.getMenuBtn().getX(), model.getMenuBtn().getY(), 0);

		HighScore highScore = new HighScore();

		al_draw_multiline_text(font, al_map_rgb_f(0f, 0f, 0f), model.getHSBackground().getX() + 5,
				model.getHSBackground().getY() + 10, 300, 20, ALLEGRO_ALIGN_LEFT, highScore.getTopHighScores());
		al_draw_text(font, al_map_rgb_f(0f, 0f, 0f), model.getMenuBtn().getMiddleX(), model.getMenuBtn().getY() + 10,
				ALLEGRO_ALIGN_CENTER, "Menu");

	}// end renderHighScore

	/**
	 * draws the components of the menu
	 */
	public void renderMenu() {
		// resets transformation to scale back from game over screen
		al_identity_transform(trans);
		al_scale_transform(trans, 1, 1);
		al_use_transform(trans);

		// draws background
		al_draw_scaled_bitmap(model.getStdBackground(), 0f, 0f, 1920f, 1080f, 0f, 0f, model.getDisplayX(),
				model.getDisplayY(), 0);

		al_draw_bitmap(model.getHeader().getImage(), model.getHeader().getX(), model.getHeader().getY(), 0);

		// draws buttons
		al_draw_bitmap(model.getPlayBtn().getImage(), model.getPlayBtn().getX(), model.getPlayBtn().getY(), 0);
		al_draw_bitmap(model.getSetupBtn().getImage(), model.getSetupBtn().getX(), model.getSetupBtn().getY(), 0);
		al_draw_bitmap(model.getHighScoreBtn().getImage(), model.getHighScoreBtn().getX(),
				model.getHighScoreBtn().getY(), 0);
		al_draw_bitmap(model.getExitBtn().getImage(), model.getExitBtn().getX(), model.getExitBtn().getY(), 0);
		al_draw_bitmap(model.getHelpBtn().getImage(), model.getHelpBtn().getX(), model.getHelpBtn().getY(), 0);

		if (toolTipActive) {
			renderToolTip();
		} // end if

	}// end displayMenu

	/**
	 * draws the components of the toolTip
	 */
	public void renderToolTip() {

		al_draw_bitmap(model.getToolTip().getImage(), model.getToolTip().getX(), model.getToolTip().getY(), 0);
		al_draw_bitmap(model.getXBtn().getImage(), model.getXBtn().getX(), model.getXBtn().getY(), 0);
	}// end rendertoolTip

	/**
	 * translates the keyboard and mouse movement in the menu and in the game
	 */
	public void calculateMovement() {
		al_get_keyboard_state(keys);
		al_get_mouse_state(mouse);

		// if toolTip is displayed
		if (toolTipActive) {
			if (al_mouse_button_down(mouse, 1) && mouseOverXBtn())
				toolTipActive = false;
		} // end if

		// if Menu is displayed
		if (choice == MENU_OPTIONS[0]) {
			if (toolTipActive) {
				if (al_mouse_button_down(mouse, 1) && mouseOverXBtn())
					toolTipActive = false;
			} else {
				if (al_mouse_button_down(mouse, 1) && mouseOverPlayBtn()) {
					run = false;
					choice = MENU_OPTIONS[1];
				} // end if

				if (al_mouse_button_down(mouse, 1) && mouseOverSetupBtn()) {
					run = false;
					choice = MENU_OPTIONS[3];
				} // end if

				if (al_mouse_button_down(mouse, 1) && mouseOverHighScoreBtn()) {
					run = false;
					choice = MENU_OPTIONS[4];
				} // end if

				if (al_mouse_button_down(mouse, 1) && mouseOverExitBtn()) {
					run = false;
					choice = MENU_OPTIONS[5];
				} // end if

				if (al_mouse_button_down(mouse, 1) && mouseOverHelpBtn()) {
					toolTipActive = true;
				} // end if
			} // end if
		} // end if

		// if the game is displayed:
		if (choice == MENU_OPTIONS[1] && (!model.getGameOver())) {
			if (toolTipActive) {
				if (al_mouse_button_down(mouse, 1) && mouseOverXBtn())
					toolTipActive = false;
			} else {
				// if A or left is pressed move player to the left
				if (al_key_down(keys, left)) {
					controller.controlPlayerShip(1);
				}

				// if D or right is pressed move player to the right
				if (al_key_down(keys, right)) {
					controller.controlPlayerShip(2);
				} // end if

				// if space or left mouse button are pressed, shoot a missile
				if (al_key_down(keys, shoot) || al_mouse_button_down(mouse, 1)) {
					controller.controlPlayerShip(3);
				} // end if

				if (al_mouse_button_down(mouse, 1) && mouseOverHelpBtn()) {
					toolTipActive = true;
				} // end if

			} // end if
				// if game over is reached, reset the keyboard state and mouse state so that
				// they don't influence the game over screen

//		} else if (choice == MENU_OPTIONS[1] && (model.getGameOver())) {
//			keys = new ALLEGRO_KEYBOARD_STATE();
//			mouse = new ALLEGRO_MOUSE_STATE();
		} // end if

		// if game over screen is displayed and player has finished typing name(if applicable)
		if (choice == MENU_OPTIONS[2] && (!model.getTyping())) {
			if (al_mouse_button_down(mouse, 1) && mouseOverMenuBtn()) {
				run = false;
				choice = MENU_OPTIONS[0];
				mouse = new ALLEGRO_MOUSE_STATE();
			} // end if
		} // end for

		//if game over screen is displayed and player is prompted to enter a name
		if (choice == MENU_OPTIONS[2] && model.getTyping()) {
			typing();
		} // endIf

		//if setup is displayed
		if (choice == MENU_OPTIONS[3]) {

			//if no key binding is selected
			if (buttonNum == 0) {

				if (al_mouse_button_down(mouse, 1) && mouseOverKeyBtn1()) {
					buttonNum = 1;

				} // end if

				if (al_mouse_button_down(mouse, 1) && mouseOverKeyBtn2()) {
					buttonNum = 2;
				} // end if

				if (al_mouse_button_down(mouse, 1) && mouseOverKeyBtn3()) {
					buttonNum = 3;
				} // end if

				controller.setKeyActive(true, buttonNum);

				if (al_mouse_button_down(mouse, 1) && mouseOverCheckbox()) {
					controller.setSound();
				} // end if

				if (al_mouse_button_down(mouse, 1) && mouseOverMenuBtn()) {
					if (model.getMove1Name().equals(model.getMove2Name())
							|| model.getMove1Name().equals(model.getMove3Name())
							|| model.getMove2Name().equals(model.getMove3Name())) {
						error = true;
						errorMessage = "Key Bindings must be unique!";
					} else {
						error = false;
						run = false;
						choice = MENU_OPTIONS[0];
					} // end IF
				} // end if

			} else {
				//if a key binding has been selected
				switch (buttonNum) {
				case 1:
					typing(model.getMove1Key(), model.getMove1Name());
					buttonNum = 0;
					break;
				case 2:
					typing(model.getMove2Key(), model.getMove2Name());
					buttonNum = 0;
					break;
				case 3:
					typing(model.getMove2Key(), model.getMove2Name());
					buttonNum = 0;
					break;
				}//end switch
			}//end if

		} // end if

		//if view high score is displayed
		if (choice == MENU_OPTIONS[4]) {
			if (al_mouse_button_down(mouse, 1) && mouseOverMenuBtn()) {
				run = false;
				choice = MENU_OPTIONS[0];
			} // end if
		}//end if

	}// end calculateMovement

	/**
	 * takes a character from the current keyboard state and adds it to the input
	 * until enter is pressed, if backspace is pressed deletes a character from the
	 * input
	 */
	public void typing() {
		int max = 10;
		char c;
		c = keyboard.type(keys);
		error = false;

		switch (c) {
		case '1':
			if (input.length() > 0) {
				input = input.substring(0, input.length() - 1);
			}//end if
			break;
		case '2':
			if (input.length() > 0) {
				model.setTyping(false);
				controller.updateHighScore(input);
				input = "";
			} else {
				error = true;
				errorMessage = "No name entered";
			} // end if
			break;
		case '3':
			if (input.length() < 10) {
				input = input + " ";
			} else {
				error = true;
				errorMessage = "Name to long";
			} // end if
			break;
		case '4':
		case '5':
		case '6':
		case '7':
		case '0':
			error = true;
			errorMessage = "Invalid character";
			break;
		default:
			if (input.length() < max) {
				input = input + keyboard.type(keys);
			} else {
				error = true;
				errorMessage = "Name to long";
			} // end if

		}// end switch

	}// end typing

	/**
	 * Gets a key and a key name from the current pressed key and calls setKeybinding(int move, int key, String keyName)
	 * on the controller. If the pressed key is out of range of the accepted key, 
	 * an error is displayed and values remain unchanged.
	 * @param key - numeric value of key
	 * @param keyName - name for key
	 */
	public void typing(int key, String keyName) {

		char c;
		c = keyboard.type(keys);
		error = false;

		switch (c) {
		case '1':
			keyName = "Backspace";
			key = keyboard.getKey(keys);
			break;
		case '2':
			keyName = "Enter";
			key = keyboard.getKey(keys);
			break;
		case '3':
			keyName = "Space";
			key = keyboard.getKey(keys);
			break;
		case '4':
			keyName = "Left";
			key = keyboard.getKey(keys);
			break;
		case '5':
			keyName = "Right";
			key = keyboard.getKey(keys);
			break;
		case '6':
			keyName = "Up";
			key = keyboard.getKey(keys);
			break;
		case '7':
			keyName = "Down";
			key = keyboard.getKey(keys);
			break;
		case '0':
			error = true;
			errorMessage = "Invalid character";
			break;
		default:
			keyName = Character.toString(keyboard.type(keys));
			key = keyboard.getKey(keys);
		}// end switch

		controller.setKeyBinding(buttonNum, key, keyName);

	}// end typing2

	/**
	 * returns true if mouse position is above play game button
	 * @return true or false
	 */
	public boolean mouseOverPlayBtn() {
		return mouse.x >= model.getPlayBtn().getX() && mouse.x < model.getPlayBtn().getRightX()
				&& mouse.y >= model.getPlayBtn().getY() && mouse.y < model.getPlayBtn().getBottomY();
	}// end mouseOverPlayBtn

	/**
	 * returns true if mouse position is above setup button
	 * 
	 * @return true or false
	 */
	public boolean mouseOverSetupBtn() {
		return mouse.x >= model.getSetupBtn().getX() && mouse.x < model.getSetupBtn().getRightX()
				&& mouse.y >= model.getSetupBtn().getY() && mouse.y < model.getSetupBtn().getBottomY();
	}// end mouseOverSetupBtn

	/**
	 * returns true if mouse position is above high score button
	 * 
	 * @return true or false
	 */
	public boolean mouseOverHighScoreBtn() {
		return mouse.x >= model.getHighScoreBtn().getX() && mouse.x < model.getHighScoreBtn().getRightX()
				&& mouse.y >= model.getHighScoreBtn().getY() && mouse.y < model.getHighScoreBtn().getBottomY();
	}// end mouseOverHighScoreBtn

	/**
	 * returns true if mouse position is above exit button
	 * 
	 * @return true or false
	 */
	public boolean mouseOverExitBtn() {
		return mouse.x >= model.getExitBtn().getX() && mouse.x < model.getExitBtn().getRightX()
				&& mouse.y >= model.getExitBtn().getY() && mouse.y < model.getExitBtn().getBottomY();
	}// end mouseOverExitBtn

	/**
	 * returns true if mouse position is above help button 
	 * @return true or false
	 */
	public boolean mouseOverHelpBtn() {
		return mouse.x >= model.getHelpBtn().getX() && mouse.x < model.getHelpBtn().getRightX()
				&& mouse.y >= model.getHelpBtn().getY() && mouse.y < model.getHelpBtn().getBottomY();
	}// end mouseOverHelpBtn

	/**
	 * returns true if mouse position is above help button
	 * 
	 * @return true or false
	 */
	public boolean mouseOverXBtn() {
		return mouse.x >= model.getXBtn().getX() && mouse.x < model.getXBtn().getRightX()
				&& mouse.y >= model.getXBtn().getY() && mouse.y < model.getXBtn().getBottomY();
	}// end mouseOverHelpBtn

	/**
	 * returns true if mouse position is above menu button
	 * 
	 * @return true or false
	 */
	public boolean mouseOverMenuBtn() {
		return mouse.x >= (model.getMenuBtn().getX() * 2) && mouse.x < (model.getMenuBtn().getRightX() * 2)
				&& mouse.y >= (model.getMenuBtn().getY() * 2) && mouse.y < (model.getMenuBtn().getBottomY() * 2);
	}// end mouseOverMenuBtn

	/**
	 * returns true if mouse position is above checkbox
	 * 
	 * @return true or false
	 */
	public boolean mouseOverCheckbox() {
		return mouse.x >= (model.getCheckBox().getX() * 2) && mouse.x < (model.getCheckBox().getRightX() * 2)
				&& mouse.y >= (model.getCheckBox().getY() * 2) && mouse.y < (model.getCheckBox().getBottomY() * 2);
	}// end mouseOverCheckbox

	/**
	 * returns true if mouse position is above keybtn1
	 * 
	 * @return true or false
	 */
	public boolean mouseOverKeyBtn1() {
		return mouse.x >= (model.getKeyBtn1().getX() * 2) && mouse.x < (model.getKeyBtn1().getRightX() * 2)
				&& mouse.y >= (model.getKeyBtn1().getY() * 2) && mouse.y < (model.getKeyBtn1().getBottomY() * 2);
	}// end mouseOverKeyBtn1

	/**
	 * returns true if mouse position is above keybtn2
	 * @return true or false
	 */
	public boolean mouseOverKeyBtn2() {
		return mouse.x >= (model.getKeyBtn2().getX() * 2) && mouse.x < (model.getKeyBtn2().getRightX() * 2)
				&& mouse.y >= (model.getKeyBtn2().getY() * 2) && mouse.y < (model.getKeyBtn2().getBottomY() * 2);
	}// end mouseOverKeyBtn2

	/**
	 * returns true if mouse position is above keybtn3
	 * @return true or false
	 */
	public boolean mouseOverKeyBtn3() {
		return mouse.x >= (model.getKeyBtn3().getX() * 2) && mouse.x < (model.getKeyBtn3().getRightX() * 2)
				&& mouse.y >= (model.getKeyBtn3().getY() * 2) && mouse.y < (model.getKeyBtn3().getBottomY() * 2);
	}// end mouseOverKeyBtn3

	/**
	 * sets the views model to the parsed in model
	 * @param model - updated model
	 */
	public void updatePlayingField(Model model) {
		this.model = model;
	}// end updatePlayingField

}// end View
