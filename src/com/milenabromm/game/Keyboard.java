package com.milenabromm.game;

import static org.gillius.jalleg.binding.AllegroLibrary.*;

import org.gillius.jalleg.binding.ALLEGRO_KEYBOARD_STATE;

/**
 * Keyboard makes typing possible by determining char input on the basis of the
 * allegro keyboard state with the type() method <br>
 * Keyboard was created to separate the lengthy if statements of type() out of
 * the other classes <br>
 * and since other allegro methods to solve this did not seam to work with java.
 * 
 * @author Milena Bromm 555851
 * @version 2.1
 */
public class Keyboard {

	/**
	 * Returns a char value from the current keyboard state if it is one of the
	 * matched characters.<br>
	 * Returns 'A-Z' characters. Additionally returns '1' for backspace pressed and '2'
	 * for enter pressed, '3' for Space pressed, and 4-7 for arrow keys. if key is not matched returns '0'.
	 * 
	 * @param keys - current keyboard state
	 * @return input - holds char input taken from keyboard state
	 */
	public char type(ALLEGRO_KEYBOARD_STATE keys) {
		// If statements check if keyboard state matches an A-Z character, backspace or
		// enter and sets the input accordingly
		// If no if statement evaluates true input is not set.
		char input;

		if (al_key_down(keys, ALLEGRO_KEY_A)) {
			input = 'A';

		} else if (al_key_down(keys, ALLEGRO_KEY_B)) {
			input = 'B';

		} else if (al_key_down(keys, ALLEGRO_KEY_C)) {
			input = 'C';

		} else if (al_key_down(keys, ALLEGRO_KEY_D)) {
			input = 'D';

		} else if (al_key_down(keys, ALLEGRO_KEY_E)) {
			input = 'E';

		} else if (al_key_down(keys, ALLEGRO_KEY_F)) {
			input = 'F';

		} else if (al_key_down(keys, ALLEGRO_KEY_G)) {
			input = 'G';

		} else if (al_key_down(keys, ALLEGRO_KEY_H)) {
			input = 'H';

		} else if (al_key_down(keys, ALLEGRO_KEY_I)) {
			input = 'I';

		} else if (al_key_down(keys, ALLEGRO_KEY_J)) {
			input = 'J';

		} else if (al_key_down(keys, ALLEGRO_KEY_K)) {
			input = 'K';

		} else if (al_key_down(keys, ALLEGRO_KEY_L)) {
			input = 'L';

		} else if (al_key_down(keys, ALLEGRO_KEY_M)) {
			input = 'M';

		} else if (al_key_down(keys, ALLEGRO_KEY_N)) {
			input = 'N';

		} else if (al_key_down(keys, ALLEGRO_KEY_O)) {
			input = 'O';

		} else if (al_key_down(keys, ALLEGRO_KEY_P)) {
			input = 'P';

		} else if (al_key_down(keys, ALLEGRO_KEY_Q)) {
			input = 'Q';

		} else if (al_key_down(keys, ALLEGRO_KEY_R)) {
			input = 'R';

		} else if (al_key_down(keys, ALLEGRO_KEY_S)) {
			input = 'S';

		} else if (al_key_down(keys, ALLEGRO_KEY_T)) {
			input = 'T';

		} else if (al_key_down(keys, ALLEGRO_KEY_U)) {
			input = 'U';

		} else if (al_key_down(keys, ALLEGRO_KEY_V)) {
			input = 'V';

		} else if (al_key_down(keys, ALLEGRO_KEY_W)) {
			input = 'W';

		} else if (al_key_down(keys, ALLEGRO_KEY_X)) {
			input = 'X';

		} else if (al_key_down(keys, ALLEGRO_KEY_Y)) {
			input = 'Y';

		} else if (al_key_down(keys, ALLEGRO_KEY_Z)) {
			input = 'Z';

		} else if (al_key_down(keys, ALLEGRO_KEY_BACKSPACE)) {
			input = '1';

		} else if (al_key_down(keys, ALLEGRO_KEY_ENTER)) {
			input = '2';

		} else if (al_key_down(keys, ALLEGRO_KEY_SPACE)) {
			input = '3';
			
		} else if (al_key_down(keys, ALLEGRO_KEY_LEFT)) {
			input = '4';
			
		} else if (al_key_down(keys, ALLEGRO_KEY_RIGHT)) {
			input = '5';
			
		} else if (al_key_down(keys, ALLEGRO_KEY_UP)) {
			input = '6';
			
		} else if (al_key_down(keys, ALLEGRO_KEY_DOWN)) {
			input = '7';

		} else {
			input = '0';
		}//end if

		return input;
	}// end type
	
