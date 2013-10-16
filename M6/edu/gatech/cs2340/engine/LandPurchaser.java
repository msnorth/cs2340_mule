package edu.gatech.cs2340.engine;
import java.util.Random;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.Tile;
import edu.gatech.cs2340.io.KeyboardAdapter;
import edu.gatech.cs2340.sequencing.KeyWaiter;
import edu.gatech.cs2340.sequencing.MULETimer;
import edu.gatech.cs2340.sequencing.WaitedOn;
import edu.gatech.cs2340.sequencing.Waiter;
import edu.gatech.cs2340.ui.MainGameWindow;
import edu.gatech.cs2340.ui.MapRenderer;


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
public class LandPurchaser implements WaitedOn 
{
	private Player currentPlayer;
	private Map map;
	private boolean purchaseFinished;
	private int roundNumber;
	
	/**
	 * #M6
	 * Main constructor. Takes in references to current purchaser and to the MapRenderer to refresh the screen.
	 * 
	 * @param player
	 * @param mapRenderer
	 */
	public LandPurchaser(Player player, Map map, int roundNumber) 
	{
		currentPlayer 	 = player;
		this.map 		 = map;
		purchaseFinished = false;
		this.roundNumber = roundNumber;
	}
	
	/**
	 * #M6
	 * Gets a random tile and waits for a key to be pressed. If player buys a property (tile), the 
	 * current money amount of the player is deducted. The tile is then assigned to the person.
	 */
	@Override
	public void run() {
		MapRenderer mapRenderer = new MapRenderer(map);
		MainGameWindow.getInstance().setPanel(mapRenderer);
		
		Tile tile = map.getRandomUnownedTile();
		tile.setActive(true); 				
		
		int price               = calculatePrice();		
		KeyboardAdapter adapter = KeyboardAdapter.getInstance();
		KeyWaiter confirmKey    = new KeyWaiter(KeyboardAdapter.CONFRIM_KEY);
		MULETimer timer         = new MULETimer(3000);
		WaitedOn[] waitingArray = {confirmKey, timer};
		
		adapter.setReceiver(confirmKey);
		int killa = Waiter.waitForAny(waitingArray);
		
		if(killa == 0) {
			if (currentPlayer.deductMoney(price)) {
				currentPlayer.addTile(tile);			
				timer.end();									//Kills timer
				mapRenderer.refresh();							//Reflects changes on map
			}
		}
		
		tile.setActive(false);
		purchaseFinished = true;							//Ends land purchase phase for that person
	}
	
	/**
	 * Calculates the price associated with each tile
	 * 
	 * @param tile
	 * @return price of the property the user will select
	 */
	public int calculatePrice() {
		Random rand     = new Random();
		int randomValue = rand.nextInt(101);
		
		return (300 + roundNumber*randomValue);
	}
	
	/**
	 * Finished when user purchases Tile or when time runs out
	 */
	@Override
	public boolean isFinished() {
		return purchaseFinished;
	}
}
