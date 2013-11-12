package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.Tile;

/**
 * 
 * @author Stephen Conway M9
 * 
 * Class to cycle over tiles and add production rates to their owners.
 *
 */
public class ResourceProducer {
	private Map map;
	
	/**
	 * Main constructor. Needs access to all tiles to calculate production.
	 * @param map
	 */
	public ResourceProducer(Map map) {
		this.map = map;
	}
	
	/**
	 * Method to run (blocking) through all tiles and add their production/subtract mule used energy from owners
	 */
	public void runSynchronous() {
		Tile tile = map.getNextTile();
		while (tile != null) {
			Player owner = tile.getOwner();
			if (owner != null) {
				int ownerEnergy = owner.getResourceAmount(ResourceAmount.ResourceType.ENERGY);
				if (ownerEnergy > 0 && tile.hasMule()) {
					ResourceAmount production = tile.calculateProduction();
					owner.addResources(production);
					owner.addResources(ResourceAmount.ResourceType.ENERGY, -1);
				}
			}
			
			tile = map.getNextTile();
		}
	}
	
	
}
