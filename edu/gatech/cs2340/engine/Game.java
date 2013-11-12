
package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.GameData;
import edu.gatech.cs2340.data.GameState;
import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.MapGenerator;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.data.Store;
import edu.gatech.cs2340.sequencing.GameClock;
import edu.gatech.cs2340.sequencing.MULETimer;

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
public class Game implements Runnable{
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
		GameState gameState = new GameState();
		data = new GameData(numRounds,
							pManager,
							map,
							Store.getStore(),
							GameClock.getClock(),
							gameState);
		currentGame = this;
	}
	
	/**
	 * #M9
	 * Load game constructor
	 * @param data
	 */
	public Game(GameData data) {
		this.data = data;
		Store.setStore(data.getStore());
		GameClock.setClock(data.getGameClock());
		currentGame = this;
	}
	
	/**
	 * Execute the number of Rounds required.
	 */
	public void run() {
		GameClock.startClock();
		
		GameState state = data.getGameState();
		while (state.getRoundNumber()<=data.getNumRounds()) {
			Round round = new Round(data.getPlayerManager(), data.getMap(), state);
			round.runSynchronous();
			state.setRoundNumber(state.getRoundNumber()+1);
		}
	}	
	
	/**
	 * Execute game from save point
	 * @param data
	 */
	public void runSynchronous(GameData data) {

	}
	
	public GameData getGameData() {
		return data;
	}
}
