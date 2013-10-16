package edu.gatech.cs2340.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


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
	public static final String CONFRIM_KEY = " ";
	public static final String UP = "w";
	public static final String LEFT = "a";
	public static final String DOWN = "s";
	public static final String RIGHT = "d";
	
	private static KeyboardAdapter instance = null;
	private InputReceiver currentFocused;
	
	public static KeyboardAdapter getInstance() {
		return instance;
	}
	
	public static void initialize() {
		if (instance != null) {
			throw new RuntimeException("KeyboardAdapter already created!");
		}
		instance = new KeyboardAdapter();
	}
	
	/**
	 * @author Stephen Conway #M5
	 * Primary constructor
	 */
	private KeyboardAdapter() {
		currentFocused = null;
	}
	
	/**
	 * @author Stephen Conway #M5
	 * Method to set the current receiver of the keyboard input
	 * @param newFocused New item to focus on, or null
	 */
	public void setReceiver(InputReceiver newFocused) {
		/*
		if (currentFocused != null) {
			currentFocused.loseFocus();
		}
		*/
		currentFocused = newFocused;
		/*
		if (currentFocused != null) {
			currentFocused.gainFocus();
		}
		*/
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
		System.out.println(key);
		if (currentFocused != null) {
			currentFocused.receiveInput(key);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
