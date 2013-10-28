package edu.gatech.cs2340.data;

import edu.gatech.cs2340.data.ResourceAmount.ResourceType;

/**
 *@author Thomas Mark
 * 		Function group:		Model: Service provider
 * 		Created for:		M8		10/25/13
 * 		Assigned to:		Thomas Mark
 * 		Modifications:		M8		10/27/13 Thomas Mark
 * 									Added mule handling.
 * 							M8      10/28/13 Shreyyas Vanarase
 * 									Selling mules of a specific type should also decrease
 * 									the amount of that type from the store and add that 
 * 									type to the player's resources. Added getResourceType Method
 * 									
 * 							
 * 		Allows the player to:
 * 				- buy resources
 * 				- sell resources
 * 				- buy mule
 * 		Notes:
 * 			- Example of Singleton design pattern	
 * 			- "... = Store.getStore()" should be called when the game is created, then something 
 * 			  like "....addStartingResources(startingResources);"
 * 			- A player needs to be added to the store (symbolizes player entering store)
 * 			- Methods are from the store's perspective 
 * 			  (e.g. theStore.sellResources(...) : player buying resources)
 * 			- Classes that use store should use the message method to pass info to the player	 		 
 */
public class Store {
	private static Store theStore;
	private static ResourceAmount storeResources;
	private static ResourceAmount storePrices;
	private static ResourceAmount mulePrices;
	
	private static final int FOOD_PRICE = 30;
	private static final int ENERGY_PRICE = 25;
	private static final int SMITHORE_PRICE = 50;
	private static final int CRYSITE_PRICE = 100;
	
	private static final int FOOD_MULE = 130;
	private static final int ENERGY_MULE = 150;
	private static final int SMITHORE_MULE = 175;
	private static final int CRYSTITE_MULE = 200;
	
	private int FOOD     = 1000;
	private int ENERGY   = 1000;
	private int SMITHORE = 1000;
	private int CRYSTITE = 1000;
	
	private Player player;
	private String message;
	
	/**
	 * M8
	 * Singleton constructor
	 */
	protected Store() {
		storeResources = new ResourceAmount();	
		storePrices = new ResourceAmount();
		mulePrices  = new ResourceAmount();
		
		storePrices.add(ResourceType.FOOD, FOOD_PRICE);
		storePrices.add(ResourceType.ENERGY, ENERGY_PRICE);
		storePrices.add(ResourceType.SMITHORE, SMITHORE_PRICE);
		storePrices.add(ResourceType.CRYSTITE, CRYSITE_PRICE);
		
		mulePrices.add(ResourceType.FOOD, FOOD_MULE);
		mulePrices.add(ResourceType.FOOD, ENERGY_MULE);
		mulePrices.add(ResourceType.FOOD, SMITHORE_MULE);
		mulePrices.add(ResourceType.FOOD, CRYSTITE_MULE);	
	}
	
	/**
	 * Getter method for the store. If the store does not exist, it is created.
	 * @return theStore
	 */
	public static Store getStore() {
		if (theStore == null) {
			theStore = new Store();
		//	theStore.addStartingResources(storeResources);
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
			message = "You cannot sell that much.";
			return false;
		}
		player.removeResources(resource, amount);
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
			message = "The store only has " + storeAmount + " " 
							+ resource.toString().toLowerCase() + ".";
			return false;
		}
		if (player.getMoney() < cost) {
			message = "You do not have enough money!";
			return false;
		}
		storeResources.remove(resource, amount);
		player.deductMoney(cost);
		player.addResources(resource, amount);
		return true;
	}
	
	/**
	 * Store sells mule to the player.
	 * @param type
	 * @return
	 */
	public boolean sellMule(ResourceType type) {
		int cost = mulePrices.getAmount(type);
		String muleType = type.toString();
		
		if (player.hazMule()) {
			message = "You already have a mule.";
			return false;
		}
		if (storeResources.getAmount(ResourceType.MULE) == 0) {
			message = "There are no mules available to purchase.";
			return false;
		}
		if (player.getMoney() < cost) {
			message = "You do not have enough money!";
			return false;
		}
		storeResources.remove(ResourceType.MULE, 1);
		storeResources.remove(type, 1);
		player.deductMoney(cost);
		player.addMule(new Mule(muleType));
		player.addResources(type, 1);
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
	 * Getter method returns any messages created pertaining to store operations.
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the amount of a resource that the store has
	 * @param resource
	 * @return int 
	 */
	public int getResourceAmount(ResourceType resource) {
		return storeResources.getAmount(resource);
	}
}
