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
import javax.swing.border.BevelBorder;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.io.SpriteImageLoader;
import edu.gatech.cs2340.sequencing.MULETimer;
import edu.gatech.cs2340.sequencing.Waiter;

/**
 * @author Madeleine North
 * 
 *         A status bar to interface with Timer and show players, turns, and
 *         resources
 * 
 */
public class StatusBar extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	private Player[] players;
	private MULETimer timer;

	/* for updating player resources */
	private MULETimer updateTimer;
	Player currentPlayer;

	private SpriteImageLoader spriteImgLoader;

	private boolean refresh;

	/**
	 * Main constructor
	 */
	public StatusBar(Player[] players) {
		this.players = players;
		spriteImgLoader = new SpriteImageLoader();
		Initialize();
		refresh = true;
	}

	/**
	 * Starts the turn for the current player, including starting the progress
	 * bar and creating a colored border around the currently active player.
	 * 
	 * If there is a current turn, the current turn ends.
	 * 
	 * @param currentPlayer
	 *            the currently active player
	 * @param timer2
	 *            the MULETimer with the player's turn length
	 */
	public void startTurn(Player currentPlayer, MULETimer timer) {
		this.timer = timer;
		this.currentPlayer = currentPlayer;
		refresh();
	}

	private void Initialize() {

		this.setBorder(new BevelBorder(BevelBorder.LOWERED));

		GridLayout grid = new GridLayout(1, players.length, 1, 0);

		this.setLayout(grid);

		for (Player player : players) {
			JPanel playerPanel = drawPlayerPanel(player);
			this.add(playerPanel);
		}
		// Create a progress bar only if there is a timer
		if (timer == null) {
			JPanel fillerPanel = new JPanel();
			this.add(fillerPanel);
		} else {
			JPanel progressBar = new ProgressBar(timer);
			progressBar.setPreferredSize(getPreferredSize());
			this.add(progressBar);
		}

		grid.layoutContainer(this);

	}

	/**
	 * Redraws player panels and progress bar
	 */

	private void refresh() {
		refresh = false;
		this.removeAll();
		Initialize();
		refresh = true;
	}

	/**
	 * Refreshes stats of a player
	 * 
	 * @param playerIndex
	 *            -index of player to refresh
	 */

	private void refreshPlayer(int playerIndex) {
		try {
			Player player = players[playerIndex];
			JPanel newPanel = drawPlayerPanel(player);
			this.remove(playerIndex);
			this.add(newPanel, playerIndex);
		} catch (Exception e) {
			// do nothing
		}
	}

	/**
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
		GridLayout labelGrid = new GridLayout(7, 1, 0, 0);
		labelPanel.setLayout(labelGrid);

		JLabel fillerLabel = new JLabel("");
		JLabel playerName = new JLabel(player.getName());
		playerName.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(playerName);
		labelPanel.add(fillerLabel);

		JLabel moneyLabel = new JLabel("Money: ");
		JLabel playerMoney = new JLabel(player.getMoney() + "");
		playerMoney
				.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		moneyLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(moneyLabel);
		labelPanel.add(playerMoney);

		JLabel energyLabel = new JLabel("Energy: ");
		JLabel playerEnergy = new JLabel(
				player.getResourceAmount(ResourceType.ENERGY) + "");
		playerEnergy
				.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		energyLabel
				.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(energyLabel);
		labelPanel.add(playerEnergy);

		JLabel foodLabel = new JLabel("Food: ");
		JLabel playerFood = new JLabel(""
				+ player.getResourceAmount(ResourceType.FOOD));
		playerFood.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		foodLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(foodLabel);
		labelPanel.add(playerFood);

		JLabel oreLabel = new JLabel("Smithore: ");
		JLabel playerSmithore = new JLabel(""
				+ player.getResourceAmount(ResourceType.SMITHORE));
		playerSmithore.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN,
				11));
		oreLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(oreLabel);
		labelPanel.add(playerSmithore);

		JLabel crystiteLabel = new JLabel("Crystite: ");
		JLabel playerCrystite = new JLabel(""
				+ player.getResourceAmount(ResourceType.CRYSTITE));
		playerCrystite.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN,
				11));
		crystiteLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN,
				11));
		labelPanel.add(crystiteLabel);
		labelPanel.add(playerCrystite);

		JLabel muleLabel = new JLabel("Mule: ");
		JLabel playerMule = new JLabel("" + player.getMuleAmount());
		if (player.hasMule()) {
			playerMule.setText("" + player.getMuleAmount() + " , "
					+ player.getMule().toString());
		}
		muleLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		playerMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		labelPanel.add(muleLabel);
		labelPanel.add(playerMule);

		ImageIcon imageIcon = spriteImgLoader.getImage(player);
		JLabel playerIcon = new JLabel(imageIcon);
		playerPanel.add(playerIcon);
		playerPanel.add(labelPanel);
		grid.layoutContainer(playerPanel);

		return playerPanel;

	}

	@Override
	public void run() {
		while (true) {

			// update();
			if (refresh) {
				for (int i = 0; i < players.length; i++) {
					refreshPlayer(i);

				}

			}

			updateTimer = new MULETimer(25);
			updateTimer.start();
			Waiter.waitOn(updateTimer, 500);

		}

	}

	/**
	 * Method to run the Status Bar in a separate thread.
	 * 
	 */
	public void runAsynchronous() {
		new Thread(this).start();
	}

}
