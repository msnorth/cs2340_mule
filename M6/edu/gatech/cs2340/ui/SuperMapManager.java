package edu.gatech.cs2340.ui;

import java.awt.Color;

import javax.swing.JFrame;
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
		TownRendererUpdated.initialize();
		TownRendererUpdated townRenderer = new TownRendererUpdated();
		
		StoreMenu storeMenu = new StoreMenu(player);
		
		JPanel[] panels = {storeMenu, townRenderer, mapRenderer};
		panel = new SuperTurnPanel(panels, sprite);
		
		MainGameWindow.getInstance().setPanel(panel);
		
		while (sprite.getLocation() != -1) {
			panel.setCurrentPanel(sprite.getLocation());
			if (sprite.getLocation() == 0) {
				storeMenu.repaint();
				Waiter.waitOn(storeMenu);
				sprite.update();
				sprite.setPosition(4700, 2500);
				panel.repaint();
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
