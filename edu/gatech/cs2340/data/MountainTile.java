package edu.gatech.cs2340.data;


/**
 * 
 * @author Dan
 * 
 * Class representing very hilly terrain
 */
public class MountainTile extends Tile{
	public static String name = "Mountain";
	public final static int CRYSTITE_INCREASE = 2;
	public final static int ORE_INCREASE = 3;
	public final static int ENERGY_INCREASE = 1;
	public final static int FOOD_INCREASE = 1;
	public MountainTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
	}
	@Override
	protected int getOreIncrease() {
		return 3;
	}
	@Override
	protected int getFoodIncrease() {
		return 1;
	}
	@Override
	protected int getEnergyIncrease() {
		return 1;
	}
	@Override
	protected int getCrystiteIncrease() {
		return 3;
	}
}
