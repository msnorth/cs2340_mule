package edu.gatech.cs2340.data;

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
public  class Tile {
	// Tile and TileRenderer are paired as Model and View of the Tile concept
	private TileRenderer renderer;
	private Player owner;
	private String id;

	public Tile(String id, Player owner, TileRenderer renderer) {
		this.renderer = renderer;
		this.owner = owner;
		this.id = id;
	}

	/**
	 * #FUTURE Method to get the production of a Tile. Based upon Tile type,
	 * MULE present, and random events.
	 * 
	 * @return
	 */
	public ResourceAmount calculateProduction() {
		return null;
	}

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
}
