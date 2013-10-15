package edu.gatech.cs2340.ui;

import edu.gatech.cs2340.io.InputReceiver;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		View: Graphic
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Shreyyas, Stephen
 * 		Modifications:		M6		10/15/13 Thomas Mark
 * 									Initial fleshing.
 * 											
 * 
 * 
 * 
 * 		Purpose: Graphic that moves around on the town
 */
public class TownSprite implements InputReceiver{
	private double x;
	private double y;
	private static final  int DX = 5;
	private static final  int DY = 5;
	private TownRenderer townRenderer;
	
	/**
	 * #M6
	 * Main constructor. Sets initialSprite position and provides a connection to TownRenderer for callback
	 * 
	 * @param x
	 * @param y
	 * @param townRenderer
	 */
	public TownSprite(double x, double y, TownRenderer townRenderer) {
		this.x = x;
		this.y = y;
		this.townRenderer = townRenderer;
	}
	
	
	@Override
	public void receiveInput(String input) {
		if (input.equals("UP")) {
			y = y - DY;
		} else if (input.equals("LEFT")) {
			x = x - DX;
		} else if (input.equals("DOWN")) {
			y = y + DY;
		} else if (input.equals("RIGHT")) {
			x = x + DX;
		}
		townRenderer.refresh();	
	}
	
	/**
	 * #M6
	 * Getter method for a Sprite's X position.
	 * 
	 * @return the x position of the sprite.
	 */	
	
	public double getX() {
		return this.x;
	}
	
	/**
	 * #M6
	 * Getter method for a Sprite's Y position.
	 * 
	 * @return the Y position of the sprite.
	 */	
	public double getY() {
		return this.y;
	}
}
