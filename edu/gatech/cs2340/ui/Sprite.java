package edu.gatech.cs2340.ui;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.Tile;
import edu.gatech.cs2340.io.KeyboardAdapter;
import edu.gatech.cs2340.io.SpriteImageLoader;
/**
 * The Sprite Logic for movement and the location
 * @author ShreyyasV
 *
 */
public class Sprite {	
	private int x;
	private int y;
	private final int w;
	private final int h;
	private int speed;
	private final Image image;
	private int location;
	private final Player player;
	private final Map map;
	/**
	 * Main constructor for Sprite class
	 * @param player
	 * @param map
	 */
	public Sprite(Player player, Map map) {
		this.player = player;
		this.map = map;
		SpriteImageLoader loader = new SpriteImageLoader();
		ImageIcon icon = loader.getImage(player);
		w = icon.getIconWidth();
		h = icon.getIconHeight();
		image = icon.getImage();
		x = 4250;
		y = 3500;
		location = 2;
	}
	/**
	 * Updates the movement of the sprite based on key presses
	 */
	public void update() {
		KeyboardAdapter kba = KeyboardAdapter.getInstance();
		int dx = 0;
		int dy = 0;
		if (kba.isPressed(KeyboardAdapter.KEY_NAME.UP)) {
			dy -= speed;
		}
		if (kba.isPressed(KeyboardAdapter.KEY_NAME.DOWN)) {
			dy += speed;
		}
		if (kba.isPressed(KeyboardAdapter.KEY_NAME.LEFT)) {
			dx -= speed;
		}
		if (kba.isPressed(KeyboardAdapter.KEY_NAME.RIGHT)) {
			dx += speed;
		}
		
		if (kba.isPressed(KeyboardAdapter.KEY_NAME.CONFIRM) && 
				player.hasMule() && location == 2) { 
			int r = y/1000; 
			int c = x/1000;
			Tile tile = map.getTileAt(r, c);
			//if the player tries to place the MULE on an empty tile they own, it succeeds
			if (tile.getOwner() == player && !tile.hasMule()) {
				tile.setMule(player.getMule());
				player.removeMule();
			}
			else {
				player.removeMule();
			}
		}
		
		x += dx;
		y += dy;
		
		correctSpritePosition();
		location = calculateLocation();
	}
	/**
	 * Corrects the sprite position based on the locations it's in:
	 * 	1) Pub
	 *  2) Store 
	 *  3) Town
	 *  4) Map
	 */
	private void correctSpritePosition() {
		//bind to map
		if (x < 0) {
			x = 0;
		}
		else if (x > 9000) {
			x = 9000;
		}
		if (y < 0) {
			y = 0;
		}
		else if (y > 5000) {
			y = 5000;
		}
		
		//if entering town
		if (location == 2 && calculateLocation() != 2) {
			if (y > 2000 + speed || y < 3000 + speed) {
				x = 4500;
			}
			else {
				y = 2500;
			}
		}
	}
	/**
	 * Calculates the location of the sprite based on the location it's in
	 * @return
	 */
	private int calculateLocation() {
		int result;
		if (x > 4000 && x < 4300 && y > 2700 && y < 3000 ) {
			result = -1; // pub
		}
		else if (x > 4700 && x < 5000 && y > 2700 && y < 3000) {
			result = 0; // store
		}
		else if (x > 4000 && x < 5000 && y > 2000 && y < 3000) {
			result = 1; // town
		}
		else {
			result = 2; // map
		}
		return result;
	}
	/**
	 * Sets the position of the sprite
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Gets the image
	 * @return
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * Gets the location
	 * @return
	 */
	public int getLocation() {
		return location;
	}
	/**
	 * Gets the x coordinate of the sprite
	 * @return
	 */
	public int getXCoord() {
		return x;
	}
	/**
	 * Gets the y coordinate of the sprite
	 * @return
	 */
	public int getYCoord() {
		return y;
	}
	/**
	 * Gets the Screen's Coordinates
	 * @return
	 */
	public Point getScreenCoords() {
		Point result;
		if (location != 2) {
			speed = 10;
			result = new Point((int)((x-4000)*(375.0/1000.0)+75+w/2), (int)(((y-2000)*(375.0/1000.0)-h/2)));
		}
		else {
			speed = 50;
			result = new Point((int)(x*(75.0/1000.0)+w/2), (int)(y*(75.0/1000.0)-h/2));
		}
		return result;
	}
}
