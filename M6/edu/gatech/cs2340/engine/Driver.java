package edu.gatech.cs2340.engine;

import javax.swing.JPanel;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.io.KeyboardAdapter;
import edu.gatech.cs2340.sequencing.Waiter;
import edu.gatech.cs2340.ui.GUIManager;
import edu.gatech.cs2340.ui.MainGameWindow;
import edu.gatech.cs2340.ui.MainMenuManager;

/**
 * 
 * @author Stephen Conway
 * 		Function group:		Control: Engine
 * 		Created for:		M5		9/30/13
 * 		Modifications:		M5		10/6/13	Thomas Mark
 * 									Initial Driver fleshing from stub 		
 * 							M6		10/8/13 Stephen
 * 									Removed mainMenu management functions. Added procedural call to handle this.	
 * 							M6		10/15/13 Thomas Mark
 * 									Modified so that game class is instantiated properly.				
 * 
 * 
 * 
 * 		Purpose: Initial control class. Sets up application by creating game window Initializes background tasks
 * 					like KeyboardAdapter and starts main menu sequence.
 */
public abstract class Driver {
	private static MainGameWindow frame;
	private static Game game;
	
	/**
	 * Application entry point. From here, KeyboardAdapter should be initialized and
	 * the entry menu should be brought up.
	 */
	public static void main(String[] args) {
		KeyboardAdapter input = new KeyboardAdapter();
		frame = new MainGameWindow(input);
		MainMenuManager mainMenu = new MainMenuManager(frame);
		mainMenu.run();
		Waiter.waitOn(mainMenu);
		
		PlayerManager pManager = mainMenu.getPlayers();
		
		// need to figure out best way to get players to game
		
		game = new Game(pManager, false, 8);
		game.run();
		Waiter.waitOn(game, 10);
	}		
}
