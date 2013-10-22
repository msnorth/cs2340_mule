package edu.gatech.cs2340.ui;
import javax.swing.JPanel;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.sequencing.WaitedOn;
import edu.gatech.cs2340.sequencing.Waiter;



/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller: GUI
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Stephen
 * 		Modifications:		M6		10/8/13		Stephen
 * 									Moved all management of the main menus to this class from Driver			
 * 							M7		10/21/13	Stephen
 * 									Changed implementation to run in line (removed WaitedOn)
 * 
 * 
 * 		Purpose: Common parent class for any GUI components			 
 */
public class MainMenuManager {
	PlayerManager playerManager;
	
	/**
	 * #M6
	 * Method to start the main menu sequence
	 */
	public void runSynchronous() {
		MainGameWindow window = MainGameWindow.getInstance();
		GameConfigMenu gameConfig = new GameConfigMenu();
		window.setPanel(gameConfig);
		Waiter.waitOn(gameConfig);
		String difficulty = gameConfig.getGameDifficulty();
		int numPlayers = gameConfig.getPlayerCount();
		PlayerConfigMenu playerConfig = new PlayerConfigMenu(numPlayers);
		window.setPanel(playerConfig);
		Waiter.waitOn(playerConfig);
		Player[] players = playerConfig.getPlayers();
		playerManager = new PlayerManager(players, difficulty);
	}

	public PlayerManager getPlayers() {
		return playerManager;
	}
}
