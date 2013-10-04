import javax.swing.JFrame;

/**
 * 
 * @author Stephen Conway
 * 
 * Created for:		M5		10/3/13
 * Last modified:	M5		10/3/13			Stephen Conway
 * 
 * 
 * 
 * Purpose: Main JFrame for the game. Channels keyboard input to KeyboardAdapter.
 * 
 */
public class MainGameWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Main constructor.
	 * Sets a KeyboardAdapter as the handler for keyboard input
	 * @param keyboardAdapter
	 */
	public MainGameWindow(KeyboardAdapter keyboardAdapter) {
		this.setFocusable(true);
		this.addKeyListener(keyboardAdapter);
	}
}
