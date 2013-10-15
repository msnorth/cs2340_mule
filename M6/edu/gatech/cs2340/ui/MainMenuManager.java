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
 * 
 * 
 * 
 * 		Purpose: Common parent class for any GUI components			 
 */
public class MainMenuManager implements WaitedOn{
	private boolean finished;
	private MainGameWindow mainGameWindow;
	PlayerManager playerManager;
	
	/**
	 * #M6
	 * Main constructor. 
	 * 
	 * @param mainGameWindow
	 */
	public MainMenuManager(MainGameWindow mainGameWindow) {
		finished = false;
		this.mainGameWindow = mainGameWindow;
	}
	
	/**
	 * #M6
	 * Method to start the main menu sequence
	 */
	public void run() {
		GameConfigMenu gameConfig = new GameConfigMenu();
		mainGameWindow.setPanel(gameConfig);
		Waiter.waitOn(gameConfig);
		String difficulty = gameConfig.getGameDifficulty();
		int numPlayers = gameConfig.getPlayerCount();
		PlayerConfigMenu playerConfig = new PlayerConfigMenu(numPlayers);
		mainGameWindow.setPanel(playerConfig);
		playerConfig.run();
		Waiter.waitOn(playerConfig);
		Player[] players = playerConfig.getPlayers();
		playerManager = new PlayerManager(players, difficulty);
		finished = true;
	}

	public PlayerManager getPlayers() {
		return playerManager;
	}
	
	@Override
	public boolean isFinished() {
		return finished;
	}	
}
