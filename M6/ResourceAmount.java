
/**
 * 
 * @author Stephen Conway
 * 		Function group:		Model: Data holder
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Stephen
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Encapsulate a count of the game's four resource types.	 
 */
public class ResourceAmount {
	public static enum ResourceType {
		SMITH_ORE, FOOD, ENERGY, CRYSTITE
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
