
package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.MapGenerator;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.sequencing.GameClock;

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
	private PlayerManager playerManager;
	private int numberRounds;
	private Map map;
	
	/**
	 * #M6
	 * Constructor to create new game from config menus.
	 * 
	 * @param pManager Player collection from PlayerConfigMenu
	 * @param randomMap Map generation option
	 * @param numRounds Length of game
	 */
	public Game(PlayerManager pManager, boolean randomMap, int numRounds) {
		playerManager = pManager;
		numberRounds = numRounds;
		
		if (!randomMap) {
			map = MapGenerator.generateStandardMap();
		} else {
			map = MapGenerator.generateRandomMap();
		}
		
	}
	
	/**
	 * Execute the number of Rounds required.
	 */
	public void runSynchronous() {
		GameClock.startClock();
		for (int i=1; i <= numberRounds; i++) {
			Round round = new Round(playerManager, map, i);
			round.runSynchronous();
		}
	}	
}
