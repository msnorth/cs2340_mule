//package edu.gatech.cs2340.ui;
//import java.awt.Graphics;
//import java.awt.Color;
//import java.awt.Dimension;
//
//import javax.swing.BorderFactory;
//import javax.swing.JLabel;
//import javax.swing.border.Border;
//
//import edu.gatech.cs2340.data.Player;
//import edu.gatech.cs2340.data.Tile;
//
///**
// * 
// * @author Stephen Conway
// * 		Function group:		View: Background
// * 		Created for:		M6		10/7/13
// * 		Assigned to:		Dan
// * 		Modifications:								
// * 
// * 
// * 
// * 		Purpose: GUI representation of Tile.				 
// */
//public class TileRenderer extends GUIComponent{
//	private static final long serialVersionUID = 1L;
//
////	//Tile and TileRenderer are paired as Model and View of the Tile concept
////	private Tile tile;
////	
////	private boolean active;
////	
////	/**
////	 * #M6
////	 * Method to determine if tile is highlighted as active
////	 * 
////	 * @return
////	 */
////	public boolean isActive() {
////		return active;
////	}
//	
////	public void setTile(Tile tile) {
////		this.tile = tile;
////		this.add(new JLabel(tile.getName()));
////		
////		
////		Dimension dim = new Dimension(MainGameWindow.DIM_X/9, MainGameWindow.DIM_X/9);
////		this.setMaximumSize(dim);
////		this.setPreferredSize(dim);
////		this.setMinimumSize(dim);
////	}
//
//
////	public void refresh() {
////		drawTile();
////		tile.dirty = false;
////	}
//	
//	/**
//	 * #M6 Method to draw tile. If an unowned tile is active,
//	 * its border should be black If an unowned tile is inactive, its border
//	 * should be "clear" If an owned tile is active, its border should be dashed
//	 * with the Player color If an owned tile is inactive, its border should be
//	 * solid with the Player color
//	 */
//	private void drawTile() {
//		Color color;
//		Player p = tile.getOwner();
//		int thickness = 1;
//		Border border = null;
//		if (tile.isActive() && p == null) {
//			// thick black border
//			thickness = 5;
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
//
//		this.setBorder(border);
//	}
//		
//}
