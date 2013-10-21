package edu.gatech.cs2340.ui;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.Tile;

<<<<<<< HEAD
public class TownTile extends Tile {
	public static String name = "Town";
	public TownTile(String id, Player owner) {
		super(name);
		this.setOwner(null);
		this.setId(id);
		image = townImage; // get static image from superclass
	}
	@Override
	public ResourceAmount calculateProduction() {
		// This doesn't apply
=======
public class TownTile extends Tile{
	public TownTile(String id, Player owner, TileRenderer renderer) {
		super("Town");
		this.id = id;
		this.owner = owner;
		this.renderer = renderer;
	}

	@Override
	public ResourceAmount calculateProduction() {
		// TODO Auto-generated method stub
>>>>>>> 2393cea41c2d70311bb028f29056d3dcdb4e35db
		return null;
	}

}
