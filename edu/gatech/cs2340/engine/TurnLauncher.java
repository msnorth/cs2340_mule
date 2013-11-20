package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.GameData;
import edu.gatech.cs2340.data.Player;

/**
 * 
 * @author Stephen
 *
 * Class to wrap the creation and execution of Turn objects for each player.
 */
public class TurnLauncher {
	private GameData data;
	
	/**
	 * Main constructor. Needs reference to game data.
	 * @param data
	 */
	public TurnLauncher(GameData data) {
		this.data = data;
	}
	
	/**
	 * Execution. Cycles over players and runs their turns.
	 */
	public void runSynchronous() {
		while (data.getPlayerNum() < data.getNumPlayers()) {
			Turn turn = new Turn(data);
			turn.runSynchronous();
			data.nextPlayer();
		}
		data.resetPlayerNum();
		data.nextState();
	}
}
