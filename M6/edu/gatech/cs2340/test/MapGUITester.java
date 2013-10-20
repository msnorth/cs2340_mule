package edu.gatech.cs2340.test;

import java.awt.GridLayout;

import javax.swing.*;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.MapGenerator;
import edu.gatech.cs2340.ui.MapRenderer;

/**
 * Tests the MapRenderer class
 * 
 * @author Dan
 * 
 */
public class MapGUITester {
	/**
	 * Simple run method to test MapRenderer
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("MapRendererTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Make map
		Map map = MapGenerator.generateStandardMap();
		// Make map panel
		MapRenderer mapRenderer = new MapRenderer(map);

		// Temp
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(9, 5, 1, 1));
		// panel
		ImageIcon plainImage = new ImageIcon(MapGUITester.class.getResource(
				"../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/plain.png"));
		JLabel label = new JLabel(plainImage);
		for (int i = 0; i < 45; i++) {
			//mapRenderer.add(label);
			panel.add(label, JLabel.CENTER);
		}
		// mapRenderer.initialize();

		// add it to frame
		frame.getContentPane().add(mapRenderer);
		// compile the program and make it visible
		frame.pack();
		frame.setVisible(true);
		// // TO_DO remove below
		// mapRenderer.refresh();
	}
}