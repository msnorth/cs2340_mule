import edu.gatech.mule.utils.InputReceiver;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		View: Graphic
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Shreyyas, Stephen
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Graphic that moves around on top of the map
 */
public class MapSprite implements InputReceiver{
	private static final double UNITS_PER_TILE = 100.0;
	
	private double x;
	private double y;
	private MapRenderer mapRenderer;
	
	/**
	 * #M6
	 * Main constructor when MapSprite appears.
	 * 
	 * @param x
	 * @param y
	 * @param mapRenderer
	 */
	public MapSprite(double x, double y, MapRenderer mapRenderer) {
		this.x = x;
		this.y = y;
		this.mapRenderer = mapRenderer;
	}
	
	/**
	 * #M6
	 * Method to repaint the current tile, then repaint the sprite
	 */
	public void refresh() {
		
	}
	
	@Override
	public void receiveInput(String input) {
		// TODO Auto-generated method stub
		
	}

}
