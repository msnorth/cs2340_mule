package edu.gatech.cs2340.ui;

import javax.swing.JPanel;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.sequencing.WaitedOn;

public class SuperMapManager implements WaitedOn, Runnable{
	private SuperTurnPanel panel;
	private Player player;
	private Map map;
	private boolean finished;
	
	
	public SuperMapManager(Player player, Map map) {
		this.player = player;
		this.map = map;
		finished = false;
	}
	
	
	public void runAsynchronous() {
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		
		
		SuperSprite sprite = new SuperSprite(player);
		sprite.setPosition(4500, 3500);
		MapRenderer mapRenderer = new MapRenderer(map);
		TownRenderer townRenderer = new TownRenderer();
		StoreMenu storeMenu = new StoreMenu();
		JPanel[] panels = {mapRenderer, townRenderer, storeMenu};
		panel = new SuperTurnPanel(panels, sprite);
		
		MainGameWindow.getInstance().setPanel(panel);
		int currentPanel = 0;
		
		
		
		
		finished = true;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}
	
	
	
}
