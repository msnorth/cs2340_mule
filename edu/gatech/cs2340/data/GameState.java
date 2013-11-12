package edu.gatech.cs2340.data;

import java.awt.Point;
import java.io.Serializable;

import edu.gatech.cs2340.sequencing.GameClock;

/**
 * @author Stephen Conway
 * 
 * Class to store execution information for save/load
 * 
 */
public class GameState implements Serializable {
	//public static enum STATE {PRODUCTION, RANDOM_EVENT, LAND_GRANT, TURN};
	public static final int PRODUCTION = 0;
	public static final int RANDOM_EVENT = 1;
	public static final int LAND_GRANT = 2;
	public static final int TURN = 3;
	
	private int roundNumber;
	private int state;
	private boolean saveable;
	private int playerNum;
	private int tileNum;
	private long timeRemaining;
	private Point spritePosition;
	
	public int getRoundNumber() {
		return roundNumber;
	}
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		System.out.println("State changed to " + state);
		this.state = state;
	}
	public boolean isSaveable() {
		return saveable;
	}
	public void setSaveable(boolean save) {
		saveable = save;
	}
	public int getPlayerNum() {
		return playerNum;
	}
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	public int getTileNum() {
		return tileNum;
	}
	public void setTileNum(int tileNum) {
		this.tileNum = tileNum;
	}
	public long getTimeRemaining() {
		return timeRemaining;
	}
	public void setTimeRemaining(long timeRemaining) {
		this.timeRemaining = timeRemaining;
	}
	public Point getSpritePosition() {
		return spritePosition;
	}
	public void setSpritePosition(Point spritePosition) {
		this.spritePosition = spritePosition;
	}
	
	public void savePoint() {
		saveable = true;
		GameClock.sync();
		saveable = false;
	}
}
