package edu.gatech.cs2340.data;

import java.util.Random;

import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.engine.Round;



/**
 * 
 * @author Stephen Conway
 * 		Function group:		Model: Data manager
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Thomas Mark
 * 		Modifications:		M6		10/14/2013 Thomas Mark
 * 									Added bubble sort check to order players for land grant/auction.
 * 									Filled out getNextPlayer() method.
 * 							M9		11/3/13 Thomas Mark
 * 									Added random events.
 * 
 * 
 * 
 * 		Purpose: Holder class for all Player objects
 * 				 
 */
public class PlayerManager implements PlayerManagerResponsibilities{
	private final Player[] players;
	private int nextPlayer;
	private final Random rand = new Random();
	private static final int[] RANDOMEVENTPRICEMODIFIER = {25, 25, 25, 50, 50, 50, 50, 75, 75, 75, 
	                                                       75, 100};
	
	/**
	 * #M6
	 * Main constructor. Takes in players and then calculates their initial ordering.
	 * 
	 * @param players
	 */
	public PlayerManager(Player[] players, String difficulty) {
		this.players = players;
		calculatePlayerOrder();
		nextPlayer = 0;
	}
	
	/**
	 * #M6
	 * Calculates player order using bubble sort method.
	 * Rearranges players array in ascending order of score.
	 */
	@Override
	public void calculatePlayerOrder() {
		boolean sorted = false;
		Player temp;
		
		while (!sorted) {
			sorted = true;
			for (int i=0; i < players.length-1; i++) {
					players[i].setlowestScore(false);
				if (players[i].getGameScore() > players[i+1].getGameScore()) {
					temp = players[i];
					players[i] = players[i+1];
					players[i+1] = temp;
					sorted = false;
				}
			}
			players[0].setlowestScore(true);
		}
		
	}
	
	/**
	 * #M6
	 * Returns next player in order of score.
	 * 
	 * @return next
	 */
	@Override
	public Player getNextPlayer() {
		Player next = players[nextPlayer];
		nextPlayer++;
		if (nextPlayer >= players.length) {
			nextPlayer = 0;
		}

		return next;
	}

	/**
	 * #M9
	 * Method responsible for checking for and applying random events to players at the beginning
	 * of their turn. There is a 27% chance of any event occurring.
	 * 
	 * @param player
	 * @return message
	 */
	public String randomEventSimulator(Player player) {
		double eventRoll = rand.nextDouble();
		if (player.hasLowestScore()) {
			if (eventRoll <= 0.27) {
				return goodEventHelper(player);
			} else {
				return "No event happened.";
			}
		} else {
			
			int cost = RANDOMEVENTPRICEMODIFIER[Round.getRoundNumber()];
			int playerMoney = player.getMoney();
			
			if (eventRoll <= 0.154) {
				return goodEventHelper(player);
			} else if (eventRoll <= 0.193) {	
				cost *= 4;
				if (playerMoney < cost) {
					player.deductMoney(playerMoney);
				} else {
					player.deductMoney(cost);
				}
				return "FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST " + cost + ".";
			} else if (eventRoll <= 0.231) {
				cost *= 6;
				if (playerMoney < cost) {
					player.deductMoney(playerMoney);
				} else {
					player.deductMoney(cost);
				}
				return "YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU " + cost 
						+ " TO CLEAN IT UP.";
			} else if (eventRoll <= 0.27) {
				int playerFoodAmount = player.getResourceAmount(ResourceType.FOOD);
				player.removeResources(ResourceType.FOOD, playerFoodAmount/2);
				return "MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR "
						+ "FOOD.";
			} else {
				return "No event happened.";
			}
		}
	}
	
	/**
	 * #M6
	 * Returns number of player in game.
	 * 
	 * @return the number of players
	 */
	public int getTotalPlayers() {
		return players.length;
	}
	
	/**
	 * #M9
	 * Returns the array of players in the game.
	 * 
	 * @return players
	 */
	public Player[] getPlayers() {
		return players;
	}
	
	/**
	 * #M9
	 * Method calculating a randomized good event.
	 * 
	 * @return the corresponding message of the good event
	 */
	private String goodEventHelper(Player player) {
		double goodEventRoll = rand.nextDouble();
		if (goodEventRoll <= 0.25) {
			player.addResources(ResourceType.FOOD, 3);
			player.addResources(ResourceType.ENERGY, 2);
			return "YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 "
					+ "ENERGY UNITS.";
		}
		if (goodEventRoll <= 0.5) {
			player.addResources(ResourceType.SMITHORE, 2);
			return "A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF "
					+ "ORE.";
		}
		
		int price = RANDOMEVENTPRICEMODIFIER[Round.getRoundNumber()];
		if (goodEventRoll <= 0.75) {
			price *= 8;
			player.addMoney(price);
			return "THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR " + price +".";
		} else {
			price *= 2;
			player.addMoney(price);
			return "YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR " + price + ".";
		}
	}
}
