package edu.gatech.cs2340.ui;
import edu.gatech.cs2340.data.Tile;

/**
 * 
 * @author Stephen Conway
 * 		Function group:		View: Background
 * 		Created for:		M6		10/7/13
 * 		Assigned to:		Dan
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: GUI representation of Tile.				 
 */
public class TileRenderer extends GUIComponent{
	private static final long serialVersionUID = 1L;

	//Tile and TileRenderer are paired as Model and View of the Tile concept
	private Tile tile;
	
	private boolean active;
	
	/**
	 * #M6
	 * Method to determine if tile is highlighted as active
	 * 
	 * @return
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * #M6
	 * Method to set activity of TileRenderer.
	 * If an unowned tile is active, its border should be black
	 * If an unowned tile is inactive, its border should be "clear"
	 * If an owned tile is active, its border should be dashed with the Player color
	 * If an owned tile is inactive, its border should be solid with the Player color
	 */
	public void setActive(boolean activity) {
		
	}
	
	public void refresh() {
		
	}
	
	
}
