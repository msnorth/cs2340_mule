package edu.gatech.cs2340.ui;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.Store;
import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.data.StoreImageLoader;
import edu.gatech.cs2340.sequencing.WaitedOn;

/**
 *@author Shreyyas Vanarase
 * 		Function group:		Model:  UI
 * 		Created for:		M8		10/25/13
 * 		Assigned to:		Shreyyas Vanarase
 * 		Modifications:		
 * 
 * 		Purpose: Generates the store menu for the player to interact with the store.
 */
public class StoreMenu extends JPanel implements WaitedOn{
	private static final long serialVersionUID = 1L;
	private int amount = 1;
	private Player player;
	private boolean enable;
	private StoreImageLoader imageLoader;
	private JButton buyEnergy;
	private JButton buySmithore;
	private JButton buyFood;
	private JButton buyCrystite;
	private JButton sellEnergy;
	private JButton sellSmithore;
	private JButton sellFood;
	private JButton sellCrystite;
	private JButton buyEnergyMule;
	private JButton buySmithoreMule;
	private JButton buyFoodMule;
	private JButton exitButton;
	private boolean exitKilla;
	/**
	 * #M8
	 * Create the application.
	 */
	public StoreMenu(Player player) {
		this.player = player;
		initialize();
	}

	/**
	 * #M8
	 * Initialize the contents of the panel and make the store menu.
	 * Allows the store to buy/sell resources and Mules. Allows the player
	 * to choose how much of each resource he/she wants and adds it to their
	 * resource list.
	 */
	
