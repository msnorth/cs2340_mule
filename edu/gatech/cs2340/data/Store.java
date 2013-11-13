package edu.gatech.cs2340.data;

import java.io.Serializable;

import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.ui.MainGameWindow;
import edu.gatech.cs2340.ui.StatusBar;

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
 * 									type to the player's resources. Added getResourceType Method.
 * 							M8		10/29/13 Thomas Mark
 * 									Modified Mule parameter type to actual ResourceType as opposed 
 * 									to String.
 * 							M8 		11/4/13  Shreyyas Vanarase
 * 									Allowed proper selling of mules in the store. Removed mulePrices 
 * 									since it was a ResourceAmount and Mules are not resources. Edited the getMulePrice
 * 									method to get the mule price of a specific resource.	
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
public class Store implements Serializable {
	public static final int FOOD_PRICE = 30;
	public static final int ENERGY_PRICE = 25;
	public static final int SMITHORE_PRICE = 50;
	public static final int CRYSTITE_PRICE = 100;
	
	public static final int FOOD_MULE = 130;
	public static final int ENERGY_MULE = 125;
	public static final int SMITHORE_MULE = 150;
	public static final int CRYSTITE_MULE = 200;
	
	private static final int FOOD    	  = 150;
	private static final int ENERGY      = 150;
	private static final int SMITHORE    = 150;
	private static final int CRYSTITE    = 150;
	
	private static ResourceAmount storeResources = new ResourceAmount(SMITHORE, FOOD, ENERGY, CRYSTITE);
	private static ResourceAmount storePrices = new ResourceAmount(SMITHORE_PRICE, FOOD_PRICE, ENERGY_PRICE, CRYSTITE_PRICE);
	
	private int numberOfMules;
	
	private Player player;
	private String message;
	private StatusBar statBar;
	
	/**
	 * M8
	 * Singleton constructor
	 */
	public Store() {
		numberOfMules = createMules();
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
		statBar = MainGameWindow.getLowerPanel();
		statBar.refreshPlayer(player);
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
		statBar = MainGameWindow.getLowerPanel();
		statBar.refreshPlayer(player);
		return true;
	}
	
	/**
	 * Store sells mule to the player.
	 * @param type
	 * @return
	 */
	public boolean sellMule(ResourceType type) {
		int cost = 0;
		
		if(type.name().equals("ENERGY")) {
			cost = ENERGY_MULE;
		}
		else if(type.name().equals("FOOD")) {
			cost = FOOD_MULE;
		}
		else cost = SMITHORE_MULE;
		
		if (player.hazMule()) {
			message = "You already have a mule.";
			return false;
		}
		if (numberOfMules == 0) {
			message = "There are no mules available to purchase.";
			return false;
		}
		if (player.getMoney() < cost) {
			message = "You do not have enough money!";
			return false;
		}
		--numberOfMules;
		player.deductMoney(cost);
		player.addMule(new Mule(type));
		numberOfMules = createMules();
		statBar = MainGameWindow.getLowerPanel();
		statBar.refreshPlayer(player);
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
	 * Get the number of mules
	 * @return
	 */
	public int getMuleAmount() {
		return numberOfMules;
	}
	/**
	 * Method used at end of round to create mules from stores supply of smithore.
	 */
	public int createMules() {
		int smithoreAmount = storeResources.getAmount(ResourceType.SMITHORE);
		
		if (smithoreAmount > 1) {
			int muleNumber = smithoreAmount/2;
			storeResources.remove(ResourceType.SMITHORE, muleNumber*2);
			numberOfMules = muleNumber;
		}
		return numberOfMules;
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
	
	/**
	 * Gets the store price of a resource 
	 * @param resource
	 * @return int 
	 */
	public static int getResourcePrice(ResourceType resource) {
		return storePrices.getAmount(resource);
	}
	
	/**
	 * Gets the store price of a specific Mule type 
	 * @param resource
	 * @return int 
	 */
	public static int getMulePrice(ResourceType resource) {
		if(resource.name().equals("ENERGY")) {
			return ENERGY_MULE;
		}
		else if(resource.name().equals("FOOD")) {
			return FOOD_MULE;
		}
		else if(resource.name().equals("CRYSTITE")) {
			return CRYSTITE_MULE;
		}
		else return SMITHORE_MULE;
	}
}
