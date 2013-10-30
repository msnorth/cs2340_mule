package edu.gatech.cs2340.ui;

import java.awt.Image;

import javax.swing.ImageIcon;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.SpriteImageLoader;
import edu.gatech.cs2340.io.KeyboardAdapter;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		View: Graphic
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Shreyyas, Stephen
 * 		Modifications:		M6 		10/20/13 Shreyyas, Tommy
 * 									Added basic functionality for receiving input
 * 							M7		10/22/13 Stephen
 * 									Burned class to the ground. Starting from scratch.
 * 
 * 
 * 		Purpose: Graphic that moves around on top of the map
 */
public class  MapSprite {
	
	private final int SPEED = 50;
	private final double WORLD_PIXEL_RATIO = 75.0/1000; //pixels / worldUnit (1000 world units = 1 Tile)
	
	private int x;
	private int y;
	private int h;
	private int w;
	private Player player;
	private Image image;
	private boolean enteredTown;
	
	/**
	 * #M6
	 * Main constructor. Gets icon based on Player race and color.
	 * 
	 * @param player
	 */
	public MapSprite(Player player) {
		this.player = player;
		resetPosition();
		
		SpriteImageLoader loader = new SpriteImageLoader();
		ImageIcon ii = loader.getImage(player);

        image = ii.getImage();
        h = (int)(ii.getIconHeight()/WORLD_PIXEL_RATIO);
        w = (int)(ii.getIconWidth()/WORLD_PIXEL_RATIO);
	}
	
	/**
	 * #M6
	 * 
	 * Method to update sprite position based on key status. Should be called with a delay.
	 */
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

		x = bindValue(x, 0, 9000 - w);
		y = bindValue(y, 0, 5000 - h);
		
		int xc = x - w/2;
		int yc = y + h/2;
		
		if (xc >= 4000 && xc <= 5000 && yc >= 2000 && yc <= 3000) {
			enteredTown = true;
		}
	}
	
	/**
	 * Helper method to restrict range of an int
	 * 
	 * @param val int to check
	 * @param low lower bound
	 * @param high upper bound
	 * @return bound value
	 */
	private int bindValue(int val, int low, int high) {
		if (val < low) {
			val = low;
		}
		else if (val > high) {
			val = high;
		}
		return val;
	}
	
	/**
	 * #M6
	 * Method to get sprite's image. Used by MapRenderer to draw the sprite.
	 * 
	 * @return
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * #M6
	 * Method to return sprite's X position on the screen. 
	 * 
	 * @return
	 */
	public int getScreenX() {
		return (int)(x * WORLD_PIXEL_RATIO);
	}
	
	/**
	 * #M6
	 * Method to return sprite's Y position on the screen. 
	 * 
	 * @return
	 */
	public int getScreenY() {
		return (int)(y * WORLD_PIXEL_RATIO );
	}

	/**
	 * #M6
	 * Method to check if sprite is on town tile.
	 * @return
	 */
	public boolean hasEnteredTown() {
		return enteredTown;
	}
	
	/**
	 * #M6
	 * Method to reset sprite to default position.
	 */
	public void resetPosition() {
		x = 4500;
		y = 3500;
		enteredTown = false;
	}
}
