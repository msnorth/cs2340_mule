package edu.gatech.cs2340.data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Logger;

/**
 * 
 * @author Stephen Conway Function group: Model: Data manager Created for: M6
 *         10/7/13 Assigned to: Maddy Modifications:
 * 
 * 
 * 
 *         Purpose: Hold Tiles. Return tiles as needed.
 * 
 */

// 5 x 9 grid
public class Map implements MapResponsibilities {


	// Can actually handle a map of any size
	/**
	 * #M6 Main constructor. Takes in 44 Tiles to hold.
	 * 
	 * @param tiles
	 */
	// the current title coordinates
	private int currNdx;
	private int nextUnownedNdx;

	private Tile[][] tiles;

	public Map(Tile[][] tiles) {
		this.tiles = tiles;
		currNdx = 0;
		nextUnownedNdx = 0;
	}

	/**
	 * Get next tile in row major order. Null if end of tiles reached. 
	 * Automatically resets.
	 */
	@Override
	public Tile getNextTile() {
		int rows = tiles.length;
		int cols = tiles[0].length;
		Tile result = null;
		if (currNdx < rows * cols) {
			result = getTileNumber(currNdx);
			currNdx++;
		}
		else {
			currNdx = 0;
		}
		return result;
	}

	/**
	 * Return tile at grid location. Null if out of bounds.
	 */
	@Override
	public Tile getTileAt(int r, int c) {
		Tile result = null;
		if (r < tiles.length && c < tiles[0].length && r > -1 && c > -1) {
			result = tiles[r][c];
		}
		return result;
	}

	@Override
	public Tile getTileAt(double x, double y) {
		//TODO
		//return getTileAt((int) (x / tiles.length), (int) (y / tiles[0].length));
		return null;
	}

	@Override
	public Tile getTileNumber(int ndx) {
		int row = ndx / tiles[0].length;
		int col = ndx % tiles[0].length;

		return getTileAt(row, col);
	}

	@Override
	public Tile getNextUnownedTile() {
		Tile result = getTileNumber(nextUnownedNdx);
		while (result != null && result.getOwner() != null) {
			nextUnownedNdx++;
			result = getTileNumber(nextUnownedNdx);
		}
		return result;
	}

	@Override
	public Tile getRandomUnownedTile() {
		int rows = tiles.length;
		int cols = tiles[0].length;
		Random rand = new Random();
		Tile result = null;
		do {
			result = getTileNumber(rand.nextInt(rows * cols));
		} while (result.getOwner() != null);
		
		return result;
	}

	@Override
	public void resetNextUnownedTile() {
		nextUnownedNdx = 0;
	}


}
