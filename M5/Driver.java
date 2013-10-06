import javax.swing.JPanel;

import edu.gatech.mule.ui.MainGameWindow;
import edu.gatech.mule.utils.*;

/**
 * 
 * @author Stephen Conway
 * 
 * 		Created for:		M5		9/30/13
 * 		Modifications:		M5		10/6/13	Thomas Mark
 * 									Initial Driver fleshing from stub 							
 * 
 * 
 * 
 * 
 * Purpose: Initial control class. Sets up application by creating game window and 
 * 			handling navigation through top level menus. Initializes background tasks
 * 			like KeyboardAdapter.
 * Abstracted because of single instance
 */
public abstract class Driver implements GUIManager{
	private static JPanel currentPanel;
	/**
	 * @author Thomas Mark
	 * Application entry point. From here, KeyboardAdapter should be initialized and
	 * the entry menu should be brought up.
	 */
	public static void main(String[] args) {
		KeyboardAdapter input = new KeyboardAdapter();
		MainGameWindow frame = new MainGameWindow(input);
		
		PlayerConfigMenu playerConfig = new PlayerConfigMenu();
		currentPanel = playerConfig;
		frame.setPanel(playerConfig);
	}
	
	/**
	 * Driver configures new panels based on input from users.
	 * 
	 * @param panel
	 * @param message
	 */
	@Override
	public void notify(JPanel panel, String message) {
		if (panel == currentPanel && message.equals("next")) {
			GameConfigMenu gameConfig = new GameConfigMenu();
			currentPanel = gameConfig;
			frame.setPanel(gameConfig);
			
		} else if (panel == currentPanel && message.equals("next") {
			System.out.println("Next milestone!");
		}
	}
}
