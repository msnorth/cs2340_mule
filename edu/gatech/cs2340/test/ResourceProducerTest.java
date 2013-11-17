/**
 * 
 */
package edu.gatech.cs2340.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.MapHolder;
import edu.gatech.cs2340.data.MapResponsibilities;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.data.RiverTile;
import edu.gatech.cs2340.data.Tile;
import edu.gatech.cs2340.engine.ResourceProducer;

/**
 * @author Dan
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class ResourceProducerTest {

	/**
	 * Array of colors to choose from
	 */
	private static Color[] colors = { Color.BLUE, Color.YELLOW, Color.GREEN,
			Color.RED };

	/**
	 * There are 4 colors to choose from
	 */
	private static int numColors = 4;

	// private static ResourceType[] resourceTypes = {
	// ResourceAmount.ResourceType.FOOD,
	// ResourceAmount.ResourceType.CRYSTITE,
	// ResourceAmount.ResourceType.ENERGY,
	// ResourceAmount.ResourceType.SMITHORE };

	/**
	 * The class to be tested
	 */
	ResourceProducer resProd;

	/**
	 * Mocked object of GameData to return the map
	 */
	@Mock
	MapHolder gameData;

	/**
	 * Mocked object of Map to return tiles
	 */
	@Mock
	MapResponsibilities map;

	/**
	 * Holds the tiles to test the resource producer
	 */
	ArrayList<Tile> tiles;

	/**
	 * Holds the tiles to test the resource producer
	 */
	ArrayList<Player> players;

	/**
	 * Number of tiles in the array
	 */
	int numTiles;
	/**
	 * Number of players in the array
	 */
	static int numPlayers = 4;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// MockitoAnnotations.initMocks(this); // initialize mocked classes
		gameData = Mockito.mock(MapHolder.class);
		map = Mockito.mock(MapResponsibilities.class);
		when(gameData.getMap()).thenReturn((Map) map); // return the map
		// initialize players
		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player("" + i, "" + i, colors[i % numColors]));
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		gameData = null;
		map = null;
		players.clear();
		tiles.clear();
	}

	@Test
	public void runSynchronousTest() {
		numTiles = 5;
		for (int j = 0; j < numTiles; j++) {
			Tile curTile = new RiverTile("" + j, null);
			if (j <= numPlayers){
				curTile.setOwner(players.get(j));
			}
			tiles.add(curTile);
		}
		when(map.getNextTile()).thenReturn(tiles.get(0)).thenReturn(tiles.get(1)).thenReturn(tiles.get());
		assertEquals("", 0, 0);
	}

	// private static void printPlayerResources() {
	// for (int i = 0; i < players.length; i++) {
	// Player curPlayer = players[i];
	// System.out.println("Player " + i + ": ");
	// System.out.println("Food " + i + ": "
	// + curPlayer.getResourceAmount(ResourceType.FOOD));
	// System.out.println("Ore " + i + ": "
	// + curPlayer.getResourceAmount(ResourceType.SMITHORE));
	// System.out.println("Energy " + i + ": "
	// + curPlayer.getResourceAmount(ResourceType.ENERGY));
	// System.out.println("Crystite " + i + ": "
	// + curPlayer.getResourceAmount(ResourceType.CRYSTITE));
	// System.out.println("-------------");
	// }
	// }

}
