package edu.gatech.cs2340.ui;
import javax.swing.JPanel;

import edu.gatech.cs2340.sequencing.MULETimer;


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
public class MapManager implements GUIManager{
	private MainGameWindow mainGameWindow;
	private MULETimer timer;
	
	/**
	 * #M6
	 * Main constructor. Handles GUI decisions for free range phase.
	 * 
	 * @param mainGameWindow
	 * @param timeout_ms
	 */
	public MapManager(MainGameWindow mainGameWindow, long timeout_ms) {
		this.mainGameWindow = mainGameWindow;
		timer = new MULETimer(timeout_ms);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isFinished() {
		return timer.isFinished();
	}

	@Override
	public void notify(JPanel panel, String message) {
		// TODO Auto-generated method stub
		
	}
	
	
}
