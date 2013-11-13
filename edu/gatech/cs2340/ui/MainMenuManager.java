package edu.gatech.cs2340.ui;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.PlayerManager;
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
	String mapType;
	/**
	 * #M6
	 * Method to start the main menu sequence
	 */
	public void runSynchronous() {
		GameConfigMenu gameConfig = new GameConfigMenu();
		MainGameWindow.setMainPanel(gameConfig);
		Waiter.waitOn(gameConfig);
		String difficulty = gameConfig.getGameDifficulty();
		mapType = gameConfig.getMapType();
		int numPlayers = gameConfig.getPlayerCount();
		PlayerConfigMenu playerConfig = new PlayerConfigMenu(numPlayers);
		MainGameWindow.setMainPanel(playerConfig);
		Waiter.waitOn(playerConfig);
		Player[] players = playerConfig.getPlayers();
		playerManager = new PlayerManager(players, difficulty);
	}

	public PlayerManager getPlayers() {
		return playerManager;
	}
	public String getMapType() {
		return mapType;
	}
}
