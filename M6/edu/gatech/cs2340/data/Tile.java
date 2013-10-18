package edu.gatech.cs2340.data;

import java.util.logging.Logger;

import javax.swing.ImageIcon;

import edu.gatech.cs2340.ui.TileRenderer;

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
	private TileRenderer renderer;
	private Player owner;
	private String id;
	private boolean isActive;
	private String name;
	private static ImageIcon hill;
	private static ImageIcon mountain;
	private static ImageIcon peak;
	private static ImageIcon plains;
	private static ImageIcon river;
	
	// TODO private ImageIcon Town;

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

	public TileRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(TileRenderer renderer) {
		this.renderer = renderer;
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
	}

	public String getName() {
		return name;
	}
	
	public void Initialize(){
		// for error logging
		logger = Logger.getGlobal();
		try {
			hill = new ImageIcon(this.getClass().getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/hill.png")).getImage();
			mountain = new ImageIcon(this.getClass().getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/mountain.png")).getImage();
			peak = new ImageIcon(this.getClass().getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/peak.png")).getImage();
			river = new ImageIcon(this.getClass().getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/river.png")).getImage();
			plain = new ImageIcon(this.getClass().getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/plain.png")).getImage();
		} catch (Exception e){
			logger.log(Level.WARNING,"Couldn't load all images in TileRenderer");
		}
	}
}
