package edu.gatech.cs2340.data;
import java.awt.Color;
import java.util.ArrayList;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Model: Data holder
 * 		Created for:		M6		10/7/13
 * 		Assigned to:		Thomas Mark
 * 		Modifications:		M6		10/12/13 Shreyyas Vanarase
 * 									Added the addTile method and a score getter method.
 * 							M6		10/14/2013 Thomas Mark
 * 									Added method to determine players color.
 * 							M6		10/15/2013 Shreyyas Vanarase							
 * 									Added method to deduct money from player after buying something
 * 
 * 
 * 		Purpose: Holds information for a player in the game.
 * 				 
 */
public class Player {
	private String name;
	private String race;
	private Color color;
	private ResourceAmount resources;
	private int money;
	private int gameScore;
	private ArrayList<Tile> ownedTiles;
	
	
	/**
	 * #M6
	 * Main constructor
	 * 
	 * @param name
	 * @param race
	 * @param color
	 */
	public Player(String name, String race, Color color) {
		this.name  = name;
		this.race  = race;
		this.color = color;
		ownedTiles = new ArrayList<Tile>();
	}
	
	/**
	 * #M6
	 * Method to calculate the player's current game score.
	 * Used to determine player order by PlayerManager
	 * @return
	 */
	public int calculateScore() {
		gameScore = 0;
		return gameScore;
	}

	/**
	 * #M6
	 * Method to add the tile to player's ownedTile list
	 * @param tile
	 */
	public void addTile(Tile tile) {
		if(tile != null) {
			ownedTiles.add(tile);
		}
	}
	/**
	 * #M6
	 * Method to get the current player score
	 * @return gameScore
	 */
	public int getGameScore() {
		return gameScore;
	}
	
	/**
	 * #M6
	 * Method to obtain players color for tile coloring purposes
	 * @return color
	 */
	public Color getPlayerColor() {
		return color;
	}
	
	/**
	 * #M6
	 * Method to check if player is able to buy a good
	 * @param price
	 * @return true if player can buy good, else false
	 */
	public boolean deductMoney(int price) {
		if(price < money) {
			money -= price;
			return true;
		}
		return false;
	}
	
	/**
	 * #M6
	 * Method to determine length of Player's turn. Based on food.
	 * 
	 * @return
	 */
	public long calculateTurnTime() {
		return 50000;
	}
}
