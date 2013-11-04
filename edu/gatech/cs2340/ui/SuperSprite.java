package edu.gatech.cs2340.ui;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.SpriteImageLoader;
import edu.gatech.cs2340.data.Tile;
import edu.gatech.cs2340.io.InputReceiver;
import edu.gatech.cs2340.io.KeyboardAdapter;

public class SuperSprite {
	private int x;
	private int y;
	private int w;
	private int h;
	private int speed;
	private Image image;
	private int location;
	private Player player;
	private Map map;
	
	public SuperSprite(Player player, Map map) {
		this.player = player;
		this.map = map;
		SpriteImageLoader loader = new SpriteImageLoader();
		ImageIcon icon = loader.getImage(player);
		w = icon.getIconWidth();
		h = icon.getIconHeight();
		image = icon.getImage();
		location = 2;
	}
	
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
				player.hazMule() && location == 2) { 
			Tile tile = map.getTileAt(x/9000 - 1, y/5000 - 1);
			System.out.println(tile);
			//if the player tries to place the MULE on an empty tile they own, it succeeds
			if (tile.getOwner() == player && !tile.hasMule()) {
				tile.setMule(player.getMule());
				player.removeMule();
				System.out.println("MULE placement success.");
			}
			else {
				System.out.println("MULE placement failed.");
			}
		}
		
		x += dx;
		y += dy;
		
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
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Image getImage() {
		return image;
	}
	
	public int getLocation() {
		return location;
	}
	
	public int getXCoord() {
		return x;
	}
	
	public int getYCoord() {
		return y;
	}
	
	public Point getScreenCoords() {
		Point result = new Point((int)((x-4000)*(375.0/1000.0)), (int)(((y-2000)*(375.0/1000.0))));
		if (x > 4200 && x < 4500 && y > 2650 && y < 3000 ) {
			location = -1; // pub
			System.out.println("x: " + getXCoord() + " , y: " +getYCoord());
		}
		//else if (x > 4950 && x < 5220 && y > 2650 && y < 3000) {
		else if (x > 4700 && x < 5000 && y > 2650 && y < 3000) {
			location = 0; // store
		}
		else if (x > 4000 && x < 5000 && y > 2000 && y < 3000) {
			speed = 10;
			location = 1; // town
		}
		else {
			result = new Point((int)(x*(75.0/1000.0)), (int)(y*(75.0/1000.0)));
			speed = 50;
			location = 2; // map
		}
		return result;
	}
}
