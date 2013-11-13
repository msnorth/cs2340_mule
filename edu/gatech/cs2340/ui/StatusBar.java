/**
 * 
 */
package edu.gatech.cs2340.ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.io.SpriteImageLoader;
import edu.gatech.cs2340.sequencing.MULETimer;

/**
 * @author Madeleine North
 * 
 *         A status bar to interface with Timer and show players, turns, and
 *         resources
 * 
 */
public class StatusBar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Player[] players;
	private MULETimer timer;
	Player currentPlayer;

	

	private SpriteImageLoader spriteImgLoader;

	/**
	 * Main constructor
	 */
	public StatusBar(Player[] players) {
		this.players = players;
		spriteImgLoader = new SpriteImageLoader();
		Initialize();

	}

	/*
	 * Starts the turn for the current player, including starting the progress
	 * bar and creating a colored border around the currently active player.
	 * 
	 * If there is a current turn, the current turn ends.
	 * 
	 * @param currentPlayer the currently active player
	 */
	public void startTurn(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		refresh();
	}

	/*
	 * Ends the turn and the whole turn cycle. Stops the progress bar, removes
	 * colored border, removes the timer
	 */
	public void endTurn() {
		this.currentPlayer = null;
		this.timer = null;
		refresh();
	}
	
	/*
	 * Sets a timer to associate with a progressBar
	 * Refreshes to add progressBar to panel
	 * 
	 * @param timer 
	 */
	public void setTimer(MULETimer timer){
		this.timer = timer;
		refresh();
	}

	private void Initialize() {

		this.setBorder(new BevelBorder(BevelBorder.LOWERED));

		GridLayout grid = new GridLayout (1, players.length, 1, 0);
		
		this.setLayout(grid);

		
	

		for (Player player : players) {
			JPanel playerPanel = drawPlayerPanel(player);
			this.add(playerPanel);
		}

		
		//Create a progress bar only if there is a timer
		if (timer == null) {
			JPanel fillerPanel = new JPanel();
			this.add(fillerPanel);
		}else{
			JPanel progressBar = new ProgressBar(timer);
			this.add(progressBar);
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
	 * Refreshes stats of a player
	 * @param  player -player whose stats to refresh
	 */
	public void refreshPlayer(Player player){
		JPanel newPanel = drawPlayerPanel(player);
		int index = Arrays.asList(players).indexOf(player);
		this.remove(index);
		this.add(newPanel, index);
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

		JLabel playerMule = new JLabel("Mule: " +player.getMuleAmount());
		if(player.hazMule()) {
			playerMule.setText("Mule: " +player.getMuleAmount() + " , " +player.getMule().toString());
		}
		playerMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(playerMule);

		ImageIcon imageIcon = spriteImgLoader.getImage(player);
		JLabel playerIcon = new JLabel(imageIcon);
		playerPanel.add(playerIcon);
		playerPanel.add(labelPanel);
		grid.layoutContainer(playerPanel);

		return playerPanel;

	}



}
