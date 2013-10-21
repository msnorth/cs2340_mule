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

<<<<<<< HEAD
	// increment the current tile coordinates and return the next tile
=======
	/**
	 * Get next tile in row major order. Null if end of tiles reached. 
	 * Automatically resets.
	 */
>>>>>>> 2393cea41c2d70311bb028f29056d3dcdb4e35db
	@Override
	public Tile getNextTile() {
		int rows = tiles.length;
		int cols = tiles[0].length;
		Tile result = null;
		if (currNdx < rows * cols) {
			result = getTileNumber(currNdx);
			currNdx++;
		}
<<<<<<< HEAD
		Tile tile = getTileAt(currX, currY);
		// If we've reached the end of the tiles, reset for next time
		if (currX == tiles.length && currY == tiles[0].length){
			currX = 0;
			currY = 0;
		}
		return tile;
=======
		else {
			currNdx = 0;
		}
		return result;
>>>>>>> 2393cea41c2d70311bb028f29056d3dcdb4e35db
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

<<<<<<< HEAD
	/**
	 * @return Next unknown tile, setting pointers to point to it
	 *  will return null if all tiles are owned
	 */
=======
>>>>>>> 2393cea41c2d70311bb028f29056d3dcdb4e35db
	@Override
	public Tile getNextUnownedTile() {
		Tile result = getTileNumber(nextUnownedNdx);
		while (result != null && result.getOwner() != null) {
			nextUnownedNdx++;
			result = getTileNumber(nextUnownedNdx);
		}
		nextUnownedNdx++;
		return result;
	}

<<<<<<< HEAD
	// 
	/**
	 * Creates a list of unowned tiles and returns a random element from this list.
	 * Sets pointers to point to the random unowned tile
	 * @return Random tile
	 */
=======
>>>>>>> 2393cea41c2d70311bb028f29056d3dcdb4e35db
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

<<<<<<< HEAD
	/**
	 * Helper method to get x y coordinates of tile
	// and set currX and currY
	 * @param t Tile to which to point the currX and currY of Map
	 */
	private void setCurrentTile(Tile t) {
		outerloop: for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (tiles[i][j].compareTo(t)) {
					currX = i;
					currY = j;
					break outerloop;
				}
			}
		}

	}

	// resets ownership of next tile
	// sets current tile pointers to next tile
=======
>>>>>>> 2393cea41c2d70311bb028f29056d3dcdb4e35db
	@Override
	public void resetNextUnownedTile() {
		nextUnownedNdx = 0;
	}

<<<<<<< HEAD
	/**
	 * Get the number of tiles in the map
	 * @return Number of tiles in map
	 */
	public int getNumTiles(){
		return tiles.length*tiles[0].length;
	}
	
	/**
	 * Set the current X and Y that the map is internally pointing to
	 * TODO Is there a better way to do this to reduce coupling? Visitor class?
	 * @param currX Next X coordinate of tile to retrieve
	 * @param currY Next Y coordinate of tile to retrieve
	 */
	public void setCurrXY(int currX, int currY){
		this.currX = currX;
		this.currY = currY;
	}
=======

	public int getNumTiles(){
		return tiles.length*tiles[0].length;
	}
>>>>>>> 2393cea41c2d70311bb028f29056d3dcdb4e35db
}
