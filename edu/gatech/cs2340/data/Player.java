package edu.gatech.cs2340.data;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import edu.gatech.cs2340.data.ResourceAmount.ResourceType;

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
 * 							M7      10/19/2013 Shreyyas Vanarase
 * 									Added method to calculate the player's turn time
 * 							M8		10/25/13 Thomas Mark
 * 									Began fleshing out resource usage.
 * 							M8		10/27/13 Thomas Mark
 * 									Added initial beginner resources, mule holding
 * 							M9      10/27/13 Shreyyas Vanarase
 * 									Updated remove method and the beginning resources
 * 							M9		10/29/13 Thomas Mark
 * 									Player now calculates their score.
 * 							M9		11/3/13 Thomas Mark
 * 									Added lowest score knowledge.
 * 							M9      11/4/13 Shreyyas Vanarase
 * 									Added method to get the player's mule amount.
 * 							
 * 		Purpose: Holds information for a player in the game.
 * 				 
 */
public class Player {
	private String name;
	private final String race;
	private final Color color;
	private final ResourceAmount resources;
	private Mule mule;
	private int money;
	private int gameScore;
	private boolean lowestScore;
	private final String difficulty;
	//private final ArrayList<Tile> ownedTiles;
	private Store store;
	
	private ImageIcon image;

	/**
	 * #M6 Main constructor
	 * 
	 * @param name
	 * @param race
	 * @param color
	 */
	public Player(String pName, String pRace, Color pColor) {
		name = pName;
		race = pRace;
		color = pColor;
		this.lowestScore = false;

		difficulty = "Beginner"; // variable defined if it needs to be changed
									// to allow for Standard/Tournament
		//ownedTiles = new ArrayList<Tile>();
		resources = new ResourceAmount();

		if (difficulty.equalsIgnoreCase("Beginner")) {
			resources.add(ResourceAmount.ResourceType.FOOD, 8);
			resources.add(ResourceAmount.ResourceType.ENERGY, 4);
			resources.add(ResourceAmount.ResourceType.FOOD, 0);
		}
		if (!difficulty.equalsIgnoreCase("Beginner")) {
			resources.add(ResourceAmount.ResourceType.FOOD, 4);
			resources.add(ResourceAmount.ResourceType.ENERGY, 2);
			resources.add(ResourceAmount.ResourceType.FOOD, 0);
		}

		if (race.equalsIgnoreCase("Human")) {
			money = 600;
		} else if (race.equalsIgnoreCase("Flapper")) {
			money = 1600;
		} else
			money = 1000;


	}


	/**
	 * #M6 Method to calculate the player's current game score. Used to
	 * determine player order by PlayerManager
	 * 
	 * @return
	 */
	public int calculateScore() {
		store = Store.getStore();
		gameScore = 0;
		
		ResourceType[] types = ResourceType.values();
		for(int i=0; i<types.length; i++) {
			// Add total resource value to score
			gameScore += resources.getAmount(types[i])*store.getResourcePrice(types[i]);
			
			// Add money to total score
			gameScore += this.money;
			
			// Add mule price to total score
			if(this.hazMule()) {
				gameScore += store.getMulePrice(mule.getType());
			}
		}
		return gameScore;
	}

	/**
	 * #M6
	 * Method to add the tile to player's ownedTile list
	 * @param tile
	 */
	
	public void addTile(Tile tile) {
		if (tile != null) {
			tile.setOwner(this);
		}
	}
	
	/**
	 * #M6
	 * Method to get the current player score
	 * @return gameScore
	 */
	public int getGameScore() {
		calculateScore();
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
	 * Method to get player's race.
	 * 
	 * @return
	 */
	public String getRace() {
		return race;
	}

	/*
	 * #M8
	 * 
	 * Method to get the image associate with the player
	 */
	public ImageIcon getImageIcon() {
		return image;
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
	 * #M7
	 * Method for determining how much money a player owns.
	 * 
	 * @return money
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * #M7
	 * Method to determine length of Player's turn. Based on food and the roundNumber of the player.
	 * 
	 * @return player's turn time
	 */
	public long calculateTurnTime(int roundNumber) {
		int foodAmount = resources.getAmount(ResourceType.FOOD);
		if(roundNumber <= 4 && foodAmount >= 3) {
			return 50000;
		}
		else if(roundNumber <= 8 && foodAmount >= 4) {
			return 50000;
		}
		else if(roundNumber <= 12 && foodAmount >= 5) {
			return 50000;
		}
		else if(foodAmount == 0) {
			return 5000;
		}
		else return 30000;
	}
	/**
	 * Sets the amount of player money
	 * @param additionalMoney
	 */
	public void addMoney(int additionalMoney) {
		money += additionalMoney;
	}
	
	/**
	 * #M8
	 * I haz mule?
	 * @return
	 */
	public boolean hazMule() {
		return (mule != null);
	}

	/**
	 * #M8
	 * Getter method for a player's particular resource.
	 * @return the resource amount
	 */
	public int getResourceAmount(ResourceType resource) {
		return resources.getAmount(resource);
	}
	
	/**
	 * #M8
	 * Removal method for a player's particular resource.
	 */
	public void removeResources(ResourceType resource, int amount) {
		if(getResourceAmount(resource) >= amount) {
			resources.remove(resource, amount);
		}
	}
	
	/**
	 * #M8
	 * Adder method for a player's particular resource.
	 */
	public void addResources(ResourceType resource, int amount) {
		resources.add(resource, amount);
	}
	
	/**
	 * #M9
	 * @param amount
	 */
	public void addResources(ResourceAmount amount) {
		resources.add(amount);
	}

	/**
	 * #M8
	 * Method allowing a player to obtain a specific type of mule.
	 */
	public void addMule(Mule mule) {
		this.mule = mule;
	}
	
	/**
	 * #M8
	 * Getter method for a player's mule.
	 * @return mule
	 */
	public Mule getMule() {
		return mule;
	}
	
	/**
	 * #M8
	 * Method to remove the mule from the player.
	 */
	public void removeMule() {
		mule = null;
		
	}
	
	/**
	 * #M9
	 * Method to get the player's mule amount.
	 * @return player's mule amount
	 */
	public int getMuleAmount() {
		int result = 0;
		if (mule != null) {
			result = 1;
		}
		return result;
	}
	/**
	 * #M9
	 * Method to determine if this player has the lowest score out of any player.
	 * 
	 * @return lowestScore
	 */
	public boolean hasLowestScore() {
		return lowestScore;
	}
	
	/**
	 * #M9
	 * Method to set player lowestScore boolean value.
	 *  
	 * @param lowScore
	 */
	public void setlowestScore(boolean lowScore) {
		this.lowestScore = lowScore;
	}
}
