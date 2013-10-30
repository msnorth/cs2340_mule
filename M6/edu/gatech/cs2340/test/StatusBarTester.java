package edu.gatech.cs2340.test;

import java.awt.Color;

import javax.swing.JFrame;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.sequencing.MULETimer;
import edu.gatech.cs2340.ui.StatusBar;

public class StatusBarTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("StatusBarTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 300, 400);
		Player[] players = { new Player("player1", "Human", Color.BLUE),
				new Player("player2", "Flapper", Color.GREEN) };
		MULETimer timer = new MULETimer(10000);
		timer.start();
		StatusBar statBar = new StatusBar(players, timer, frame.getWidth(),
				120);
		statBar.startTurn(players[1]);
		frame.getContentPane().add(statBar);
		frame.pack();
		frame.setVisible(true);

	}

}
