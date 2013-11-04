package edu.gatech.cs2340.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
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
		JLabel label = new JLabel(tile.getImageIcon(), JLabel.CENTER);
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
	
//	/**
//	 * #M6 Method to draw tile. If an unowned tile is active,
//	 * its border should be black If an unowned tile is inactive, its border
//	 * should be "clear" If an owned tile is active, its border should be dashed
//	 * with the Player color If an owned tile is inactive, its border should be
//	 * solid with the Player color
//	 */
//	private void drawTile(Tile tile) {
//		Color color;
//		Player p = tile.getOwner();
//		int thickness = 1;
//		Border border = null;
//		if (tile.isActive() && p == null) {
//			// thick black border
//			thickness = 2;
//			color = Color.black;
//			border = BorderFactory.createLineBorder(color, thickness);
//		} else if (!tile.isActive() && p == null) {
//			// thin black border
//			// TODO just for now, remove when have icons
//			color = Color.black;
//			border = BorderFactory.createLineBorder(color, thickness);
//		} else if (tile.isActive() && p != null) {
//			// dashed border
//			color = p.getPlayerColor();
//			border = BorderFactory.createDashedBorder(color);
//		} else if (!tile.isActive() && p != null) {
//			// solid border
//			thickness = 2;
//			color = p.getPlayerColor();
//			border = BorderFactory.createLineBorder(color, thickness);
//		}
//		this.add(new JLabel(tile.getName()));
//
//		this.setBorder(border);
//	}

}
