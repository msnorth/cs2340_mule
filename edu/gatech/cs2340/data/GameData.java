package edu.gatech.cs2340.data;

import java.io.Serializable;
import java.util.ArrayList;

import edu.gatech.cs2340.sequencing.GameClock;
import edu.gatech.cs2340.sequencing.MULETimer;

public class GameData implements Serializable {
	public static final int PRODUCTION = 0;
	public static final int RANDOM_EVENT = 1;
	public static final int LAND_GRANT = 2;
	public static final int TURN = 3;
	private static final int NUM_STATES = 4;
	
	
	private PlayerManager playerManager;
	private Map map;
	private Store store;
	private int numRounds;
	private int roundNum;
	private int playerNum;
	private int state;
	
	public GameData(PlayerManager playerManager, Map map, Store store, int numRounds) {
		this.playerManager = playerManager;
		this.map = map;
		this.store = store;
		this.numRounds = numRounds;
		roundNum = 0;
		playerNum = 0;
		state = PRODUCTION;
	}
	

	
	/**
	 * Getter for current round number
	 * @return
	 */
	public int getRoundNum() {
		return roundNum;
	}

	/**
	 * Increments round number
	 */
	public void nextRound() {
		roundNum++;
	}

	/**
	 * Getter for current player number
	 * @return
	 */
	public int getPlayerNum() {
		return playerNum;
	}

	/**
	 * Setter for current player number
	 * @param playerNum
	 */
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	
	/**
	 * Method to reset the player number
	 */
	public void resetPlayerNum() {
		playerNum = 0;
	}

	/**
	 * Getter for current state
	 * @return
	 */
	public int getState() {
		return state;
	}

	/**
	 * Increment current state
	 */
	public void nextState() {
		state++;
		state%=NUM_STATES;
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

	public int getNumRounds() {
		return numRounds;
	}

	public void setNumRounds(int numRounds) {
		this.numRounds = numRounds;
	}
}
