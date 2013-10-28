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

//        // Temp
//        GUIPanelTester panel = new GUIPanelTester();
//
//         panel.add(label,JLabel.CENTER);
//         // add it to frame
//                        frame.getContentPane().add(panel);
        // Make map
        Map map = MapGenerator.generateRandomMap();
        // Make map panel
        MapRenderer mapRenderer = new MapRenderer(map);
       // mapRenderer.initialize();
        frame.getContentPane().add(mapRenderer);
        
        
        
        // compile the program and make it visible
        frame.pack();
        frame.setVisible(true);
}

}