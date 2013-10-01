/**
 * 
 * @author Stephen Conway
 * #M5
 * Last modified:	M5		9/30/13		Stephen Conway
 * 
 * 
 * 
 * Purpose: Interface implemented by anything that would use
 * 			keyboard input. Notably planned for GUIs, Auction,
 * 			LandGranter, Sprite, etc.
 */
public interface KeyboardReceiver {
	private boolean hasKeyboardFocus;
	
	/**
	 * @author Stephen Conway #M5
	 * Called by KeyboardAdapter when focus comes onto object
	 */
	public void gainFocus() {
		hasKeyboardFocus = true;
	}
	
	/**
	 * @author Stephen Conway #M5
	 * Called by KeyboardAdapter when focus moves away from object
	 */
	public void loseFocus() {
		hasKeyboardFocus = false;
	}
	
	/**
	 * @author #M5
	 * Method to interpret input from keyboard.
	 * @param input String representation of input. Either single 
	 * 					char or a word for special keys (enter)
	 */
	public abstract void receiveInput(String input);
}
