package edu.gatech.cs2340.data;

import java.io.Serializable;
import edu.gatech.cs2340.sequencing.GameClock;
import edu.gatech.cs2340.sequencing.GameTerminatedException;
import edu.gatech.cs2340.sequencing.WaitedOn;

public class GameData implements Serializable, WaitedOn {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3855939233536369027L;
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
	private boolean saveable;
	private boolean terminated;

	public GameData(PlayerManager playerManager, Map map, Store store,
			int numRounds) {
		this.playerManager = playerManager;
		this.map = map;
		this.store = store;
		this.numRounds = numRounds;
		roundNum = 0;
		playerNum = 0;
		state = PRODUCTION;
		saveable = false;
		terminated = false;
	}

	/**
	 * Getter for current round number
	 * 
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
	 * 
	 * @return
	 */
	public int getPlayerNum() {
		return playerNum;
	}

	/**
	 * Setter for current player number
	 * 
	 * @param playerNum
	 */
	public void nextPlayer() {
		playerNum++;
	}

	/**
	 * Method to reset the player number
	 */
	public void resetPlayerNum() {
		playerNum = 0;
	}

	/**
	 * Method to get Player
	 * 
	 * @return
	 */
	public Player getCurrentPlayer() {
		return playerManager.getPlayerNumber(playerNum);
	}

	/**
	 * Method to get number of players playing
	 * 
	 * @return
	 */
	public int getNumPlayers() {
		return playerManager.getNumPlayers();
	}

	/**
	 * Getter for current state
	 * 
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
		state %= NUM_STATES;
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

	@Override
	public boolean isFinished() {
		return saveable;
	}

	public void savePoint() {
		saveable = true;
		GameClock.sync();
		if (terminated) {
			throw new GameTerminatedException();
		}
		saveable = false;
	}

	public void terminate() {
		terminated = true;
	}
}
