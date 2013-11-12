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
	private GameState gameState;
	private int numRounds;
	
	public int getNumRounds() {
		return numRounds;
	}

	public void setNumRounds(int numRounds) {
		this.numRounds = numRounds;
	}

	public GameData(int numRounds, PlayerManager playerManager, Map map, Store store, GameClock gameClock, GameState gameState) {
		this.numRounds = numRounds;
		this.playerManager = playerManager;
		this.map = map;
		this.store = store;
		this.gameClock = gameClock;
		this.gameState = gameState;
	}
	
	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public void setPlayerManager(PlayerManager playerManager) {
		this.playerManager = playerManager;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public GameClock getGameClock() {
		return gameClock;
	}

	public void setGameClock(GameClock gameClock) {
		this.gameClock = gameClock;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
}
