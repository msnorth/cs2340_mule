package edu.gatech.cs2340.ui;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.Tile;

public class HillTile extends Tile{
<<<<<<< HEAD
	public static String name = "Hill";
	public HillTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
		this.setId(id);
		image = hillImage; // get static image from superclass
=======
	public HillTile(String id, Player owner, TileRenderer renderer) {
		super("Hill");
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
