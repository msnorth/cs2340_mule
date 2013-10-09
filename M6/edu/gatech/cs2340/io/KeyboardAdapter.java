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
 * 
 * 			Purpose: Data input class. Takes in keyboard input and channels it to
 * 					active KeyboardReceiver. Handles top level key inputs (like esc
 * 					to pull up main menu) itself.
 */
public class KeyboardAdapter implements KeyListener{
	private InputReceiver currentFocused;
	
	/**
	 * @author Stephen Conway #M5
	 * Primary constructor
	 */
	public KeyboardAdapter() {
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
		currentFocused.receiveInput(key);
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
