package edu.gatech.cs2340.ui;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		View: Background
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Shreyyas, Stephen
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Graphic of the inside of the town.
 */
public class TownRenderer extends GUIComponent{
	public static enum Side {
		NORTH, EAST, SOUTH, WEST
	}
	
	private GUIManager manager;
	private TownSprite sprite;
	
	/**
	 * #M6
	 * Main constructor. Takes in a GUIManager for callback, takes in a side to determine TownSprite position
	 * @param manager
	 * @param side
	 */
	public TownRenderer(GUIManager manager, Side side) {
		this.manager = manager;
		
		sprite = new TownSprite(0,0,this);
	}
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
