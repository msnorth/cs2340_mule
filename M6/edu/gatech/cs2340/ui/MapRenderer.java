package edu.gatech.cs2340.ui;
import java.awt.GridLayout;

import edu.gatech.cs2340.data.Map;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		View: Background
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Dan
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Provide a graphical representation of the Map
 */
public class MapRenderer extends GUIComponent{
	private Map map;
	private GUIManager manager;
	private MapSprite sprite;
	
	/**
	 * #M6
	 * Main constructor. Connects renderer with data (map) and with callback line (manager).
	 * @param map
	 */
	public MapRenderer(GUIManager manager, Map map) {
		this.manager = manager;
		this.map = map;
		sprite = new MapSprite(0,0,this);
		
		setLayout(new GridLayout(5,9));
	}
	
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		// iterate through each tile with Map.getNextTile()
	}
	
	/**
	 * #M6
	 * Refresh method to be called on a Tile at a specific world coordinate.
	 * Used with Sprite's position to prevent streaking without refreshing all of the tiles.
	 * 
	 * @param x
	 * @param y
	 */
	public void refresh(double x, double y) {
		
	}
	
	/**
	 * #M6
	 * Refresh method to be called on a specific Tile number.
	 * Used with LandGranter to update border colors.
	 * 
	 * @param ndx
	 */
	public void refresh(int ndx) {
		
	}

}
