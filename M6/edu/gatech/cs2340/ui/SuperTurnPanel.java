package edu.gatech.cs2340.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class SuperTurnPanel extends JPanel{
	private JPanel[] panels;
	private int currentPanel;
	private SuperSprite sprite;

	public SuperTurnPanel(JPanel[] panels, SuperSprite sprite) {
		this.panels = panels;
		this.sprite = sprite;
		add(panels[0]);
		currentPanel = 0;
	}
	
	public void setCurrentPanel(int ndx) {
		if (ndx != currentPanel) {
			removeAll();
			add(panels[ndx]);
			revalidate();
			currentPanel = ndx;
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(sprite.getImage(), sprite.getScreenX(), sprite.getScreenY(), this);
	}
}
