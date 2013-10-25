package edu.gatech.cs2340.data;

/**
 * 
 * @author Stephen Conway
 * 		Function group:		Model: Data holder
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Stephen
 * 		Modifications:		M8		10/25/13	Thomas Mark
 * 									Added ability to add and remove specific resource amounts.						
 * 
 * 
 * 
 * 		Purpose: Encapsulate a count of the game's four resource types.	 
 */
public class ResourceAmount {
	public static enum ResourceType {
		SMITHORE, FOOD, ENERGY, CRYSTITE, MULE
	}
	
	private int[] amounts;
	
	/**
	 * #M6
	 * Main constructor. All resources start at 0.
	 */
	public ResourceAmount() {
		amounts = new int[ResourceType.values().length];
	}
	
	/**
	 * #M6
	 * Method to get the amount of a given resource present in 
	 *  the pile.
	 * 
	 * @param type
	 * @return
	 */
	public int getAmount(ResourceType type) {
		return amounts[type.ordinal()];
	}
	
	/**
	 * #M6
	 * Method to add the resources in the input ResourceAmount to this one.
	 * 
	 * @param resourceAmount
	 */
	public void add(ResourceAmount resourceAmount) {
		ResourceType[] types = ResourceType.values();
		for (int i=0; i<types.length; i++) {
			amounts[i] += resourceAmount.getAmount(types[i]);
		}
	}
	
	/**
	 * #M8
	 * Method to add a particular resource amount to this collection of resources.
	 * 
	 * @param resourceType, amount
	 */
	public void add(ResourceType resourceType, int amount) {
		amounts[resourceType.ordinal()] += amount;
	}
	
	/**
	 * #M6
	 * Method to remove the resources in the input ResourceAmount from this one.
	 * 
	 * @param resourceAmount
	 */
	public void remove(ResourceAmount resourceAmount) {
		ResourceType[] types = ResourceType.values();
		for (int i=0; i<types.length; i++) {
			amounts[i] -= resourceAmount.getAmount(types[i]);
		}
	}
	
	/**
	 * #M8
	 * Method to remove a particular resource amount from this collection of resources.
	 * 
	 * @param resourceType, amount
	 */
	public void remove(ResourceType resourceType, int amount) {
		amounts[resourceType.ordinal()] -= amount;
	}
	
	/**
	 * #M6
	 * Method to determine if the input ResourceAmount is a subset of this one.
	 * 
	 * @param resourceAmount
	 */
	public boolean contains(ResourceAmount resourceAmount) {
		boolean result = true;
		ResourceType[] types = ResourceType.values();
		for (int i=0; i<types.length; i++) {
			result &= amounts[i] >= resourceAmount.getAmount(types[i]);
		}
		return result;
	}
}
