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
		MapSprite mapSprite = new MapSprite(player);
		MapRenderer mapRenderer = new MapRenderer(map, mapSprite);
		
		TownSprite townSprite = new TownSprite(player);
		TownRenderer townRenderer = new TownRenderer(townSprite);
		TownRenderer.initialize();
		townRenderer.refresh();
		
		while (!townSprite.hasEnteredPub()) {
			mapSprite.resetPosition();
			MainGameWindow.getInstance().setPanel(mapRenderer);
			while (!mapSprite.hasEnteredTown()) {
				try {
					Thread.sleep(25);
				} 
				catch (InterruptedException e) {}
				mapRenderer.refreshSprite();
			}
			
			townSprite.resetPosition();
			MainGameWindow.getInstance().setPanel(townRenderer);
			while (!townSprite.hasLeftTown() && !townSprite.hasEnteredPub()) {
				try {
					Thread.sleep(25);
				} 
				catch (InterruptedException e) {}
				townRenderer.refreshSprite();
			}
		}
		
		
		finished = true;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}	
}
