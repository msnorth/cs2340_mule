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
import edu.gatech.cs2340.ui.TileImageFactory;


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
 * 
 * 
 * 
 * 		Purpose: Grant unowned plots of land to players in the first few rounds of the game.
 */
public class LandGranter implements InputReceiver, WaitedOn 
{
	private Player currentPlayer;
	private MapRenderer mapRenderer;
	private boolean grantFinished;
	private boolean hasSelected;
	private Map map;
	private KeyWaiter keyWaiter;
	private MULETimer timer;
	private KeyboardAdapter adapter;
	private WaitedOn[] array = {keyWaiter, timer}; 

	
	/**
	 * #M6
	 * Main constructor. Takes in references to current grantee and to the MapRenderer to refresh the screen.
	 * 
	 * @param player
	 * @param mapRenderer
	 */
	public LandGranter(Player player, Map map, MapRenderer mapRenderer) 
	{
		currentPlayer 	 = player;
		this.mapRenderer = mapRenderer;
		this.map 		 = map;
		grantFinished 	 = false;
		hasSelected      = false;
		keyWaiter 		 = new KeyWaiter(KeyboardAdapter.CONFRIM_KEY);
		adapter			 = KeyboardAdapter.getInstance();
	}
	
	/**
	 * #M6
	 * Cycles through unowned map tiles, waiting for the user to press the select key.
	 * Once the key is pressed, the Tile is assigned to the Player, and the grant is finished.
	 * If the cycle reaches the end of the map before the user selects a Tile, a random Tile
	 * should be assigned to the Player.
	 */
	@Override
	public void run() 
	{
		while(!grantFinished)
		{
			Tile unownedTile = map.getNextUnownedTile();		 	//Obtains the nextUnownedTile. This is our currentTile now.
			if((unownedTile != null))								//As long as that unowned tile isn't null
			{
				adapter.setReceiver(keyWaiter);						//Sets focus to the keyWaiter to listen to user response
				timer = new MULETimer(3000);						//Length of time the granter will stay on one tile
				timer.run();										//Starts the timer
				int value = Waiter.waitForAny(array);				//Waits for any thread to finish
				
				if(value == 0) 
				{
					currentPlayer.addTile(unownedTile);				//Assigns tile to player
					timer.end();									//Kills timer
					mapRenderer.refresh();							//Reflects changes on map
					grantFinished = true;							//Ends land grant phase for that person
				}
				else if((map.getNextUnownedTile() == null) && value == 1)
				{
					Tile randomUnownedTile = map.getRandomUnownedTile();	//Gets random, unowned tile
					currentPlayer.addTile(randomUnownedTile);				//Assigns it to player
					mapRenderer.refresh();									//Reflects changes on map
					grantFinished = true;
				}
			}
		}
	}
		
	@Override
	public void receiveInput(String input) {
		//
	}

	@Override
	public boolean isFinished() {
		return grantFinished;
	}
	
	
	
	/*****************************************************************************
	 * New variables: hasSelected - LandGranter
	 * 			      unownedTile - Tile
	 * 				  tile		  - Tile
	 * 					
	 * 
	 * Methods:		  map.getNextUnownedTile() - map object should give me the nextUnownedTile
	 * 				  receiveInput("Enter")    - a boolean that says "yea, player pushed Enter"
	 * 				  map.getCurrentTile()
	 * 				  currentPlayer.addTile(tile)
	 * 
	 * Ideology: 	  While your current grant is not finished, get the nextUnownedTile
	 * 				  and this will be your currentUnownedTile, since it's the one you're
	 * 				  currently looking at. If that unownedTile is not null (meaning it 
	 * 			      doesn't exist) and you receive "SpaceKey" as an input (which gets passed 
	 * 				  eventually to Keyboard Adapter to do a VK_SpaceKey or something maybe) 
	 * 				  then get that current tile (should be unowned), add that to currentPlayer's
	 * 				  tileList, and the land granting phase should finish...FOR THAT PERSON.
	 * 				  Do it again for next person.
	 */
}
