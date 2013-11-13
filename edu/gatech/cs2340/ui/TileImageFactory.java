package edu.gatech.cs2340.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.Tile;
import edu.gatech.cs2340.io.TileImageLoader;

/**
 * 
 * @author Stephen Conway Function group: View: Background Created for: M6
 *         10/7/13 Assigned to: Dan Modifications:
 * 
 * 
 * 
 *         Purpose: GUI representation of Tile.
 */
public class TileImageFactory {

	static int numUses = 0;
	// // Tile and TileRenderer are paired as Model and View of the Tile concept
	// private Tile tile;

	/**
	 * Factory create method for turning a Tile into a JLabel to be added to the GridLayout
	 * @param tile Tile to be represented on the GridLayout
	 * @return Label to add to GridLayout
	 */
	public static JLabel getTileLabelImage(Tile tile){
		TileImageLoader loader = new TileImageLoader();
		ImageIcon icon = loader.getImage(tile);
		if (tile.hasMule()) {
			icon = drawDeployedMule(tile, icon);
		}

		JLabel label = new JLabel(icon, JLabel.CENTER);
		Color color = Color.BLACK;
		Border border;

		//create the border
		if (tile.isActive()) {
			if (tile.isOwned()) {
				Player owner = tile.getOwner();
				color = owner.getPlayerColor();
				border = BorderFactory.createDashedBorder(color);
			}
			else {
				border = BorderFactory.createLineBorder(color, 2);
			}
		}
		else {
			if (tile.isOwned()) {
				Player owner = tile.getOwner();
				color = owner.getPlayerColor();
				border = BorderFactory.createLineBorder(color, 2);	
			}
			else {
				border = BorderFactory.createLineBorder(color, 1);
			}	
		}
		
		//add the border to the label and return
		label.setBorder(border);
		return label;
	}
	
	/**
	 * Method to draw a square to indicate MULE deployment on a tile
	 * 
	 * @param tile
	 * @param icon
	 * @return
	 */
	private static ImageIcon drawDeployedMule(Tile tile, ImageIcon icon) {
		final int RECT_SIZE = 20;
		ImageIcon result = null;
		
		//----------------------
		//http://stackoverflow.com/questions/15053214/converting-an-imageicon-to-a-bufferedimage 
			BufferedImage buffer = new BufferedImage(
				    icon.getIconWidth(),
				    icon.getIconHeight(),
				    BufferedImage.TYPE_INT_RGB);
			Graphics g = buffer.createGraphics();
			// paint the Icon to the BufferedImage.
			icon.paintIcon(null, g, 0,0);
			
		//-----------------------
		Player player = tile.getOwner();
		Color color = player.getPlayerColor();
		g.setColor(color);
		int x = icon.getIconWidth()/2 - RECT_SIZE/2;
		int y = icon.getIconHeight()/2 - RECT_SIZE/2;
		g.drawRect(x, y, RECT_SIZE, RECT_SIZE);
		g.dispose();
		
		result = new ImageIcon(buffer);
		return result;
	}

}
