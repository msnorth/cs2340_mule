package edu.gatech.cs2340.data;

import java.awt.Color;

/**
 * 
 * @author Dan
 *
 * Class representing the town tile on the map.
 */
public class TownTile extends Tile {
	public static String name = "Town";
	public TownTile(String id, Player owner) {
		super(name);
		Player player = new Player("Municipatily","Human",new Color(255,255,255));
	}
	@Override
	protected int getOreIncrease() {
		return 0;
	}
	@Override
	protected int getFoodIncrease() {
		return 0;
	}
	@Override
	protected int getEnergyIncrease() {
		return 0;
	}
	@Override
	protected int getCrystiteIncrease() {
		return 0;
	}

}
