import edu.gatech.mule.utils.InputReceiver;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		View
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Shreyyas, Stephen
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Graphic that moves around on the town
 */
public class TownSprite implements InputReceiver{
	private double x;
	private double y;
	private TownRenderer townRenderer;
	
	/**
	 * Main constructor. Sets initialSprite position and provides a connection to TownRenderer for callback
	 * 
	 * @param x
	 * @param y
	 * @param townRenderer
	 */
	public TownSprite(double x, double y, TownRenderer townRenderer) {
		this.x = x;
		this.y = y;
		this.townRenderer = townRenderer;
	}
	
	
	@Override
	public void receiveInput(String input) {
		// TODO Auto-generated method stub
		
	}
}
