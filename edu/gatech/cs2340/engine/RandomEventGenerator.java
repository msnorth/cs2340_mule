package edu.gatech.cs2340.engine;

import java.util.Random;

import edu.gatech.cs2340.data.GameState;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.sequencing.GameClock;
import edu.gatech.cs2340.sequencing.MULETimer;
import edu.gatech.cs2340.sequencing.Waiter;

public class RandomEventGenerator {
	private final Random rand = new Random();
	private static final int[] RANDOMEVENTPRICEMODIFIER = {25, 25, 25, 50, 50, 50, 50, 75, 75, 75, 
	                                                       75, 100};
	private PlayerManager playerManager;
	private GameState state;
	
	public RandomEventGenerator(PlayerManager playerManager, GameState state) {
		this.playerManager = playerManager;
		this.state = state;
	}
	
	/**
	 * #M9
	 * Method to generate random events for all players
	 */
	public void runSynchronous() {
		state.setState(GameState.RANDOM_EVENT);
		Player[] players = playerManager.getPlayers();
		int i = state.getPlayerNum();
		while (i<players.length) {
			state.setPlayerNum(i);
			state.savePoint();
			String result = randomEventSimulator(players[i]);
			i++;
		}
		state.setPlayerNum(0);
		state.setState(GameState.RANDOM_EVENT + 1);
	}
	
	
	/**
	 * #M9
	 * Method responsible for checking for and applying random events to players at the beginning
	 * of their turn. There is a 27% chance of any event occurring.
	 * 
	 * @param player
	 * @return message
	 */
	private String randomEventSimulator(Player player) {
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
