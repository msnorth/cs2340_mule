package edu.gatech.cs2340.ui;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout; 

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.sun.media.sound.Toolkit;

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
	private Map map;
	private MapSprite sprite;
	private Timer timer;
	
	/**
	 * #M6
	 * Main constructor. Connects renderer with data (map) and with callback line (manager).
	 * @param map
	 */
	public MapRenderer(Map map) {
		setLayout(new GridLayout(5,9));
		this.map = map;
		sprite = null;
		initialize();
	}
	
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
	 * Refresh all tiles
	 */
	public void refresh() {
		for (int i = 0; i < map.getNumTiles(); i++){
			refresh(i, true, false);
		}
		if (sprite != null) {
			sprite.update();
			int x = sprite.getScreenX();
			int y = sprite.getScreenY();
		}
		this.revalidate();
		this.repaint();
	}
//	 * 
//	 */
//	public void refresh() {
//		removeAll();
//		
//		setLayout(new GridLayout(5,9));
//		TileRenderer tileRenderer;
//		Tile tile = map.getNextTile();
//		do {
//			tileRenderer = tile.getRenderer();
//			add(tileRenderer);
//			tileRenderer.refresh();
//			tile = map.getNextTile();
//			
//		} while (tile != null);
//		
//		revalidate();
//	}
	
	/**
	 * #M6
	 * Refresh method to be called on a Tile at a specific world coordinate.
	 * Used with Sprite's position to prevent streaking without refreshing all of the tiles.
	 * 
	 * @param x
	 * @param y
	 */
	public void refresh(double x, double y) {
		int ndx = (int)(x*y + x); // TODO should this be "+y"?
		refresh(ndx, false, false);
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
		JLabel label = TileImageFactory.getTileLabelImage(curTile);
		// There's nothing to remove if this is the first time
		if (!firstTime){
			this.remove(ndx);
		}
		this.add(label, ndx);
		if (!waitToPaint){ // Paint if we're not doing a sleu in a row
			this.revalidate();
			this.repaint();
		}
	}

	public void paint(Graphics g) {
        super.paint(g);
        if (sprite != null) {
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.drawImage(sprite.getImage(), sprite.getScreenX(), sprite.getScreenY(), this);
        }
        g.dispose();
        
    }
}
