package edu.gatech.cs2340.ui;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout; 

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Tile;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		View: Background
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Dan
 * 		Modifications:		M6		10/15/2013 Thomas Mark
 * 									Removed GUIManager references.								
 * 
 * 
 * 
 * 		Purpose: Provide a graphical representation of the Map
 */
public class MapRenderer extends JPanel{
	private static final long serialVersionUID = 1L;
	private Map map;
	private MapSprite sprite;

	
	/**
	 * #M6
	 * Main constructor. Connects renderer with data (map) and with callback line (manager).
	 * @param map
	 */
	public MapRenderer(Map map) {
		setLayout(new GridLayout(5,9,0,0));
		this.map = map;
		sprite = null;
		initialize();
	}
	
	/**
	 * 
	 * @param map
	 * @param sprite
	 */
	public MapRenderer(Map map, MapSprite sprite) {
		this(map);
		this.sprite = sprite;
	}
	
	/**
	 * Let the tiles populate for the first time
	 */
	public void initialize(){
		for (int i = 0; i < map.getNumTiles(); i++){
			refresh(i, true, true);
		}
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Attempt to refresh all tiles and repaint afterwards.
	 */
	public void refresh() {
		for (int i = 0; i < map.getNumTiles(); i++){
			refresh(i, true, false);
		}
		if (sprite != null) {
			sprite.update();
		}
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * #M6
	 * Refresh method to be called on a specific Tile number.
	 * Used with LandGranter to update border colors.
	 * 
	 * @param ndx Index at which to put refresh tile left to right, then down
	 * @param waitToPaint Whether the method should hold off on painting after
	 * a single refresh
	 * @param firstTime If this is the first time, don't remove the former tile 
	 */
	public void refresh(int ndx, boolean waitToPaint, boolean firstTime) {
		Tile curTile = map.getTileNumber(ndx);
		if (curTile.dirty) { 
			JLabel label = TileImageFactory.getTileLabelImage(curTile);
			// There's nothing to remove if this is the first time
			if (!firstTime){
				this.remove(ndx);
			}
			this.add(label, ndx);
			if (!waitToPaint){ // Paint if we're not doing a slew in a row
				this.revalidate();
				this.repaint();
			}
		}
	}

	/**
	 * #M6
	 * Method that draws the Sprite if one is currently on the map.
	 */
	public void paint(Graphics g) {
        super.paint(g);
        if (sprite != null) {
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.drawImage(sprite.getImage(), sprite.getScreenX(), sprite.getScreenY(), this);
        }
        g.dispose();
        
    }
}
