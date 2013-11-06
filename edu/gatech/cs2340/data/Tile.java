package edu.gatech.cs2340.data;

import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import edu.gatech.cs2340.ui.TileImageFactory;
import edu.gatech.cs2340.ui.MapRenderer;

/**
 * 
 * @author Stephen Conway Function group: Model: Data holder Created for: M6
 *         10/7/13 Assigned to: Maddy Modifications:
 * 
 * 
 * 
 *         Purpose: Parent class for all Tile objects that make up a grid on the
 *         game's map.
 */
public abstract class Tile {
	//Static
	public static Logger logger;
	
	protected static ImageIcon hillImage;
	protected static ImageIcon mountainImage;
	protected static ImageIcon peakImage;
	protected static ImageIcon plainImage;
	protected static ImageIcon riverImage;
	protected static ImageIcon townImage;
	
	
	//Instance
	private Player owner;
	private String id;
	private boolean isActive;
	private String name;
	private int price;
	private Mule mule;
	
	protected ImageIcon image; // image for the current instance of Tile
	protected boolean dirty;

	/**
	 * Create a tile of type name
	 * @param name
	 */
	public Tile(String name) {
		this.name = name;
		dirty = true;
		// TODO throw error if not of correct type?
	}

	/**
	 * #FUTURE Method to get the production of a Tile. Based upon Tile type,
	 * MULE present, and random events.
	 * 
	 * @return
	 */
	public abstract ResourceAmount calculateProduction();

	/**
	 * #M6 Method to set owner of tile. Should handle "null" as returning Tile
	 * to neutral (through sale) Should call something to update its
	 * TileRenderer to reflect the change
	 * 
	 * @param newOwner
	 */
	public void setOwner(Player newOwner) {
		this.owner = newOwner;
		dirty = true;
	}

	/**
	 * #M6 Method to get owner of tile. Should return null if tile is unowned
	 * 
	 * @return
	 */
	public Player getOwner() {
		return this.owner;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isDirty() {
		return dirty;
	}
	
	public void cleaned() {
		dirty = false;
	}

	// returns true if equal, false otherwise
	public boolean compareTo(Tile t) {
		return (id.equals(t.getId()));
	}

	//get whether or not is active
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
		dirty = true;
	}

	public String getName() {
		return name;
	}
	
	public Mule getMule() {
		return mule;
	}
	
	public void setMule(Mule mule) {
		if (this.mule != null) {
			throw new RuntimeException("Cannot place mule where there already is one.");
		}
		this.mule = mule;
		dirty = true;
	}
	
	public boolean hasMule() {
		return (mule != null);
	}
	
	public static void initialize(){
		// for error logging
		logger = Logger.getGlobal();
		try {
			hillImage = new ImageIcon(Tile.class.getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/hill.png"));
			mountainImage = new ImageIcon(Tile.class.getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/mountain.png"));
			peakImage = new ImageIcon(Tile.class.getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/peak.png"));
			riverImage = new ImageIcon(Tile.class.getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/river.png"));
			plainImage = new ImageIcon(Tile.class.getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/plain.png"));
			townImage = new ImageIcon(Tile.class.getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/town.png"));
		} catch (Exception e){
			logger.log(Level.WARNING,"Couldn't load all images in TileRenderer");
		}
	}
	
	public ImageIcon getImageIcon(){
		return image;
	}

	/**
	 * Set the price of the tile and draw a string with that price
	 * @param price Price of the tile
	 * @return Whether the setting was successful (because the tile is unowned)
	 */
	public void setPrice(int price) {
		this.price = price; 
		dirty = true;
	}
	
	/**
	 * Get the price of the tile
	 * @return Price of the tile
	 */
	public int getPrice(){
		return price;
	}

}
