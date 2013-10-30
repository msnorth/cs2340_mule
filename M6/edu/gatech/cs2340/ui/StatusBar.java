/**
 * 
 */
package edu.gatech.cs2340.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.sequencing.MULETimer;

/**
 * @author Madeleine North
 * 
 *         A status bar to interface with Timer and show players, turns, and
 *         resources
 * 
 */
public class StatusBar extends JPanel {

	private Player[] players;
	private MULETimer timer;
	private int height;
	private int width;
	Player currentPlayer;

	private JProgressBar progressBar;

	/**
	 * Main constructor
	 */
	public StatusBar(Player[] players, MULETimer timer, int width, int height) {
		this.players = players;
		this.timer = timer;
		this.width = width;
		this.height = height;
		Initialize();

	}

	/*
	 * Starts the turn for the current player, including starting the progress
	 * bar and creating a colored border around the currently active player
	 * 
	 * @param currentPlayer the currently active player
	 */

	public void startTurn(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		refresh();
		Thread thread = new Thread() {
			public void run() {
				while (!timer.isFinished()) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							progressBar.setValue((int) timer.getTimeRemaining());
						}
					});

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
			}
		};

		thread.start();
	}

	/*
	 * Ends the turn. Stops the progress bar, removes colored border
	 */
	public void endTurn() {
		this.currentPlayer = null;
		refresh();
	}

	private void Initialize() {
		

		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.setPreferredSize(new Dimension(width, height));
		GridLayout grid = new GridLayout(1, players.length, 1, 0);
		this.setLayout(grid);

		for (Player player : players) {
			JPanel playerPanel = drawPlayerPanel(player);
			this.add(playerPanel);
		}
		// create the progress bar only if it is a turn
		if (currentPlayer != null) {
			JPanel progressPanel = drawProgressPanel();
			this.add(progressPanel);
		}

		grid.layoutContainer(this);

	}

	/*
	 * Redraws player panels and progress bar
	 */
	private void refresh() {
		this.removeAll();
		Initialize();
	}

	/*
	 * Makes a tile with stats for each player
	 */
	private JPanel drawPlayerPanel(Player player) {

		JPanel playerPanel = new JPanel();
		GridLayout grid = new GridLayout(1, 2, 0, 0);
		playerPanel.setLayout(grid);
		if (player.equals(currentPlayer)) {
			playerPanel.setBorder(BorderFactory.createLineBorder(
					player.getPlayerColor(), 2));
		} else {
			playerPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		}

		JPanel labelPanel = new JPanel();
		GridLayout labelGrid = new GridLayout(6, 1, 0, 0);
		labelPanel.setLayout(labelGrid);

		JLabel moneyLabel = new JLabel("Money" + " " + player.getMoney() + "");
		moneyLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(moneyLabel);

		JLabel playerEnergy = new JLabel("Energy: "
				+ player.getResourceAmount(ResourceType.ENERGY));
		playerEnergy
				.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(playerEnergy);

		JLabel playerFood = new JLabel("Food: "
				+ player.getResourceAmount(ResourceType.FOOD));
		playerFood.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(playerFood);

		JLabel playerSmithore = new JLabel("Smithore: "
				+ player.getResourceAmount(ResourceType.SMITHORE));
		playerSmithore.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN,
				11));
		labelPanel.add(playerSmithore);

		JLabel playerCrystite = new JLabel("Crystite: "
				+ player.getResourceAmount(ResourceType.CRYSTITE));
		playerCrystite.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN,
				11));
		labelPanel.add(playerCrystite);

		JLabel playerMule = new JLabel("Mule:  "
				+ player.getResourceAmount(ResourceType.MULE) + " , "
				+ player.getMule());
		playerMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(playerMule);

		JLabel playerIcon = new JLabel(player.getImageIcon());
		playerPanel.add(playerIcon);
		playerPanel.add(labelPanel);
		grid.layoutContainer(playerPanel);

		return playerPanel;

	}

	/*
	 *  
	 */
	private JPanel drawProgressPanel() {
		JPanel progressPanel = new JPanel();

		progressBar = new JProgressBar(JProgressBar.VERTICAL, 0,
				(int) timer.getTotalTime());
		progressPanel.add(progressBar);
		progressPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		return progressPanel;

	}

}
