package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.GameData;
import edu.gatech.cs2340.data.Player;

public class TurnLauncher {
	private GameData data;
	
	public TurnLauncher(GameData data) {
		this.data = data;
	}
	
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
