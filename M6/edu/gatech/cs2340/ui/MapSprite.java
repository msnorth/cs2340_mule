package edu.gatech.cs2340.ui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.io.InputReceiver;
import edu.gatech.cs2340.io.KeyboardAdapter;
import edu.gatech.cs2340.sequencing.WaitedOn;


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
	
	public MapSprite(Player player) {
		this.player = player;
		resetPosition();
		ImageIcon ii = new ImageIcon(this.getClass().getResource("human_blue.png"));

        image = ii.getImage();
        int what = ii.getIconHeight();
        h = (int)(ii.getIconHeight()/WORLD_PIXEL_RATIO);
        w = (int)(ii.getIconWidth()/WORLD_PIXEL_RATIO);
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

		x = bindValue(x, 0, 9000 - w);
		y = bindValue(y, 0, 5000 - h);
		
		if (x >= 4000 && x <= 5000 && y >= 2000 && y <= 3000) {
			enteredTown = true;
		}
	}
	
	private int bindValue(int val, int low, int high) {
		if (val < low) {
			val = low;
		}
		else if (val > high) {
			val = high;
		}
		return val;
	}
	
	public Image getImage() {
		return image;
	}
	
	public int getScreenX() {
		return (int)(x * WORLD_PIXEL_RATIO);
	}
	
	public int getScreenY() {
		return (int)(y * WORLD_PIXEL_RATIO);
	}

	public boolean hasEnteredTown() {
		return enteredTown;
	}
	
	public void resetPosition() {
		x = 4500;
		y = 3500;
		enteredTown = false;
	}
}
