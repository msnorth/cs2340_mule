package edu.gatech.cs2340.ui;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.Store;
import edu.gatech.cs2340.data.ResourceAmount.ResourceType;

/**
 *@author Shreyyas Vanarase
 * 		Function group:		Model:  UI
 * 		Created for:		M8		10/25/13
 * 		Assigned to:		Shreyyas Vanarase
 * 		Modifications:		
 * 
 * 		Purpose: Generates the store menu for the player to interact with the store.
 */
public class StoreMenu {

	private JPanel storePanel;
	private int amount = 1;
	private Player player;
	private boolean disable;
	
	/**
	 * #M8
	 * Create the application.
	 */
	public StoreMenu(Player player) {
		initialize();
	}

	private void disableMuleButtons(JButton button, boolean disable) {
		if(player.hazMule()) {
			button.setEnabled(disable);
		}
	}
	/**
	 * #M8
	 * Initialize the contents of the panel and make the store menu.
	 * Allows the store to buy/sell resources and Mules. Allows the player
	 * to choose how much of each resource he/she wants and adds it to their
	 * resource list.
	 */
	
	private void initialize() {
		this.player = player;
		disable = false;
		
		//Defines the panel and sets its bounds
		storePanel = new JPanel();
		storePanel.setBackground(new Color(255, 255, 102));
		storePanel.setBounds(100, 100, 650, 500);
				
		//Makes the Store Menu Label
		JLabel title = new JLabel("Store Menu");
		title.setBounds(227, 12, 189, 31);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 21));
		
		//Makes a food label
		JLabel foodLabel = new JLabel("Food");
		foodLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		foodLabel.setBounds(300, 144, 46, 14);
		
		//Button to sell energy to player (player is buying energy)
		JButton buyEnergy = new JButton("Energy");
		buyEnergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().sellResources(ResourceType.ENERGY, amount);
			}
		});
		buyEnergy.setBackground(new Color(244, 50, 0));
		buyEnergy.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buyEnergy.setBounds(62, 223, 111, 23);
		
		//Button to sell smithore to player (player is buying smithore)
		JButton buySmithore = new JButton("Smithore");
		buySmithore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().sellResources(ResourceType.SMITHORE, amount);
			}
		});
		buySmithore.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buySmithore.setBackground(new Color(244, 50, 0));
		buySmithore.setBounds(62, 257, 111, 23);
		
		//Button to sell food to player (player is buying food)
		JButton buyFood = new JButton("Food");
		buyFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().sellResources(ResourceType.FOOD, amount);
			}
		});
		buyFood.setBackground(new Color(244, 50, 0));
		buyFood.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buyFood.setBounds(62, 291, 111, 23);
		
		//Button to sell crystite to player (player is buying crystite)
		JButton buyCrystite = new JButton("Crystite");
		buyCrystite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().sellResources(ResourceType.CRYSTITE, amount);
			}
		});
		buyCrystite.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buyCrystite.setBackground(new Color(244, 50, 0));
		buyCrystite.setBounds(62, 325, 111, 23);
		
		//adds these components to the panel now
		storePanel.setLayout(null);
		storePanel.add(title);
		storePanel.add(foodLabel);
		storePanel.add(buyEnergy);
		storePanel.add(buySmithore);
		storePanel.add(buyCrystite);
		storePanel.add(buyFood);
		
		//Makes a label for the Buying section
		JLabel buyTitle = new JLabel("Buy from the Store!");
		buyTitle.setForeground(new Color(0, 153, 51));
		buyTitle.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));
		buyTitle.setBounds(37, 184, 179, 23);
		storePanel.add(buyTitle);
		
		//Makes a label for the Selling section
		JLabel sellTitle = new JLabel("Sell to the Store!");
		sellTitle.setForeground(new Color(0, 153, 51));
		sellTitle.setBackground(new Color(0, 153, 51));
		sellTitle.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));
		sellTitle.setBounds(254, 188, 162, 14);
		storePanel.add(sellTitle);
		
		//Separates the selling title and the selling section
		JSeparator mainSeperator = new JSeparator();
		mainSeperator.setBackground(new Color(204, 0, 0));
		mainSeperator.setForeground(new Color(244, 50, 0));
		mainSeperator.setBounds(37, 204, 581, 8);
		storePanel.add(mainSeperator);
		
		//Button to buy energy from player (player is selling energy)
		JButton sellEnergy = new JButton("Energy");
		sellEnergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().buyResources(ResourceType.ENERGY, amount);
				System.out.println(amount);
			}
		});
		sellEnergy.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		sellEnergy.setBackground(new Color(244, 50, 0));
		sellEnergy.setBounds(277, 223, 111, 23);
		storePanel.add(sellEnergy);
		
		//Button to buy smithore from player (player is selling smithore)
		JButton sellSmithore = new JButton("Smithore");
		sellSmithore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().buyResources(ResourceType.SMITHORE, amount);
			}
		});
		sellSmithore.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		sellSmithore.setBackground(new Color(244, 50, 0));
		sellSmithore.setBounds(277, 257, 111, 23);
		storePanel.add(sellSmithore);
		
		//Button to buy food from player (player is selling food)
		JButton sellFood = new JButton("Food");
		sellFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().buyResources(ResourceType.FOOD, amount);
			}
		});
		sellFood.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		sellFood.setBackground(new Color(244, 50, 0));
		sellFood.setBounds(277, 291, 111, 23);
		storePanel.add(sellFood);
		
		//Button to buy energy from player (player is selling energy)
		JButton button = new JButton("Crystite");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().buyResources(ResourceType.CRYSTITE, amount);
			}
		});
		button.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		button.setBackground(new Color(244, 50, 0));
		button.setBounds(277, 325, 111, 23);
		storePanel.add(button);
		
		//Button to sell energy mule to player 
		JButton buyEnergyMule = new JButton("Energy Mule");
		buyEnergyMule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().sellMule(ResourceType.ENERGY);
			}
		});
		buyEnergyMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buyEnergyMule.setBackground(new Color(244, 50, 0));
		buyEnergyMule.setBounds(51, 359, 135, 23);
		storePanel.add(buyEnergyMule);
		
		//Button to sell smithore mule to player 
		JButton buyOreMule = new JButton("Ore Mule");
		disableMuleButtons(buyOreMule, disable);
		buyOreMule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().sellMule(ResourceType.SMITHORE);
				disable = true;
			}
		});
		buyOreMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buyOreMule.setBackground(new Color(244, 50, 0));
		buyOreMule.setBounds(51, 425, 135, 23);
		storePanel.add(buyOreMule);
		
		
		//Button to sell food mule to player 
		JButton buyFoodMule = new JButton("Food Mule");
		buyFoodMule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().sellMule(ResourceType.FOOD);
			}
		});
		buyFoodMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buyFoodMule.setBackground(new Color(244, 50, 0));
		buyFoodMule.setBounds(51, 391, 135, 23);
		storePanel.add(buyFoodMule);
		
		//Exits the store
		JButton exitButton = new JButton("Exit Store!");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storePanel.setVisible(false);
			}
		});
		exitButton.setBackground(new Color(244, 50, 0));
		exitButton.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		exitButton.setBounds(277, 466, 121, 23);
		storePanel.add(exitButton);
		
		/*
		 * Makes labels for the resources and images
		 */
		JLabel foodIcon = new JLabel("Food");
		foodIcon.setIcon(new ImageIcon(StoreMenu.class.getResource("/edu/gatech/cs2340/ui/FOODFinal.png")));
		foodIcon.setBounds(279, 73, 80, 60);
		storePanel.add(foodIcon);
		
		JLabel energyIcon = new JLabel("Energy");
		energyIcon.setIcon(new ImageIcon(StoreMenu.class.getResource("/edu/gatech/cs2340/ui/EnergyFinal.png")));
		energyIcon.setBounds(43, 72, 39, 62);
		storePanel.add(energyIcon);
		
		JLabel smithoreIcon = new JLabel("Smithore");
		smithoreIcon.setIcon(new ImageIcon(StoreMenu.class.getResource("/edu/gatech/cs2340/ui/SmithoreFinal.png")));
		smithoreIcon.setBounds(145, 69, 72, 71);
		storePanel.add(smithoreIcon);
		
		JLabel crystiteIcon = new JLabel("Crystite");
		crystiteIcon.setIcon(new ImageIcon(StoreMenu.class.getResource("/edu/gatech/cs2340/ui/CrystiteFinal.png")));
		crystiteIcon.setBounds(407, 68, 72, 71);
		storePanel.add(crystiteIcon);
		
		JLabel muleIcon = new JLabel("Mule");
		muleIcon.setIcon(new ImageIcon(StoreMenu.class.getResource("/edu/gatech/cs2340/ui/MuleFinal.png")));
		muleIcon.setBounds(533, 73, 75, 60);
		storePanel.add(muleIcon);
		
		JLabel energyLabel = new JLabel("Energy");
		energyLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		energyLabel.setBounds(37, 144, 72, 14);
		storePanel.add(energyLabel);
		
		JLabel smithoreLabel = new JLabel("Smithore");
		smithoreLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		smithoreLabel.setBounds(155, 145, 82, 14);
		storePanel.add(smithoreLabel);
		
		JLabel crystiteLabel = new JLabel("Crystite");
		crystiteLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		crystiteLabel.setBounds(417, 145, 87, 14);
		storePanel.add(crystiteLabel);
		
		JLabel muleLabel = new JLabel("Mule");
		muleLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		muleLabel.setBounds(562, 145, 56, 14);
		storePanel.add(muleLabel);
		
		JLabel welcomeLabel = new JLabel("Welcome to Irata 1 Thrift Shop!! Best of the Resource Shops!");
		welcomeLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		welcomeLabel.setBounds(74, 43, 509, 14);
		storePanel.add(welcomeLabel);
		
		JSeparator buySellSeperator = new JSeparator();
		buySellSeperator.setOrientation(SwingConstants.VERTICAL);
		buySellSeperator.setForeground(new Color(204, 0, 0));
		buySellSeperator.setBackground(new Color(204, 0, 0));
		buySellSeperator.setBounds(227, 204, 2, 249);
		storePanel.add(buySellSeperator);
		
		JSeparator sellStatsSeperator = new JSeparator();
		sellStatsSeperator.setOrientation(SwingConstants.VERTICAL);
		sellStatsSeperator.setForeground(new Color(204, 0, 0));
		sellStatsSeperator.setBackground(new Color(204, 0, 0));
		sellStatsSeperator.setBounds(435, 204, 2, 249);
		storePanel.add(sellStatsSeperator);
		
		JLabel statisticsLabel = new JLabel("Statistics");
		statisticsLabel.setForeground(new Color(0, 153, 51));
		statisticsLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));
		statisticsLabel.setBackground(new Color(0, 153, 51));
		statisticsLabel.setBounds(482, 188, 90, 14);
		storePanel.add(statisticsLabel);
		
		JLabel playerMoney = new JLabel("Player Money:");
		playerMoney.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		playerMoney.setBounds(448, 223, 101, 14);
		storePanel.add(playerMoney);
		
		JLabel playerResources = new JLabel("Your Current Resources: ");
		playerResources.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		playerResources.setBounds(447, 276, 193, 14);
		storePanel.add(playerResources);
		
		JLabel storeResources = new JLabel("Store Resources: ");
		storeResources.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		storeResources.setBounds(447, 359, 171, 14);
		storePanel.add(storeResources);
		
		JLabel energyPrice = new JLabel("$50");
		energyPrice.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		energyPrice.setBounds(51, 159, 35, 14);
		storePanel.add(energyPrice);
		
		JLabel smithorePrice = new JLabel("$75");
		smithorePrice.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		smithorePrice.setBounds(175, 159, 24, 14);
		storePanel.add(smithorePrice);
		
		JLabel foodPrice = new JLabel("$50");
		foodPrice.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		foodPrice.setBounds(305, 159, 35, 14);
		storePanel.add(foodPrice);
		
		JLabel crystitePrice = new JLabel("$200");
		crystitePrice.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		crystitePrice.setBounds(425, 159, 46, 14);
		storePanel.add(crystitePrice);
		
		JLabel mulePrice = new JLabel("$100");
		mulePrice.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		mulePrice.setBounds(562, 159, 46, 14);
		storePanel.add(mulePrice);
	}
}
