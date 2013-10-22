package edu.gatech.cs2340.ui;
import javax.swing.JPanel;

import edu.gatech.cs2340.data.Map;
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
 * 							M7		10/21/13 Stephen Conway
 * 									Runs asynchronously.
 * 
 * 
 * 		Purpose: Manages free movement of player over the game world
 */
public class MapManager implements WaitedOn, Runnable{
	private Player player;
	private boolean finished;
	private Map map;
	
	/**
	 * #M6
	 * Main constructor. Handles GUI decisions for free range phase.
	 * 
	 * @param mainGameWindow
	 * @param timeout_ms
	 */
	public MapManager(Player player, Map map) {
		this.player = player;
		this.map = map;
		finished = false;
	}
	
	public void runAsynchronous() {
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		MapSprite sprite = new MapSprite(player);
		MapRenderer mapRenderer = new MapRenderer(map, sprite);
		MainGameWindow.getInstance().setPanel(mapRenderer);
		while (true) {
			try {
				Thread.sleep(25);
			} 
			catch (InterruptedException e) {}
			mapRenderer.refresh();
		}
		
		//finished = true;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}	
}
