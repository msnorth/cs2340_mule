package edu.gatech.cs2340.engine;
import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.data.Tile;
import edu.gatech.cs2340.io.InputReceiver;
import edu.gatech.cs2340.io.KeyboardAdapter;
import edu.gatech.cs2340.sequencing.KeyWaiter;
import edu.gatech.cs2340.sequencing.MULETimer;
import edu.gatech.cs2340.sequencing.WaitedOn;
import edu.gatech.cs2340.sequencing.Waiter;
import edu.gatech.cs2340.ui.MapRenderer;
import edu.gatech.cs2340.ui.TileRenderer;


/**
 * 
 * @author Shreyyas Vanarase
 * 		Function group:		Controller: Game Process
 * 		Created for:		M6		10/15/13
 * 		Assigned to:		Shreyyas
 * 		Modifications:		
 * 
 * 
 * 
 * 		Purpose: Allows for the purchasing of property.
 */
public class LandPurchase implements InputReceiver, WaitedOn 
{
	private Player currentPlayer;
	private Map map;
	private MapRenderer mapRenderer;
	private boolean purchaseFinished;
	
	/**
	 * #M6
	 * Main constructor. Takes in references to current purchaser and to the MapRenderer to refresh the screen.
	 * 
	 * @param player
	 * @param mapRenderer
	 */
	public LandPurchase(Player player, Map map, MapRenderer mapRenderer) 
	{
		currentPlayer 	 = player;
		this.mapRenderer = mapRenderer;
		this.map 		 = map;
		purchaseFinished = false;
	}
	
	/**
	 * #M6
	 * Gets a random tile and waits for a key to be pressed. If player buys a property (tile), the 
	 * current money amount of the player is deducted. The tile is then assigned to the person.
	 */
	@Override
	public void run() {
		
		Tile tile = map.getRandomUnownedTile();
		tile.setActive(true); 					//use it to highlight it black
		int price = calculatePrice(tile);	
		
		KeyboardAdapter adapter= KeyboardAdapter.getInstance();

		KeyWaiter confirmKey = new KeyWaiter(KeyboardAdapter.CONFRIM_KEY);
		MULETimer timer  = new MULETimer(3000);
		
		WaitedOn[] waitingArray = {confirmKey, timer};
		
		adapter.setReceiver(confirmKey);
		int killa = Waiter.waitForAny(waitingArray);
		
		if(killa == 0) {
			currentPlayer.deductMoney(price);
			currentPlayer.addTile(tile);			
			timer.end();									//Kills timer
			mapRenderer.refresh();							//Reflects changes on map
		}
		
		tile.setActive(false);
		purchaseFinished = true;							//Ends land grant phase for that person
	}
	
	/**
	 * Calculates the price associated with each tile
	 * 
	 * @param tile
	 * @return
	 */
	public int calculatePrice(Tile tile) {
		return 0;
	}
	@Override
	public void receiveInput(String input) {
		//
	}
	
	@Override
	public boolean isFinished() {
		return purchaseFinished;
	}
}
