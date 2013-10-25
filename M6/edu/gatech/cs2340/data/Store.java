package edu.gatech.cs2340.data;

import edu.gatech.cs2340.data.ResourceAmount.ResourceType;

/**
 *@author Tommy
 * 		Function group:		Model: Service provider
 * 		Created for:		M8		10/25/13
 * 		Assigned to:		Tommy
 * 		Modifications:		
 * 							
 * 		Allows the player to:
 * 				- buy resources
 * 				- sell resources
 * 				- buy mule
 * 		Notes:
 * 			- Example of Singleton design pattern	
 * 			- "Store.getStore()" should be called when the game is created, then something like 
 * 				"<whatever you called store>.addStartingResources(startingResources);"
 * 			- A player needs to be added to the store (symbolizes player entering store)
 * 			- Methods are from the store's perspective 
 * 			  (e.g. theStore.sellResources(...) : player buying resources)	 		 
 */
public class Store {
	private static Store theStore;
	private static ResourceAmount storeResources;
	private static ResourceAmount storePrices;
	
	private static final int SMITHORE_PRICE = 50;
	private static final int FOOD_PRICE = 30;
	private static final int ENERGY_PRICE = 25;
	private static final int CRYSITE_PRICE = 100;
	private static final int BASE_MULE_PRICE = 100;
	
	private Player player;
	private String errorMessage;
	
	/**
	 * M8
	 * Singleton constructor
	 */
	protected Store() {
		storeResources = new ResourceAmount();	
		storePrices = new ResourceAmount();
		storePrices.add(ResourceType.SMITHORE, SMITHORE_PRICE);
		storePrices.add(ResourceType.FOOD, FOOD_PRICE);
		storePrices.add(ResourceType.ENERGY, ENERGY_PRICE);
		storePrices.add(ResourceType.CRYSTITE, CRYSITE_PRICE);
		storePrices.add(ResourceType.MULE, BASE_MULE_PRICE);
	}
	
	/**
	 * Getter method for the store. If the store does not exist, it is created.
	 * @return theStore
	 */
	public static Store getStore() {
		if (theStore == null) {
			theStore = new Store();
		}
		return theStore;
	}
	
	/**
	 * Store buys resources from the player.
	 * @param resource, amount
	 * @return
	 */
	public boolean buyResources(ResourceType resource, int amount) {		
		if (player.getResourceAmount(resource) < amount) {
			errorMessage = "You cannot sell that much.";
			return false;
		}
		player.removeResourceAmount(resource, amount);
		storeResources.add(resource, amount);
		player.addMoney(amount*storePrices.getAmount(resource));
		return true;
	}
	
	/**
	 * Store sells resources to the player.
	 * @param resource, amount
	 * @return
	 */
	public boolean sellResources(ResourceType resource, int amount) {
		int storeAmount = storeResources.getAmount(resource);
		int cost = amount*storePrices.getAmount(resource);
		
		if (storeAmount < amount) {
			errorMessage = "The store only has " + storeAmount + " " 
							+ resource.toString().toLowerCase() + ".";
			return false;
		}
		if (player.getMoney() < cost) {
			errorMessage = "You do not have enough money!";
			return false;
		}
		if (resource.name().equals("MULE") && player.hazMule()) {
			errorMessage = "You already have a mule.";
			return false;
		}
		storeResources.remove(resource, amount);
		player.deductMoney(cost);
		return true;
	}
	
	/**
	 * Method adds starting resources to the store.
	 * @param startingAmount
	 */
	public void addStartingResources(ResourceAmount startingAmount) {
		storeResources.add(startingAmount);
	}
	
	/**
	 * Method used at end of round to create mules from stores supply of smithore.
	 */
	public void createMules() {
		int smithoreAmount = storeResources.getAmount(ResourceType.SMITHORE);
		
		if (smithoreAmount > 1) {
			int muleNumber = smithoreAmount/2;
			storeResources.remove(ResourceType.SMITHORE, muleNumber*2);
			storeResources.add(ResourceType.MULE, muleNumber);
		}
	}
	
	/**
	 * Method sends a player into the store.
	 * @param player
	 */
	public void addPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Getter method returns any error messages created pertaining to store operations.
	 * @return errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
}
