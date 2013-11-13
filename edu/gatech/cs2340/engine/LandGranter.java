package edu.gatech.cs2340.engine;
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
 * @author Stephen Conway
 * 		Function group:		Controller: Game Process
 * 		Created for:		M6		10/7/13
 * 		Assigned to:		Shreyyas
 * 		Modifications:		M6		10/11/13 Shreyyas
 * 									Added in functionality to grant land to players. 
 * 							M6		10/15/13 Shreyyas/Tommy
 * 									Removed PlayerManager references.
 * 							M6		10/15/13 Stephen
 * 									Replaced InputReceiver interface with KeyWaiter usage
 *  * 						M7		10/21/13 Stephen Conway
 * 									Removed WaitedOn interface. Runs synchronously.
 * 
 * 		Purpose: Grant unowned plots of land to players in the first few rounds of the game.
 */
public class LandGranter  
{
	private static final long WAIT_FOR_NEXT_TILE = 500;
	
	private GameData data;

	
	/**
	 * #M6
	 * Main constructor. Takes in references to current grantee and to the MapRenderer to refresh the screen.
	 * 
	 * @param player
	 * @param mapRenderer
	 */
	public LandGranter(GameData data) 
	{
		this.data = data;
	}
	
	/**
	 * #M6
	 * Cycles through unowned map tiles, waiting for the user to press the select key.
	 * Once the key is pressed, the Tile is assigned to the Player, and the grant is finished.
	 * If the cycle reaches the end of the map before the user selects a Tile, a random Tile
	 * should be assigned to the Player.
	 */
	public void runSynchronous() 
	{
		DebugPrinter.println("Running LandGranter synchronously");
		
		
		data.startSaveSection();
		while (data.getPlayerNum() < data.getNumPlayers()) {
			MainGameWindow.setMessage(String.format("Land grant phase for %s", data.getCurrentPlayer().getName()));
			Map map = data.getMap();
			Player currentPlayer = data.getCurrentPlayer();
			
			MapRenderer mapRenderer = new MapRenderer(map);
			MainGameWindow.setMainPanel(mapRenderer);
			
			KeyWaiter keyWaiter = new KeyWaiter(KeyboardAdapter.KEY_NAME.CONFIRM);
			KeyboardAdapter.getInstance().setReceiver(keyWaiter);
			
			boolean grantFinished = false;
			
			while(!grantFinished)
			{
				Tile unownedTile = map.getNextUnownedTile();		 	//Obtains the nextUnownedTile. This is our currentTile now.
				if(unownedTile != null)								//As long as that unowned tile isn't null
				{
					unownedTile.setActive(true);
					mapRenderer.refreshAll();
					MULETimer timer = new MULETimer(WAIT_FOR_NEXT_TILE);						//Length of time the granter will stay on one tile
					timer.start();										//Starts the timer
					WaitedOn[] waitees = {keyWaiter, timer};
					int value = Waiter.waitForAny(waitees);				//Waits for any thread to finish
					
					if(value == 0) 
					{
						//currentPlayer.addTile(unownedTile);				//Assigns tile to player
						unownedTile.setOwner(currentPlayer);
						timer.stop();									//Kills timer
						map.resetNextUnownedTile();
						grantFinished = true;							//Ends land grant phase for that person
					}
					unownedTile.setActive(false);
				}
				else {
					Tile randomUnownedTile = map.getRandomUnownedTile();	//Gets random, unowned tile
					//currentPlayer.addTile(randomUnownedTile);				//Assigns it to player
					randomUnownedTile.setOwner(currentPlayer);
					map.resetNextUnownedTile();
					grantFinished = true;
				}
			}
			mapRenderer.refreshAll();
			data.nextPlayer();
		}
		data.endSaveSection();
		data.resetPlayerNum();
		data.nextState();
	}
}
