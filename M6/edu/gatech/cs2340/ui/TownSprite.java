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
	private final int SPEED = 5;
	
	private int x;
	private int y;
	private static final  int DX = 5;
	private static final  int DY = 5;
	private Player player;
	private TownRenderer townRenderer;
	private  Image image;
	private boolean isInTown;
	private boolean isInPub;
	
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
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource("human_blue.png"));
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
		Dimension dim = MainGameWindow.getInstance().getPreferredSize();
		
		//when x or y < 0, or when x > width of main game window, or y> height of current panel
		if ((x < 0 || y < 0) || x > dim.width || y > dim.height) {
			isInTown = false;
		}
	}
	
	/**
	 * #M6
	 * Getter method for a Sprite's X position.
	 * 
	 * @return the x position of the sprite.
	 */	
	
	public int getScreenX() {
		return this.x;
	}
	
	/**
	 * #M6
	 * Getter method for a Sprite's Y position.
	 * 
	 * @return the Y position of the sprite.
	 */	
	public int getScreenY() {
		return this.y;
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
	
	/**
	 * #M7
	 *
	 * 
	 * @return whether the sprite has left town
	 */	
	public boolean hasLeftTown() {
		return !isInTown;
	}
	
	public boolean hasEnteredPub() {
		return isInPub;
	}
	
	public void resetPosition() {
		//x = MainGameWindow.DIM_X/2;
		//y = MainGameWindow.DIM_Y/4;
		x = 100;
		y = 100;
		isInPub = false;
		isInTown = true;
	}
}
