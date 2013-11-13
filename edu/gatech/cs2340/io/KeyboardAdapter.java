package edu.gatech.cs2340.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.gatech.cs2340.engine.Game;
import edu.gatech.cs2340.sequencing.GameClock;
import edu.gatech.cs2340.ui.InGameMenu;


/**
 * 
 * @author Stephen Conway
 * 			Function group:		Control
 * 			Created for:		M5		9/30/13
 * 			Modifications:		M5		10/7/13 Stephen Conway
 * 										Removed Runnable interface
 * 								M6		10/8/13 Stephen
 * 										Added some basic functionality to the KeyListener methods
 * 								M6		10/12/13 Shreyyas Vanarase
 * 										Added the getInstance method to set 1 KeyboardAdapter for all classes
 * 								M6		10/15/13 Thomas Mark
 * 										Added movement keys "wasd"
 * 
 * 			Purpose: Data input class. Takes in keyboard input and channels it to
 * 					 active KeyboardReceiver. Handles top level key inputs (like esc
 * 					 to pull up main menu) itself.
 */
public class KeyboardAdapter implements KeyListener{
	
	private static KeyboardAdapter instance = null;
	
	//Enum of key meanings
	public enum KEY_NAME {
		CONFIRM,
		UP,
		LEFT,
		DOWN,
		RIGHT
	}	
	
	//Key config
	public static final String[] KEY_CONFIG = {
		" ", //confirm
		"w", //up
		"a", //left
		"s", //down
		"d"  //right
	};
		
	
	private InputReceiver currentFocused; //receiver of typed string input
	private int keyStatus; //int mapping of currently down keys
	
	
	/**
	 * Method called to initialize the KeyboardAdapter 
	 */
	public static void initialize() {
		if (instance != null) {
			throw new RuntimeException("KeyboardAdapter already created!");
		}
		instance = new KeyboardAdapter();	
	}
	
	/**
	 * Method to get singleton instance of the KeyboardAdapter
	 * 
	 * @return
	 */
	public static KeyboardAdapter getInstance() {
		return instance;
	}
	
	/**
	 * Method to translate String to KEY_NAME
	 * 
	 * @param keyVal
	 * @return
	 */
	public static KEY_NAME getKey(String keyVal) {
		KEY_NAME result = null;
		KEY_NAME[] vals = KEY_NAME.values();
		for (int i=0; i<KEY_CONFIG.length; i++) {
			if (KEY_CONFIG[i].equals(keyVal)) {
				result = vals[i];
			}
		}
		return result;
	}
	
	/**
	 * @author Stephen Conway #M5
	 * Primary constructor
	 */
	private KeyboardAdapter() {
		currentFocused = null;
		keyStatus = 0;
	}
	
	/**
	 * @author Stephen Conway #M5
	 * Method to set the current receiver of the keyboard input
	 * @param newFocused New item to focus on, or null
	 */
	public void setReceiver(InputReceiver newFocused) {
		currentFocused = newFocused;
	}
	
	/**
	 * Method to determine if a given key is down
	 * 
	 * @param key
	 * @return
	 */
	public boolean isPressed(KEY_NAME key) {
		return (((1<<key.ordinal()) & keyStatus) != 0);
	}
	
	/*
	 * event order:
	 * keyPressed
	 * keyTyped
	 * keyReleased
	 * 
	 * need to do some testing before fleshing this out
	 */
	
	@Override
	public void keyTyped(KeyEvent e) {
		String key = e.getKeyChar() + "";
		if (currentFocused != null) {
			currentFocused.receiveInput(key);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		KEY_NAME key = getKey(e.getKeyChar() + "");
		if (key != null) {
			keyStatus |= 1<<key.ordinal();
		}
		
		if (e.getKeyChar() == 'o') {
			Game game = Game.currentGame;
			GameClock.pauseClock();
			if (game != null) {
				GameSaver saver = new GameSaver("C:/Users/Stephen/Desktop/MULEsave.txt", game.getGameData());
				saver.save();
			}
			GameClock.startClock();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			InGameMenu menu = new InGameMenu();
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		KEY_NAME key = getKey(e.getKeyChar() + "");
		if (key != null) {
			keyStatus &= ~(1<<key.ordinal());
		}
	}
	
	
}
