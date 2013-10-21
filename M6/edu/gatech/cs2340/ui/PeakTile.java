package edu.gatech.cs2340.ui;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.Tile;

public class PeakTile extends Tile {
<<<<<<< HEAD
	public static String name = "Peak";
	public PeakTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
		this.setId(id);
		image = peakImage; // get static image from superclass
=======
	public PeakTile(String id, Player owner, TileRenderer renderer) {
		super("Peak");
		this.renderer = renderer;
		this.owner = owner;
		this.id = id;
>>>>>>> 2393cea41c2d70311bb028f29056d3dcdb4e35db
	}

	@Override
	public ResourceAmount calculateProduction() {
		// TODO Auto-generated method stub
		return null;
	}


}
