package edu.gatech.cs2340.ui;

import javax.swing.JPanel;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.sequencing.WaitedOn;
import edu.gatech.cs2340.sequencing.Waiter;

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
		TownRenderer.initialize();
		TownRenderer townRenderer = new TownRenderer();
		
		StoreMenu storeMenu = new StoreMenu(player);
		JPanel[] panels = {storeMenu, townRenderer, mapRenderer,};
		panel = new SuperTurnPanel(panels, sprite);
		
		MainGameWindow.getInstance().setPanel(panel);
		
		while (sprite.getLocation() != -1) {
			panel.setCurrentPanel(sprite.getLocation());
			if (sprite.getLocation() == 0) {
				Waiter.waitOn(storeMenu);
				sprite.setPosition(4700, 2500);
			}
			else {
				sprite.update();
				panel.repaint();
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		finished = true;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}
	
	
	
}
