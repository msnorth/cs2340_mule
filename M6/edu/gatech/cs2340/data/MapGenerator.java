package edu.gatech.cs2340.data;

import java.util.UUID;

import edu.gatech.cs2340.ui.HillTile;
import edu.gatech.cs2340.ui.MountainTile;
import edu.gatech.cs2340.ui.PeakTile;
import edu.gatech.cs2340.ui.PlainsTile;
import edu.gatech.cs2340.ui.RiverTile;
import edu.gatech.cs2340.ui.TileRenderer;
import edu.gatech.cs2340.ui.TownTile;

/**
 * 
 * @author Stephen Conway Function group: Model: Factory Created for: M6 10/7/13
 *         Assigned to: Maddy Modifications:
 * 
 * 
 * 
 *         Purpose: Create Map objects. Abstracted for static use as factory.
 *         Called by Game in initial setup.
 */
public abstract class MapGenerator {
	// Plains 0
	// River 1
	// Hill 2
	// Mountain 3
	// Peak 4
	// Town 5

	private static int[][] standardMapConfig = { { 0, 0, 3, 0, 1, 0, 0, 2, 0 },
			{ 3, 0, 0, 0, 1, 0, 0, 0, 3 }, { 4, 0, 0, 0, 5, 0, 0, 0, 2 },
			{ 0, 0, 0, 0, 1, 2, 0, 0, 0 }, { 0, 0, 0, 2, 1, 0, 0, 0, 4 } };

	/**
	 * #M6 Method to generate standard game map.
	 * 
	 * @return Map
	 */
	public static Map generateStandardMap() {
		Tile[][] tiles = new Tile[standardMapConfig.length][standardMapConfig[0].length];
		Tile.initialize();
		int type;
		for (int i = 0; i < standardMapConfig.length; i++) {
			for (int j = 0; j < standardMapConfig[0].length; j++) {
				Tile tile;
				type = standardMapConfig[i][j];
				switch (type) {
				case 0:
					tile = new PlainsTile(UUID.randomUUID().toString(), null,
							new TileRenderer());
					break;
				case 1:
					tile = new RiverTile(UUID.randomUUID().toString(), null,
							new TileRenderer());
					break;
				case 2:
					tile = new HillTile(UUID.randomUUID().toString(), null,
							new TileRenderer());
					break;
				case 3:
					tile = new MountainTile(UUID.randomUUID().toString(), null,
							new TileRenderer());
					break;
				case 4:
					tile = new PeakTile(UUID.randomUUID().toString(), null,
							new TileRenderer());
					break;
				case 5:
					tile = null;
					break;
				default:
					tile = new PlainsTile(UUID.randomUUID().toString(), null,
							new TileRenderer());
					break;
				}
				tiles[i][j] = tile;
			}
		}
		Map map = new Map(tiles);

		return map;
	}

	/**
	 * #EXTRA Method to generate random map
	 * 
	 * @return Map
	 */
	public static Map generateRandomMap() {

		return null;
	}

}
