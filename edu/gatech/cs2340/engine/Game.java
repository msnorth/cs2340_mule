
package edu.gatech.cs2340.engine;

import javax.swing.JPanel;

import edu.gatech.cs2340.data.GameData;
import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.MapGenerator;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.data.Store;
import edu.gatech.cs2340.sequencing.GameClock;
import edu.gatech.cs2340.sequencing.MULETimer;
import edu.gatech.cs2340.sequencing.Waiter;
import edu.gatech.cs2340.ui.MainGameWindow;

/**
 * 
 * @author Thomas Mark
 * 		Function group:		Control: Engine
 * 		Created for:		M6		10/14/13
 * 		Modifications:		M6		10/15/13 Thomas Mark
 * 									Fleshed out implementation.
 * 							M6		10/15/13 Stephen Conway
 * 									Cleaned variables. Fixed Round loop
 * 							M7		10/21/13 Stephen Conway
 * 									Removed WaitedOn interface. Runs synchronously.
 * 
 * 		Purpose: Controls game processes. Initializes rounds. Determines end of game.
 *
 */
public class Game {
	public static Game currentGame;

	private GameData data;
	
	/**
	 * #M6
	 * Constructor to create new game from config menus.
	 * 
	 * @param pManager Player collection from PlayerConfigMenu
	 * @param randomMap Map generation option
	 * @param numRounds Length of game
	 */
	public Game(PlayerManager pManager, boolean randomMap, int numRounds) {
		Map map;
		if (!randomMap) {
			map = MapGenerator.generateStandardMap();
		} else {
			map = MapGenerator.generateRandomMap();
		}
		
		Store store = new Store();
		data = new GameData(pManager, map, store, numRounds);
		currentGame = this;
	}
	
	/**
	 * #M9
	 * Constructor for creating game from contents of save file.
	 * 
	 * @param data
	 */
	public Game(GameData data) {
		this.data = data;
		currentGame = this;
	}
	
	/**
	 * Execute the number of Rounds required.
	 */
	public void runSynchronous() {
		GameClock.startClock();
		while (data.getRoundNum() < data.getNumRounds()) {
			Round round = new Round(data);
			round.runSynchronous();
			data.nextRound();
		}
	}	
	
	/**
	 * Method to get game data being used. Primarily for saving.
	 * @return
	 */
	public GameData getGameData() {
		return data;
	}
	
	/**
	 * Method to terminate the current game.
	 */
	public static void terminate() {
		//set termination flag
		currentGame.data.terminate();
		//increment clock to kick off termination exceptions
		GameClock.startClock();
		//wait for termination to go through
		MULETimer timer = new MULETimer(GameClock.TICK_LENGTH*4);
		timer.start();
		Waiter.waitOn(timer, (int)(1000 / GameClock.TICK_LENGTH));
		//destroy the clock
		GameClock.disposeOfClock();
		//clear the screen
		MainGameWindow.setMainPanel(new JPanel());
		MainGameWindow.setMessage("");
		currentGame = null;
	}
}
