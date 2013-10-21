package edu.gatech.cs2340.data;

import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

<<<<<<< HEAD
import edu.gatech.cs2340.ui.TileImageFactory;
=======
import edu.gatech.cs2340.ui.TileRenderer;
>>>>>>> 2393cea41c2d70311bb028f29056d3dcdb4e35db

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
	// Tile and TileRenderer are paired as Model and View of the Tile concept
<<<<<<< HEAD
	private Player owner;
	private String id;
	private boolean isActive;
	private String name;
=======

	protected TileRenderer renderer;
	protected Player owner;
	protected String id;
	private boolean isActive;
	protected String name;
>>>>>>> 2393cea41c2d70311bb028f29056d3dcdb4e35db
	protected static ImageIcon hillImage;
	protected static ImageIcon mountainImage;
	protected static ImageIcon peakImage;
	protected static ImageIcon plainImage;
	protected static ImageIcon riverImage;
<<<<<<< HEAD
	protected static ImageIcon townImage;
	protected ImageIcon image; // image for the current instance of Tile
	static Logger logger;
=======
	protected ImageIcon image; // image for the current instance of Tile
	static Logger logger;
	
	// TODO private ImageIcon Town;
>>>>>>> 2393cea41c2d70311bb028f29056d3dcdb4e35db

	public Tile(String name) {
		this.name = name;
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
		// TODO update tileRenderer
	}
<<<<<<< HEAD
=======
	
	public TileRenderer getRenderer() {
		return renderer;
	}

>>>>>>> 2393cea41c2d70311bb028f29056d3dcdb4e35db

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
		// TODO find a way to set the activity of tiles
	}

	public String getName() {
		return name;
	}
	
	public static void initialize(){
		// for error logging
		logger = Logger.getGlobal();
		try {
			hillImage = new ImageIcon("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/hill.png");
			mountainImage = new ImageIcon("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/mountain.png");
			peakImage = new ImageIcon("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/peak.png");
			riverImage = new ImageIcon("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/river.png");
			plainImage = new ImageIcon("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/plain.png");
<<<<<<< HEAD
			townImage = new ImageIcon("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/town.png");
=======
>>>>>>> 2393cea41c2d70311bb028f29056d3dcdb4e35db
		} catch (Exception e){
			logger.log(Level.WARNING,"Couldn't load all images in TileRenderer");
		}
	}
	
	public ImageIcon getImageIcon(){
		return image;
	}

}
