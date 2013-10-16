package edu.gatech.cs2340.ui;
import javax.swing.JPanel;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.sequencing.MULETimer;
import edu.gatech.cs2340.sequencing.WaitedOn;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller: GUI
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Dan
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Manages free movement of player over the game world
 */
public class MapManager implements WaitedOn{
	private Player player;
	private boolean finished;
	
	/**
	 * #M6
	 * Main constructor. Handles GUI decisions for free range phase.
	 * 
	 * @param mainGameWindow
	 * @param timeout_ms
	 */
	public MapManager(Player player) {
		this.player = player;
		finished = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		finished = true;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}	
}
