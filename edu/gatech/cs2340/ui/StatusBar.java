/**
 * 
 */
package edu.gatech.cs2340.ui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.data.SpriteImageLoader;
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
	private JLabel playerMule;
	private JLabel playerCrystite;
	private JLabel playerSmithore;
	private JLabel playerFood;
	private JLabel playerEnergy;
	private JLabel moneyLabel;

	/**
	 * Main constructor
	 */
	public StatusBar(Player[] players) {
		this.players = players;
		spriteImgLoader = new SpriteImageLoader();
		initialize();
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
		refreshMenu(currentPlayer);
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

	private void initialize() {

		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		GridLayout grid = new GridLayout(1, players.length, 1, 0);
		this.setLayout(grid);

		for (Player player : players) {
			JPanel playerPanel = drawPlayerPanel(player);
			this.add(playerPanel);
		}
		
		//Create a progress bar only if there is a timer
		if (timer == null) {
			JPanel fillerPanel = new JPanel();
			this.add(fillerPanel);
		} else {
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
		initialize();
	}

	/*
	 * Makes a tile with stats for each player
	 */
	private JPanel drawPlayerPanel(Player player) {

		JPanel playerPanel = new JPanel();
		GridLayout grid = new GridLayout(1, 2, 0, 0);
		playerPanel.setLayout(grid);
		if (player == currentPlayer) {
			playerPanel.setBorder(BorderFactory.createLineBorder(
					player.getPlayerColor(), 2));
		} else {
			playerPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		}

		JPanel labelPanel = new JPanel();
		GridLayout labelGrid = new GridLayout(6, 1, 0, 0);
		labelPanel.setLayout(labelGrid);

		moneyLabel = new JLabel("Money" + " " + player.getMoney() + "");
		moneyLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(moneyLabel);

		playerEnergy = new JLabel("Energy: "+ player.getResourceAmount(ResourceType.ENERGY));
		playerEnergy.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(playerEnergy);

		playerFood = new JLabel("Food: "+ player.getResourceAmount(ResourceType.FOOD));
		playerFood.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(playerFood);

		playerSmithore = new JLabel("Smithore: "+ player.getResourceAmount(ResourceType.SMITHORE));
		playerSmithore.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(playerSmithore);

		playerCrystite = new JLabel("Crystite: "+ player.getResourceAmount(ResourceType.CRYSTITE));
		playerCrystite.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(playerCrystite);

		playerMule = new JLabel("Mule: " +player.getMuleAmount());
		if(player.hazMule()) {
			playerMule.setText("Mule: " +player.getMuleAmount() + " , " +player.getMule().toString());
		}
		else playerMule.setText("Mule: " +player.getMuleAmount());
		playerMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(playerMule);

		ImageIcon imageIcon = spriteImgLoader.getImage(player);
		JLabel playerIcon = new JLabel(imageIcon);
		playerPanel.add(playerIcon);
		playerPanel.add(labelPanel);
		grid.layoutContainer(playerPanel);

		return playerPanel;
	}
	
	public void refreshMenu(Player player) {
		moneyLabel.setText(player.getMoney()+"");
		playerEnergy.setText("Energy: " +player.getResourceAmount(ResourceType.ENERGY));
		playerFood.setText("Food: "+player.getResourceAmount(ResourceType.FOOD));
		playerSmithore.setText("Smithore: "+player.getResourceAmount(ResourceType.SMITHORE));
		playerCrystite.setText("Crystite: "+player.getResourceAmount(ResourceType.CRYSTITE));
		if(player.hazMule()) {
			playerMule.setText("Mule: " +player.getMuleAmount() + " , " +player.getMule().toString());
		}
		else playerMule.setText("Mule: " +player.getMuleAmount());
	}
}
