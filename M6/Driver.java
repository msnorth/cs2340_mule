import javax.swing.JPanel;

import edu.gatech.mule.ui.*;
import edu.gatech.mule.utils.*;

/**
 * 
 * @author Stephen Conway
 * 		Function group:		Control
 * 		Created for:		M5		9/30/13
 * 		Modifications:		M5		10/6/13	Thomas Mark
 * 									Initial Driver fleshing from stub 		
 * 							M6		10/8/13 Stephen
 * 									Removed mainMenu management functions. Added procedural call to handle this.					
 * 
 * 
 * 
 * 		Purpose: Initial control class. Sets up application by creating game window Initializes background tasks
 * 					like KeyboardAdapter and starts main menu sequence.
 */
public abstract class Driver implements GUIManager{
	private static MainGameWindow frame;
	
	/**
	 * @author Thomas Mark
	 * Application entry point. From here, KeyboardAdapter should be initialized and
	 * the entry menu should be brought up.
	 */
	public static void main(String[] args) {
		KeyboardAdapter input = new KeyboardAdapter();
		frame = new MainGameWindow(input);
		MainMenuManager mainMenu = new MainMenuManager(frame);
		mainMenu.run();
		Waiter.waitFor(mainMenu);
	}		
}
