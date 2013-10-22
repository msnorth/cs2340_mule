package edu.gatech.cs2340.ui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.io.InputReceiver;
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
	
	private final int SPEED = 5;
	
	private int x;
	private int y;
	private Player player;
	private Image image;
	
	public MapSprite(Player player) {
		x = MainGameWindow.DIM_X/2;
		y = MainGameWindow.DIM_Y/4;
		this.player = player;
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
		x = bindValue(x, 0, dim.width);
		y = bindValue(y, 0, dim.height * 2/3);
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
		return x;
	}
	
	public int getScreenY() {
		return y;
	}
}
