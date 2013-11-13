package edu.gatech.cs2340.test;

import javax.swing.JFrame;

import edu.gatech.cs2340.ui.InGameMenu;

public class InGameMenuTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new InGameMenu());
		frame.pack();
		frame.setVisible(true);
	}
}
