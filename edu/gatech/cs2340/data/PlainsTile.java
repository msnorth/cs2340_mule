package edu.gatech.cs2340.data;


/**
 * 
 * @author Dan
 *
 * Class representing flat terrain good for energy production
 */
public class PlainsTile extends Tile {
	public static String name = "Plain";
	public PlainsTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
	}
	@Override
	protected int getOreIncrease() {
		return 1;
	}
	@Override
	protected int getFoodIncrease() {
		return 2;
	}
	@Override
	protected int getEnergyIncrease() {
		return 3;
	}
	@Override
	protected int getCrystiteIncrease() {
		return 1;
	}

}
