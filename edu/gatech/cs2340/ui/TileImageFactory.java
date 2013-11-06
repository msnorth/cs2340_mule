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
		ImageIcon icon;
		if (tile.hasMule()) {
			icon = drawDeployedMule(tile);
		}
		else {
			icon = tile.getImageIcon();
		}

		JLabel label = new JLabel(icon, JLabel.CENTER);
		Color color;
		Player p = tile.getOwner();
		int thickness = 1;
		Border border = null;
		numUses++;
		if (tile.isDirty() && numUses > 45){
			thickness = 1;
		}
		if (tile.isActive() && p == null) {
			// thick black border
			thickness = 2;
			color = Color.black;
			border = BorderFactory.createLineBorder(color, thickness);
		} else if (!tile.isActive() && p == null) {
			// thin black border
			// TODO just for now, remove when have icons
			color = Color.black;
			border = BorderFactory.createLineBorder(color, thickness);
		} else if (tile.isActive() && p != null) {
			// dashed border
			color = p.getPlayerColor();
			border = BorderFactory.createDashedBorder(color);
		} else if (!tile.isActive() && p != null) {
			// solid border
			thickness = 2;
			color = p.getPlayerColor();
			border = BorderFactory.createLineBorder(color, thickness);
		}
		label.setBorder(border);
		return label;
	}
	
	private static ImageIcon drawDeployedMule(Tile tile) {
		final int RECT_SIZE = 20;
		ImageIcon result = null;
		ImageIcon icon = tile.getImageIcon();
		
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
