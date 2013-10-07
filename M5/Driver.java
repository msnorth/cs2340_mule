import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.gatech.mule.ui.*;
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
public class Driver implements GUIManager{
	private static Driver driver;
	private static MainGameWindow frame;
	
	/**
	 * @author Thomas Mark
	 * Application entry point. From here, KeyboardAdapter should be initialized and
	 * the entry menu should be brought up.
	 */
	public static void main(String[] args) {
		driver = new Driver();
		KeyboardAdapter input = new KeyboardAdapter();
		frame = new MainGameWindow(input);
		
		PlayerConfigMenu playerConfig = new PlayerConfigMenu(driver);
		frame.setPanel(playerConfig);
		/*
		JFrame frame2 = new JFrame();
		JPanel main = new JPanel();
		frame2.add(main);
		main.add(playerConfig);
		frame2.setVisible(true);;
		*/
	}
	
	/**
	 * Driver configures new panels based on input from users.
	 * 
	 * @param panel
	 * @param message
	 */
	@Override
	public void notify(JPanel panel, String message) {
		if (panel instanceof PlayerConfigMenu && message.equals("next")) {
			GameConfigMenu gameConfig = new GameConfigMenu(this);
			frame.setPanel(gameConfig);
			
		} else if (panel instanceof GameConfigMenu && message.equals("next")) {}
	}
}
