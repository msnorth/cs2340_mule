package edu.gatech.cs2340.data;

import java.util.Random;

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

	/**
	 * @return Next unknown tile, setting pointers to point to it
	 *  will return null if all tiles are owned
	 */
	@Override
	public Tile getNextUnownedTile() {
		Tile result = getTileNumber(nextUnownedNdx);
		while (result != null && result.isOwned()) {
			nextUnownedNdx++;
			result = getTileNumber(nextUnownedNdx);
		}
		nextUnownedNdx++;
		return result;
	}

	/**
	 * Creates a list of unowned tiles and returns a random element from this list.
	 * Sets pointers to point to the random unowned tile
	 * @return Random tile
	 */
	@Override
	public Tile getRandomUnownedTile() {
		int rows = tiles.length;
		int cols = tiles[0].length;
		Random rand = new Random();
		Tile result = null;
		do {
			result = getTileNumber(rand.nextInt(rows * cols));
		} while (result.isOwned());
		
		return result;
	}

//	/**
//	 * Helper method to get x y coordinates of tile
//	// and set currX and currY
//	 * @param t Tile to which to point the currX and currY of Map
//	 */
//	private void setCurrentTile(Tile t) {
//		outerloop: for (int i = 0; i < tiles.length; i++) {
//			for (int j = 0; j < tiles[0].length; j++) {
//				if (tiles[i][j].compareTo(t)) {
//					currX = i;
//					currY = j;
//					break outerloop;
//				}
//			}
//		}
//	}

	// resets ownership of next tile
	// sets current tile pointers to next tile

	@Override
	public void resetNextUnownedTile() {
		nextUnownedNdx = 0;
	}

	/**
	 * Get the number of tiles in the map
	 * @return Number of tiles in map
	 */
	public int getNumTiles(){
		return tiles.length*tiles[0].length;
	}
	
	/**
	 * Get length in x direction
	 * @return Number of columns
	 */
	public int getNumCols(){
		return tiles[0].length;
	}
	
	/**
	 * Get length in y direction
	 * @return Number of rows
	 */
	public int getNumRows(){
		return tiles.length;
	}
	
//	/**
//	 * Set the current X and Y that the map is internally pointing to
//	 * TODO Is there a better way to do this to reduce coupling? Visitor class?
//	 * @param currX Next X coordinate of tile to retrieve
//	 * @param currY Next Y coordinate of tile to retrieve
//	 */
//	public void setCurrXY(int currX, int currY){
//		this.currX = currX;
//		this.currY = currY;
//	}
}
