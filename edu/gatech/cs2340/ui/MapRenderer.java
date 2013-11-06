package edu.gatech.cs2340.ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout; 
import java.util.ArrayList;
import java.util.HashMap;

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
 * 		Purpose: Provide a graphical representation of the Map
 */
public class MapRenderer extends JPanel{
	private static final long serialVersionUID = 1L;
	private Map map;
	private MapSprite sprite;
	private HashMap<Integer, JLabel> tileLabels;
	private final int TILE_WIDTH = 75;
	private final int TILE_HEIGHT = 75;
	private boolean initialized = false;
	private final int TOWN_INDEX = 22;
	/**
	 * Whether or not prices should be displayed
	 */
	private boolean displayPrices = false;
	private boolean displayMule   = true;

	
	/**
	 * #M6
	 * Constructor. Connects renderer with data.
	 * @param map
	 */
	public MapRenderer(Map map) {
		setLayout(new GridLayout(5,9,0,0));
		this.map = map;
		sprite = null;		
		initialize();
		
	}
	
	/**
	 * #M6
	 * Constructor. Connects renderer with data and adds a sprite to render.
	 * @param map
	 * @param sprite
	 */
	public MapRenderer(Map map, MapSprite sprite) {
		this(map);
		this.sprite = sprite;
		initialize();
	}
	
	/**
	 * Let the tiles populate for the first time
	 */
	public void initialize(){
		if (!initialized){
			tileLabels = new HashMap<Integer, JLabel>(); // initialize hash map
			for (int i = 0; i < map.getNumTiles(); i++){
				refresh(i, true, true);
			}
			this.revalidate();
		}
		initialized = true;
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
	}
	
	/**
	 * #M6
	 * Refresh method to be called on a specific Tile number.
	 * Used with LandGranter to update border colors.
	 * Only refreshes tiles that have had data changed.
	 * 
	 * @param ndx Index at which to put refresh tile left to right, then down
	 * @param waitToPaint Whether the method should hold off on painting after
	 * a single refresh
	 * @param firstTime If this is the first time, don't remove the former tile 
	 */
	public void refresh(int ndx, boolean waitToPaint, boolean firstTime) {
		Tile curTile = map.getTileNumber(ndx);
		// if (curTile.dirty) { 
			JLabel label = TileImageFactory.getTileLabelImage(curTile);
			JLabel tileLabel = tileLabels.get(ndx);
			// There's nothing to remove if this is the first time--> update image
			if (!firstTime){
				tileLabel.setIcon(label.getIcon());
				tileLabel.setBorder(label.getBorder());
				// TODO Set borders?
			} else { // add the label for the first time
				add(label, ndx);
				tileLabels.put(ndx, label); // store these so we can access the labels later
				tileLabel = label;
			}
			if (curTile.isDirty()){
				// repaint the tile and its price
				//	tileLabel.invalidate();
				tileLabel.repaint();
				//repaint();
				revalidate();
				//repaint();
			}
			if (!waitToPaint){ // Paint if we're not doing a slew in a row
				repaint();
			}
		//}
	}

	
	public void refreshSprite() {
		if (sprite == null) {
			throw new RuntimeException("Cannot refresh sprite on spriteless map renderer!");
		}
		sprite.update();
		repaint();
	}
	
	/**
	 * #M6
	 * Method that draws the Sprite if one is currently on the map.
	 */
	@Override
	public void paint(Graphics g) {	// TODO change to paintComponent?
        super.paint(g);
        if (displayPrices){
        	for (int i = 0; i < map.getNumTiles(); i++){
            	Tile tile = map.getTileNumber(i);
            	if (tile.getPrice() != 0){ // only display a non-zero price
            		int x = getXCoord(i);
	            	int y = getYCoord(i);
	            	// start drawing the string in the middle left of the tile
	            	g.setColor(Color.RED);
	            	g.setFont(new Font("Serif", Font.PLAIN, 30));
	            	g.drawString("$" + tile.getPrice(), x*TILE_WIDTH + (int) TILE_WIDTH/4, y*TILE_HEIGHT + (int) TILE_HEIGHT*2/3);
        		}
            }
        }
        if (displayMule) {
        	for (int i = 0; i < map.getNumTiles(); i++){
            	Tile tile = map.getTileNumber(i);
            	if(tile.hasMule()) {
            		int x = getXCoord(i);
                	int y = getYCoord(i);
	    			g.setColor(Color.BLACK);
	    			g.draw3DRect(x+(TILE_WIDTH/2), y+(TILE_HEIGHT/3), (int) TILE_WIDTH/8, 
    					TILE_HEIGHT/8, true);
            	}
        	}
        }
        if (sprite != null) {
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.drawImage(sprite.getImage(), sprite.getScreenX(), sprite.getScreenY(), this);
        }
        g.dispose();
        
    }
	
	/**
	 * Get the x coordinate for an index
	 * @param index Index into a one-dimensional array
	 * @return Equivalent x coordinate for the two-dimensional array
	 */
	private int getXCoord(int index){
		return index % map.getNumCols();
	}
	
	/**
	 * Get the y coordinate for an index
	 * @param index Index into a one-dimensional array
	 * @return Equivalent y coordinate for the two-dimensional array
	 */
	private int getYCoord(int index){
		return (int) index / map.getNumCols();
	}

	public void setDisplayPrices(boolean displayPrices) {
		this.displayPrices = displayPrices;
		if (!displayPrices){ // if we're clearing the display prices,
			for (int i = 0; i < map.getNumTiles(); i++){
				map.getTileNumber(i).setPrice(0); // clear all tile prices so they won't display again
			}
		}
		refresh();
	}
}
