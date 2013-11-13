package edu.gatech.cs2340.engine;
import java.util.Random;

import edu.gatech.cs2340.data.GameData;
import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.Tile;
import edu.gatech.cs2340.io.KeyboardAdapter;
import edu.gatech.cs2340.sequencing.KeyWaiter;
import edu.gatech.cs2340.sequencing.MULETimer;
import edu.gatech.cs2340.sequencing.WaitedOn;
import edu.gatech.cs2340.sequencing.Waiter;
import edu.gatech.cs2340.test.DebugPrinter;
import edu.gatech.cs2340.ui.MainGameWindow;
import edu.gatech.cs2340.ui.MapRenderer;

/**
 * 
 * @author Shreyyas Vanarase
 * 		Function group:		Controller: Game Process
 * 		Created for:		M6		10/15/13
 * 		Assigned to:		Shreyyas
 * 		Modifications:		
 * 							M7		10/21/13 Stephen Conway
 * 									Removed WaitedOn interface. Runs synchronously.
 * 
 * 
 * 		Purpose: Allows for the purchasing of property.
 */
public class LandPurchaser 
{
	private GameData data;
	
	/**
	 * #M6
	 * Main constructor. Takes in references to current purchaser and to the MapRenderer to refresh the screen.
	 * 
	 * @param player
	 * @param mapRenderer
	 */
	public LandPurchaser(GameData data) 
	{
		this.data = data;
	}
	
	/**
	 * #M6
	 * Gets a random tile and waits for a key to be pressed. If player buys a property (tile), the 
	 * current money amount of the player is deducted. The tile is then assigned to the person.
	 */
	public void runSynchronous() {
		DebugPrinter.println("Running LandPurchaser synchronously");
		MainGameWindow.setMessage(String.format("Land purchase phase for %s\nPress space to purchase the tile.", data.getCurrentPlayer().getName()));
		data.startSaveSection();
		while (data.getPlayerNum() < data.getNumPlayers()) {
			Map map = data.getMap();
			Player currentPlayer = data.getCurrentPlayer();
		
			MapRenderer mapRenderer = new MapRenderer(map);
			MainGameWindow.setMainPanel(mapRenderer);
			mapRenderer.setDisplayPrices(true);
			
			
			Tile tile = map.getRandomUnownedTile();
			tile.setPrice(calculatePrice());
			tile.setActive(true); 	
			mapRenderer.refreshAll();
			int price = tile.getPrice();
			KeyboardAdapter adapter = KeyboardAdapter.getInstance();
			KeyWaiter confirmKey    = new KeyWaiter(KeyboardAdapter.KEY_NAME.CONFIRM);
			MULETimer timer         = new MULETimer(3000);
			WaitedOn[] waitingArray = {confirmKey, timer};
			
			adapter.setReceiver(confirmKey);
			timer.start();
			int killa = Waiter.waitForAny(waitingArray);
			
			if(killa == 0) {
				timer.stop();
				if (currentPlayer.deductMoney(price)) {
					tile.setOwner(currentPlayer);
					mapRenderer.refreshAll();							//Reflects changes on map
				}
			}
			
			tile.setActive(false);
			mapRenderer.setDisplayPrices(false); // all tile prices are set to zero here
			
			data.nextPlayer();
		}
		data.endSaveSection();
		data.resetPlayerNum();
		data.nextState();
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
		return (300 + (data.getRoundNum()+1)*randomValue);
	}
}
