package edu.gatech.mule.utils;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * 
 * @author Stephen Conway
 * 
 * 			Created for:		M5		9/30/13
 * 			Modifications:		M5		10/7/13 Stephen Conway
 * 										Removed Runnable interface
 * 
 * 
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
		if (currentFocused != null) {
			currentFocused.loseFocus();
		}
		currentFocused = newFocused;
		if (currentFocused != null) {
			currentFocused.gainFocus();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
