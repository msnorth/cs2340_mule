package edu.gatech.cs2340.io;

/**
 * 
 * @author Stephen Conway
 * 
 * 			Created for:		M5 		9/30/13
 * 			Modifications:		M6 		10/8/13 Stephen
 * 										Removed gainFocus and loseFocus methods. No real functionality for those as of yet.
 * 
 * 			Purpose: Interface implemented by anything that would use
 * 						keyboard/AI/network input. Notably planned for GUIs, Auction,
 * 						LandGranter, Sprite, etc.
 */
public interface InputReceiver {
	public boolean hasFocus = false;
	
	/**
	 * @author Stephen Conway #M5
	 * Called by KeyboardAdapter or other input channels when focus comes onto object
	 */
	//public void gainFocus();
	
	/**
	 * @author Stephen Conway #M5
	 * Called by KeyboardAdapter or other input channels when focus moves away from object
	 */
	//public void loseFocus();
	
	/**
	 * @author #M5
	 * Method to interpret input from keyboard, AI, or network.
	 * @param input String representation of input. Either single 
	 * 					char or a word for special keys (enter)
	 */
	public abstract void receiveInput(String input);
}

