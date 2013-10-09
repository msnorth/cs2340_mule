
/**
 * 
 * @author Stephen Conway
 * 		Function group:		Model: Factory
 * 		Created for:		M6		10/7/13
 * 		Assigned to:		Maddy
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Create Map objects.
 * 				 Abstracted for static use as factory.
 * 				 Called by Game in initial setup.
 */
public abstract class MapGenerator {
	//Plains		0
	//River			1
	//Mountain 1	2
	//Mountain 2	3
	//Mountain 3	4
	//Town			5
	
	private static int[] standardMapConfig = {
		0, 0, 3, 0, 1, 0, 0, 2, 0,
		3, 0, 0, 0, 1, 0, 0, 0, 3,
		4, 0, 0, 0, 5, 0, 0, 0, 2,
		0, 0, 0, 0, 1, 2, 0, 0, 0,
		0, 0, 0, 2, 1, 0, 0, 0, 4
	};
	
	/**
	 * #M6
	 * Method to generate standard game map.
	 * 
	 * @return Map
	 */
	public static Map generateStandardMap() {
		
		return null;		
	}
	
	/**
	 * #EXTRA
	 * Method to generate random map
	 * 
	 * @return Map
	 */
	public static Map generateRandomMap() {
		
		return null;
	}
	
}
