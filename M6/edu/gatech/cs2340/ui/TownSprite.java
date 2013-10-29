package edu.gatech.cs2340.ui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.io.InputReceiver;
import edu.gatech.cs2340.io.KeyboardAdapter;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		View: Graphic
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Shreyyas, Stephen
 * 		Modifications:		M6		10/15/13 Thomas Mark
 * 									Initial fleshing.
 * 							M7		10/20/13 Thomas Mark
 * 									Sprite needs to know what player it is attached to.
 * 
 * 		Purpose: Graphic that moves around on the town
 */
public class TownSprite {
	private final int SPEED = 10;
	private final double WORLD_PIXEL_RATIO = 675.0/1000; //pixels / worldUnit (1000 per Tile)
	public enum SPRITE_LOCATION {
		TOWN, STORE, PUB, EXITED
	}
	
	private int x;
	private int y;
	private int h;
	private int w;
	private Player player;
	private  Image image;
	private SPRITE_LOCATION location;
	
	//needs boolean for isInTown (true when in town)
	//needs hasLeftTown
	//needs constructor that has player
	//comment out current constructor
	
	/**
	 * #M6
	 * Main constructor. Sets initialSprite position and provides a connection to TownRenderer for callback
	 * 
	 * @param x
	 * @param y
	 * @param townRenderer
	 */
	public TownSprite(Player player) {
		this.player = player;
		resetPosition();
		
		SpriteImageLoader loader = new SpriteImageLoader();
		ImageIcon ii = loader.getImage(player);
        image = ii.getImage();
	}	
	
	public void update() {
		KeyboardAdapter kba = KeyboardAdapter.getInstance();
		int dx = 0;
		int dy = 0;
		if (kba.isPressed(KeyboardAdapter.KEY_NAME.UP)) {
			dy -= SPEED;
		}
		if (kba.isPressed(KeyboardAdapter.KEY_NAME.DOWN)) {
			dy += SPEED;
		}
		if (kba.isPressed(KeyboardAdapter.KEY_NAME.LEFT)) {
			dx -= SPEED;
		}
		if (kba.isPressed(KeyboardAdapter.KEY_NAME.RIGHT)) {
			dx += SPEED;
		}
		
		x += dx;
		y += dy;
		
		if (x < 0 || y < 0 || x > 1000 || y > 600) {
			location = SPRITE_LOCATION.EXITED;
		}
	}
	
	/**
	 * #M6
	 * Getter method for a Sprite's X position.
	 * 
	 * @return the x position of the sprite.
	 */	
	
	public int getScreenX() {
		return (int)(x * WORLD_PIXEL_RATIO);
	}
	
	/**
	 * #M6
	 * Getter method for a Sprite's Y position.
	 * 
	 * @return the Y position of the sprite.
	 */	
	public int getScreenY() {
		return (int)(y * WORLD_PIXEL_RATIO) ;
	}
	
	/**
	 * #M7
	 * Getter method for a Sprite's player.
	 * 
	 * @return the Sprite's player
	 */	
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * #M7
	 * Getter method for a Sprite's image
	 * 
	 * @return the Sprite's image
	 */	
	public Image getImage() {
		return image;
	}
	
	public SPRITE_LOCATION getLocation() {
		return location;
	}
	
	public void resetPosition() {
		x = 500;
		y = 500;
		location = SPRITE_LOCATION.TOWN;
	}
}
