
/**
 * 
 * @author Stephen Conway
 * 		Function group:		Model
 * 		Created for:		M6		10/7/13
 * 		Assigned to:		Maddy
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Parent class for all Tile objects that make up a grid on the game's map. 				 
 */
public abstract class Tile {
	//Tile and TileRenderer are paired as Model and View of the Tile concept
	private TileRenderer renderer;
	private Player owner;
	
	
	/**
	 * #FUTURE
	 * Method to get the production of a Tile. Based upon Tile type, MULE present, and 
	 * 	random events.
	 * 
	 * @return
	 */
	 public abstract ResourceAmount calculateProduction();
	 
	
	/**
	 * #M6
	 * Method to set owner of tile.
	 * Should handle "null" as returning Tile to neutral (through sale)
	 * Should call something to update its TileRenderer to reflect the change
	 * 
	 * @param newOwner
	 */
	public void setOwner(Player newOwner) {
		
	}
}
