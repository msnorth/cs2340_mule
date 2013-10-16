package edu.gatech.cs2340.engine;
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
 * 
 * 		Purpose: Grant unowned plots of land to players in the first few rounds of the game.
 */
public class LandGranter implements WaitedOn 
{
	private static final long WAIT_FOR_NEXT_TILE = 500;
	
	private Player currentPlayer;
	private boolean grantFinished;
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
	public LandGranter(Player player, Map map) 
	{
		currentPlayer 	 = player;
		this.map 		 = map;
		grantFinished 	 = false;
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
		MapRenderer mapRenderer = new MapRenderer(map);
		MainGameWindow.getInstance().setPanel(mapRenderer);
		
		while(!grantFinished)
		{
			Tile unownedTile = map.getNextUnownedTile();		 	//Obtains the nextUnownedTile. This is our currentTile now.
			if((unownedTile != null))								//As long as that unowned tile isn't null
			{
				adapter.setReceiver(keyWaiter);						//Sets focus to the keyWaiter to listen to user response
				timer = new MULETimer(WAIT_FOR_NEXT_TILE);						//Length of time the granter will stay on one tile
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
		
	/**
	 * Finished when user selects a free Tile or when user selected no Tile
	 * and instead a random one was assigned.
	 */
	@Override
	public boolean isFinished() {
		return grantFinished;
	}
}
