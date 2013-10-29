package edu.gatech.cs2340.test;

import java.awt.GridLayout;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

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
		frame.getContentPane().add(mapRenderer);
		// compile the program and make it visible
		frame.pack();
		frame.setVisible(true);
	}

}