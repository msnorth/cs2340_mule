package edu.gatech.cs2340.data;

/**
 * 
 * @author Dan
 * 
 * Class representing extremely hilly terrain
 *
 */
public class PeakTile extends Tile {
	public static String name = "Peak";
	public PeakTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
	}

	@Override
	protected int getOreIncrease() {
		return 4;
	}

	@Override
	protected int getFoodIncrease() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	protected int getEnergyIncrease() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	protected int getCrystiteIncrease() {
		// TODO Auto-generated method stub
		return 4;
	}


}
