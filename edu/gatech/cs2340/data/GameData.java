package edu.gatech.cs2340.data;

import java.io.Serializable;
import java.util.ArrayList;

import edu.gatech.cs2340.sequencing.GameClock;
import edu.gatech.cs2340.sequencing.MULETimer;

public class GameData implements Serializable {
	private PlayerManager playerManager;
	private Map map;
	private Store store;
	private GameClock gameClock;
	private ArrayList<MULETimer> timers;
	
	public GameData(PlayerManager playerManager, Map map, Store store, GameClock gameClock, ArrayList<MULETimer> timers) {
		this.playerManager = playerManager;
		this.map = map;
		this.store = store;
		this.gameClock = gameClock;
		this.timers = timers;
	}
}