	/**
	 * gets the numeric value of the current key pressed. Only represents a limited set of keyboard input.
	 * Input that is out of range will return a value of 0. 
	 * @param keys - current keyboard state
	 * @return key - numeric value for current key pressed
	 */
	public int getKey(ALLEGRO_KEYBOARD_STATE keys) {
		int key;
		
		if (al_key_down(keys, ALLEGRO_KEY_A)) {
			key = ALLEGRO_KEY_A;

		} else if (al_key_down(keys, ALLEGRO_KEY_B)) {
			key = ALLEGRO_KEY_B;

		} else if (al_key_down(keys, ALLEGRO_KEY_C)) {
			key = ALLEGRO_KEY_C;

		} else if (al_key_down(keys, ALLEGRO_KEY_D)) {
			key = ALLEGRO_KEY_D;

		} else if (al_key_down(keys, ALLEGRO_KEY_E)) {
			key = ALLEGRO_KEY_E;

		} else if (al_key_down(keys, ALLEGRO_KEY_F)) {
			key = ALLEGRO_KEY_F;

		} else if (al_key_down(keys, ALLEGRO_KEY_G)) {
			key = ALLEGRO_KEY_G;

		} else if (al_key_down(keys, ALLEGRO_KEY_H)) {
			key = ALLEGRO_KEY_H;

		} else if (al_key_down(keys, ALLEGRO_KEY_I)) {
			key = ALLEGRO_KEY_I;

		} else if (al_key_down(keys, ALLEGRO_KEY_J)) {
			key = ALLEGRO_KEY_J;

		} else if (al_key_down(keys, ALLEGRO_KEY_K)) {
			key = ALLEGRO_KEY_K;

		} else if (al_key_down(keys, ALLEGRO_KEY_L)) {
			key = ALLEGRO_KEY_L;

		} else if (al_key_down(keys, ALLEGRO_KEY_M)) {
			key = ALLEGRO_KEY_M;

		} else if (al_key_down(keys, ALLEGRO_KEY_N)) {
			key = ALLEGRO_KEY_N;

		} else if (al_key_down(keys, ALLEGRO_KEY_O)) {
			key = ALLEGRO_KEY_O;

		} else if (al_key_down(keys, ALLEGRO_KEY_P)) {
			key = ALLEGRO_KEY_P;

		} else if (al_key_down(keys, ALLEGRO_KEY_Q)) {
			key = ALLEGRO_KEY_Q;

		} else if (al_key_down(keys, ALLEGRO_KEY_R)) {
			key = ALLEGRO_KEY_R;

		} else if (al_key_down(keys, ALLEGRO_KEY_S)) {
			key = ALLEGRO_KEY_S;

		} else if (al_key_down(keys, ALLEGRO_KEY_T)) {
			key = ALLEGRO_KEY_T;

		} else if (al_key_down(keys, ALLEGRO_KEY_U)) {
			key = ALLEGRO_KEY_U;

		} else if (al_key_down(keys, ALLEGRO_KEY_V)) {
			key = ALLEGRO_KEY_V;

		} else if (al_key_down(keys, ALLEGRO_KEY_W)) {
			key = ALLEGRO_KEY_W;

		} else if (al_key_down(keys, ALLEGRO_KEY_X)) {
			key = ALLEGRO_KEY_X;

		} else if (al_key_down(keys, ALLEGRO_KEY_Y)) {
			key = ALLEGRO_KEY_Y;

		} else if (al_key_down(keys, ALLEGRO_KEY_Z)) {
			key = ALLEGRO_KEY_Z;

		} else if (al_key_down(keys, ALLEGRO_KEY_BACKSPACE)) {
			key = ALLEGRO_KEY_BACKSPACE;

		} else if (al_key_down(keys, ALLEGRO_KEY_ENTER)) {
			key = ALLEGRO_KEY_ENTER;

		} else if (al_key_down(keys, ALLEGRO_KEY_SPACE)) {
			key = ALLEGRO_KEY_SPACE;
			
		} else if (al_key_down(keys, ALLEGRO_KEY_LEFT)) {
			key = ALLEGRO_KEY_LEFT;
			
		} else if (al_key_down(keys, ALLEGRO_KEY_RIGHT)) {
			key = ALLEGRO_KEY_RIGHT;
			
		} else if (al_key_down(keys, ALLEGRO_KEY_UP)) {
			key = ALLEGRO_KEY_UP;
			
		} else if (al_key_down(keys, ALLEGRO_KEY_DOWN)) {
			key = ALLEGRO_KEY_DOWN;
			
		} else {
			key = 0;
		}//end IF
		
		return key;
	}//end getKey
	
	
}// end Keyboard