	private void initialize() {
		enable = false;
		Store.getStore().addPlayer(player);
		
		imageLoader = new StoreImageLoader();
		
		//Defines the panel and sets its bounds
		setBackground(new Color(255, 255, 102));
		setBounds(100, 100, 650, 500);
	    setLayout(null);
	    
		//Makes the Store Menu Label
		JLabel title = new JLabel("Store Menu");
		title.setBounds(227, 12, 189, 31);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 21));
		add(title);
		
		//Button to sell energy to player (player is buying energy)
		buyEnergy = new JButton("Energy");
		buyEnergy.addActionListener(new BuyEnergyListener());
		buyEnergy.setBackground(new Color(244, 50, 0));
		buyEnergy.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buyEnergy.setBounds(62, 223, 111, 23);
		add(buyEnergy);
		
		//Button to sell smithore to player (player is buying smithore)
		buySmithore = new JButton("Smithore");
		buySmithore.addActionListener(new BuySmithoreListener());
		buySmithore.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buySmithore.setBackground(new Color(244, 50, 0));
		buySmithore.setBounds(62, 257, 111, 23);
		add(buySmithore);
		
		//Button to sell food to player (player is buying food)
		buyFood = new JButton("Food");
		buyFood.addActionListener(new BuyFoodListener());
		buyFood.setBackground(new Color(244, 50, 0));
		buyFood.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buyFood.setBounds(62, 291, 111, 23);
		add(buyFood);
		
		//Button to sell crystite to player (player is buying crystite)
		buyCrystite = new JButton("Crystite");
		buyCrystite.addActionListener(new BuyCrystiteListener());
		buyCrystite.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buyCrystite.setBackground(new Color(244, 50, 0));
		buyCrystite.setBounds(62, 325, 111, 23);
		add(buyCrystite);

		//Button to buy energy from player (player is selling energy)
		sellEnergy = new JButton("Energy");
		sellEnergy.addActionListener(new SellEnergyListener());
		sellEnergy.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		sellEnergy.setBackground(new Color(244, 50, 0));
		sellEnergy.setBounds(277, 223, 111, 23);
		add(sellEnergy);
		
		//Button to buy smithore from player (player is selling smithore)
		sellSmithore = new JButton("Smithore");
		sellSmithore.addActionListener(new SellSmithoreListener());
		sellSmithore.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		sellSmithore.setBackground(new Color(244, 50, 0));
		sellSmithore.setBounds(277, 257, 111, 23);
		add(sellSmithore);
		
		//Button to buy food from player (player is selling food)
		sellFood = new JButton("Food");
		sellFood.addActionListener(new SellFoodListener());
		sellFood.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		sellFood.setBackground(new Color(244, 50, 0));
		sellFood.setBounds(277, 291, 111, 23);
		add(sellFood);
		
		//Button to buy crystite from player (player is selling crystite)
		sellCrystite = new JButton("Crystite");
		sellCrystite.addActionListener(new SellCrystiteListener());
		sellCrystite.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		sellCrystite.setBackground(new Color(244, 50, 0));
		sellCrystite.setBounds(277, 325, 111, 23);
		add(sellCrystite);
		
		//Button to sell energy mule to player 
		buyEnergyMule = new JButton("Energy Mule");
		buyEnergyMule.addActionListener(new BuyEnergyMuleListener());
		buyEnergyMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buyEnergyMule.setBackground(new Color(244, 50, 0));
		buyEnergyMule.setBounds(51, 359, 135, 23);
		add(buyEnergyMule);
		
		//Button to sell smithore mule to player 
		buySmithoreMule = new JButton("Ore Mule");
		buySmithoreMule.addActionListener(new BuySmithoreMuleListener());
		buySmithoreMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buySmithoreMule.setBackground(new Color(244, 50, 0));
		buySmithoreMule.setBounds(51, 425, 135, 23);
		add(buySmithoreMule);
		
		//Button to sell food mule to player 
		buyFoodMule = new JButton("Food Mule");
		buyFoodMule.addActionListener(new BuyFoodMuleListener());
		buyFoodMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		buyFoodMule.setBackground(new Color(244, 50, 0));
		buyFoodMule.setBounds(51, 391, 135, 23);
		add(buyFoodMule);
		
		//Exits the store
		exitButton = new JButton("Exit Store!");
		exitButton.addActionListener(new ExitListener());
		exitButton.setBackground(new Color(244, 50, 0));
		exitButton.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		exitButton.setBounds(277, 466, 121, 23);
		add(exitButton);
		
		/*
		 * Makes labels for the resources and images
		 */
		JLabel foodIcon = new JLabel();
		foodIcon.setIcon(imageLoader.getImage("food"));
		foodIcon.setBounds(279, 73, 80, 60);
		add(foodIcon);
		
		JLabel energyIcon = new JLabel();
		energyIcon.setIcon(imageLoader.getImage("energy"));
		energyIcon.setBounds(43, 72, 39, 62);
		add(energyIcon);
		
		JLabel smithoreIcon = new JLabel();
		smithoreIcon.setIcon(imageLoader.getImage("smithore"));
		smithoreIcon.setBounds(145, 69, 72, 71);
		add(smithoreIcon);
		
		JLabel crystiteIcon = new JLabel();
		crystiteIcon.setIcon(imageLoader.getImage("crystite"));
		crystiteIcon.setBounds(407, 68, 72, 71);
		add(crystiteIcon);
		
		JLabel muleIcon = new JLabel();
		muleIcon.setIcon(imageLoader.getImage("mule"));
		muleIcon.setBounds(533, 73, 75, 60);
		add(muleIcon);
		
		//Makes a food label
		JLabel foodLabel = new JLabel("Food");
		foodLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		foodLabel.setBounds(300, 144, 46, 14);
		add(foodLabel);
		
		JLabel energyLabel = new JLabel("Energy");
		energyLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		energyLabel.setBounds(37, 144, 72, 14);
		add(energyLabel);
		
		JLabel smithoreLabel = new JLabel("Smithore");
		smithoreLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		smithoreLabel.setBounds(155, 145, 82, 14);
		add(smithoreLabel);
		
		JLabel crystiteLabel = new JLabel("Crystite");
		crystiteLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		crystiteLabel.setBounds(417, 145, 87, 14);
		add(crystiteLabel);
		
		JLabel muleLabel = new JLabel("Mule");
		muleLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		muleLabel.setBounds(562, 145, 56, 14);
		add(muleLabel);
		
		JLabel welcomeLabel = new JLabel("Welcome to Irata 1 Thrift Shop!! Best of the Resource Shops!");
		welcomeLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		welcomeLabel.setBounds(74, 43, 509, 14);
		add(welcomeLabel);
		
		JSeparator buySellSeperator = new JSeparator();
		buySellSeperator.setOrientation(SwingConstants.VERTICAL);
		buySellSeperator.setForeground(new Color(204, 0, 0));
		buySellSeperator.setBackground(new Color(204, 0, 0));
		buySellSeperator.setBounds(227, 204, 2, 249);
		add(buySellSeperator);
		
		JSeparator sellStatsSeperator = new JSeparator();
		sellStatsSeperator.setOrientation(SwingConstants.VERTICAL);
		sellStatsSeperator.setForeground(new Color(204, 0, 0));
		sellStatsSeperator.setBackground(new Color(204, 0, 0));
		sellStatsSeperator.setBounds(435, 204, 2, 249);
		add(sellStatsSeperator);
		
		JLabel statisticsLabel = new JLabel("Statistics");
		statisticsLabel.setForeground(new Color(0, 153, 51));
		statisticsLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));
		statisticsLabel.setBackground(new Color(0, 153, 51));
		statisticsLabel.setBounds(482, 188, 90, 14);
		add(statisticsLabel);
		
		JLabel playerMoney = new JLabel("Player Money:");
		playerMoney.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		playerMoney.setBounds(448, 223, 192, 14);
		add(playerMoney);
		
		JLabel playerResources = new JLabel("Your Current Resources:");
		playerResources.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		playerResources.setBounds(447, 260, 193, 14);
		add(playerResources);
		
		JLabel storeResources = new JLabel("Store Resources: ");
		storeResources.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		storeResources.setBounds(447, 377, 171, 14);
		add(storeResources);
		
		JLabel energyPrice = new JLabel("$25");
		energyPrice.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		energyPrice.setBounds(51, 159, 35, 14);
		add(energyPrice);
		
		JLabel smithorePrice = new JLabel("$50");
		smithorePrice.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		smithorePrice.setBounds(175, 159, 24, 14);
		add(smithorePrice);
		
		JLabel foodPrice = new JLabel("$30");
		foodPrice.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		foodPrice.setBounds(305, 159, 35, 14);
		add(foodPrice);
		
		JLabel crystitePrice = new JLabel("$100");
		crystitePrice.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		crystitePrice.setBounds(425, 159, 46, 14);
		add(crystitePrice);
		
		JLabel mulePrice = new JLabel("$100");
		mulePrice.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		mulePrice.setBounds(562, 159, 46, 14);
		add(mulePrice);
		
		//Makes a label for the Buying section
		JLabel buyTitle = new JLabel("Buy from the Store!");
		buyTitle.setForeground(new Color(0, 153, 51));
		buyTitle.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));
		buyTitle.setBounds(37, 184, 179, 23);
		add(buyTitle);
		
		//Makes a label for the Selling section
		JLabel sellTitle = new JLabel("Sell to the Store!");
		sellTitle.setForeground(new Color(0, 153, 51));
		sellTitle.setBackground(new Color(0, 153, 51));
		sellTitle.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));
		sellTitle.setBounds(254, 188, 162, 14);
		add(sellTitle);
		
		//Separates the selling title and the selling section
		JSeparator mainSeperator = new JSeparator();
		mainSeperator.setBackground(new Color(204, 0, 0));
		mainSeperator.setForeground(new Color(244, 50, 0));
		mainSeperator.setBounds(37, 204, 581, 8);
		add(mainSeperator);
		
		JLabel moneyLabel = new JLabel(player.getMoney()+"");
		moneyLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		moneyLabel.setBounds(447, 240, 46, 14);
		add(moneyLabel);
		
		JLabel playerEnergy = new JLabel("Energy: " +player.getResourceAmount(ResourceType.ENERGY));
		playerEnergy.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		playerEnergy.setBounds(447, 285, 95, 14);
		add(playerEnergy);
		
		JLabel playerFood = new JLabel("Food: "+player.getResourceAmount(ResourceType.FOOD));
		playerFood.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		playerFood.setBounds(447, 300, 46, 14);
		add(playerFood);
		
		JLabel playerSmithore = new JLabel("Smithore: "+player.getResourceAmount(ResourceType.SMITHORE));
		playerSmithore.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		playerSmithore.setBounds(447, 318, 95, 14);
		add(playerSmithore);
		
		JLabel playerCrystite = new JLabel("Crystite: "+player.getResourceAmount(ResourceType.CRYSTITE));
		playerCrystite.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		playerCrystite.setBounds(447, 335, 95, 14);
		add(playerCrystite);
		
		JLabel playerMule = new JLabel("Mule:  "+player.getResourceAmount(ResourceType.MULE) +" , " +player.getMule());
		playerMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		playerMule.setBounds(447, 352, 95, 14);
		add(playerMule);
		
		JLabel storeEnergy = new JLabel("Energy: " +Store.getStore().getResourceAmount(ResourceType.ENERGY));
		storeEnergy.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		storeEnergy.setBounds(447, 396, 95, 14);
		add(storeEnergy);
		
		JLabel storeFood = new JLabel("Food: "+Store.getStore().getResourceAmount(ResourceType.FOOD));
		storeFood.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		storeFood.setBounds(447, 411, 95, 14);
		add(storeFood);
		
		JLabel storeSmithore = new JLabel("Smithore: "+Store.getStore().getResourceAmount(ResourceType.SMITHORE));
		storeSmithore.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		storeSmithore.setBounds(447, 429, 136, 14);
		add(storeSmithore);
		
		JLabel storeCrystite = new JLabel("Crystite: "+Store.getStore().getResourceAmount(ResourceType.CRYSTITE));
		storeCrystite.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		storeCrystite.setBounds(447, 446, 95, 14);
		add(storeCrystite);
		
		JLabel storeMule = new JLabel("Mule:  "+Store.getStore().getResourceAmount(ResourceType.MULE));
		storeMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		storeMule.setBounds(447, 463, 95, 14);
		add(storeMule);
		
	}
	
	private class BuyEnergyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(player.getMoney() < Store.getStore().getResourcePrice(ResourceType.ENERGY)) {
				buyEnergy.setEnabled(enable);
			}
			else {
				Store.getStore().sellResources(ResourceType.ENERGY, amount);
				enable = true;
			}
		}
	}
	
	private class BuySmithoreListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(player.getMoney() < Store.getStore().getResourcePrice(ResourceType.SMITHORE)) {
				buySmithore.setEnabled(enable);
				System.out.println(enable);
			}
			else {
				Store.getStore().sellResources(ResourceType.SMITHORE, amount);
				enable = true;
			}
		}
	}
	
	private class BuyCrystiteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(player.getMoney() < Store.getStore().getResourcePrice(ResourceType.CRYSTITE)) {
				buyCrystite.setEnabled(enable);
			}
			else {
				Store.getStore().sellResources(ResourceType.CRYSTITE, amount);
				enable = true;
			}
		}
	}
	
	private class BuyFoodListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(player.getMoney() < Store.getStore().getResourcePrice(ResourceType.FOOD)) {
				buyFood.setEnabled(enable);
			}
			else {
				Store.getStore().sellResources(ResourceType.FOOD, amount);
				enable = true;
			}
		}
	}
	
	private class SellEnergyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Store.getStore().buyResources(ResourceType.ENERGY, amount);
		}
	}
	
	private class SellSmithoreListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Store.getStore().buyResources(ResourceType.SMITHORE, amount);
		}
	}
	
	private class SellFoodListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Store.getStore().buyResources(ResourceType.FOOD, amount);
		}
	}
	
	private class SellCrystiteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Store.getStore().buyResources(ResourceType.CRYSTITE, amount);
		}
	}
	
	private class BuyEnergyMuleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(player.hazMule() || (player.getMoney() < 
					Store.getStore().getMulePrice(ResourceType.ENERGY))) {
				buyEnergyMule.setEnabled(enable);
			}
			else {
				Store.getStore().sellMule(ResourceType.ENERGY);
				enable = true;
			}
		}
	}
	
	private class BuySmithoreMuleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(player.hazMule() || (player.getMoney() < 
					Store.getStore().getMulePrice(ResourceType.SMITHORE))) {
				buySmithoreMule.setEnabled(enable);
			}
			else {
				Store.getStore().sellMule(ResourceType.SMITHORE);
				enable = true;
			}
		}
	}
	
	private class BuyFoodMuleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(player.hazMule() || (player.getMoney() < 
					Store.getStore().getMulePrice(ResourceType.FOOD))) {
				buyFoodMule.setEnabled(enable);
			}
			else {
				Store.getStore().sellMule(ResourceType.FOOD);
				enable = true;
			}
		}
	}
	
	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			exitKilla = true;
		}
	}

	public boolean isFinished() {
		return exitKilla;
	}	
}
