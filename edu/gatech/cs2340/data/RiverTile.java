package edu.gatech.cs2340.data;

/**
 * 
 * @author Dan
 *
 * Class representing river terrain. Good for food production.
 */
public class RiverTile extends Tile{
	public static String name = "River";
	public RiverTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
	}
	@Override
	protected int getOreIncrease() {
		return 0;
	}
	@Override
	protected int getFoodIncrease() {
		return 4;
	}
	@Override
	protected int getEnergyIncrease() {
		return 2;
	}
	@Override
	protected int getCrystiteIncrease() {
		return 0;
	}

}
