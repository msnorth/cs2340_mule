package edu.gatech.cs2340.test;


import javax.swing.JFrame;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.MapGenerator;
import edu.gatech.cs2340.data.Tile;
import edu.gatech.cs2340.ui.MapRenderer;

public class MapRendererTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Map map = MapGenerator.generateStandardMap();
		MapRenderer renderer = new MapRenderer(map);
		frame.add(renderer);
		frame.setVisible(true);
		frame.pack();
	}
}
