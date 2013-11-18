package edu.gatech.cs2340.test;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.gatech.cs2340.io.StartScreenImageLoader;
import edu.gatech.cs2340.ui.StartScreen;

public class StartScreenTester {
	public static void main(String args[]) {
		StartScreenImageLoader loader = new StartScreenImageLoader();
		loader.run();
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		StartScreen screen = new StartScreen();
		panel.add(screen);
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
	}
}
