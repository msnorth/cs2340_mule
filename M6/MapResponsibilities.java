
/**
 * 
 * @author Stephen Conway
 * 		Function group:		Development Contract
 * 		Created for:		M6		10/7/13
 * 		Assigned to:		
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Outline design responsibilities of Map
 * 				 
 */
public interface MapResponsibilities {
	/**
	 * #M6
	 * Method to get the next Tile from the set of all tiles.
	 * Used by MapRenderer
	 * 
	 * @return
	 */
	public abstract Tile getNextTile();
	
	/**
	 * #M6
	 * Method to get Tile at grid location.
	 * Used by MapRenderer
	 * 
	 * @param r
	 * @param c
	 * @return
	 */
	public abstract Tile getTileAt(int r, int c);
	
	
	/**
	 * #M6
	 * Method to get Tile at world position.
	 * Used by MapRenderer base on MapSprite position.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public abstract Tile getTileAt(double x, double y);
	
	/**
	 * #M6
	 * Method to get specific tile in row major order.
	 * Used by MapRenderer
	 * 
	 * @param ndx
	 * @return
	 */
	public abstract Tile getTileNumber(int ndx);
	
	/**
	 * #M6
	 * Method to get next Tile that has no owner in row major order
	 * When this method is called after all unowned tiles have been iterrated, 
	 * it should return null.
	 * Used by LandGranter
	 * 
	 * @return
	 */
	public abstract Tile getNextUnownedTile();
	
	
	/**
	 * #M6 
	 * Method to reset unowned tile iterator.
	 * Used by LandGranter
	 */
	public abstract void resetNextUnownedTile();
	
	/**
	 * #M6
	 * Method to get a random Tile without an owner.
	 * Used by LandAuction
	 * 
	 * @return
	 */
	public abstract Tile getRandomUnownedTile();
}
