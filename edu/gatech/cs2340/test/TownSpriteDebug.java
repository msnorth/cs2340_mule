package edu.gatech.cs2340.test;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.gatech.cs2340.io.KeyboardAdapter;
import edu.gatech.cs2340.ui.MainGameWindow;
import edu.gatech.cs2340.ui.MapSprite;
import edu.gatech.cs2340.ui.TownSprite;

public class TownSpriteDebug extends JPanel{

	public TownSprite sprite1;
	public MapSprite sprite2;
	
	public static void main(String[] args) {
		KeyboardAdapter.initialize();
		MainGameWindow.initialize();
		JFrame frame = new JFrame("Fantastickle Tacos");
		TownSpriteDebug panel = new TownSpriteDebug();
		panel.sprite1 = new TownSprite(null);
		panel.sprite2 = new MapSprite(null);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		while (true) {
			panel.sprite1.update();
			panel.sprite2.update();
			panel.repaint();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {}
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(sprite1.getImage(), sprite1.getScreenX(), sprite1.getScreenY(), this);
		g2d.drawImage(sprite2.getImage(), sprite2.getScreenX(), sprite2.getScreenY(), this);
	}
	
}
