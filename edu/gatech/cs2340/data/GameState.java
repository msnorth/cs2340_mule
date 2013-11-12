package edu.gatech.cs2340.data;

import java.awt.Point;

/**
 * @author Stephen Conway
 * 
 * Class to store execution information for save/load
 * 
 */
public class GameState {
	public static enum STATE {PRODUCTION, RANDOM_EVENT, LAND_GRANT, TURN};
	
	private int roundNumber;
	private STATE state;
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
	public STATE getState() {
		return state;
	}
	public void setState(STATE state) {
		this.state = state;
	}
	public boolean isSaveable() {
		return saveable;
	}
	public void setSaveable(boolean saveable) {
		this.saveable = saveable;
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
}
