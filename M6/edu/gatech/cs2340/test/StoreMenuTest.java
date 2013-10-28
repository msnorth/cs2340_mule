package edu.gatech.cs2340.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.data.Store;
import edu.gatech.cs2340.ui.StoreMenu;

public class StoreMenuTest {

	private JFrame frame;
	private JPanel storePanel;
	private int amount = 1;
	private Player player;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreMenuTest window = new StoreMenuTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StoreMenuTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		player = new Player("Bill", "Flapper", Color.BLUE);

		//Defines the panel and sets its bounds
		storePanel = new JPanel();
		storePanel.setBackground(new Color(255, 255, 102));
		storePanel.setBounds(100, 100, 650, 500);
		frame.getContentPane().add(storePanel);
		
		//Creates a JSpinner to allow the player to set the amount of the resource he wants.
		spinner = new JSpinner();
		spinner.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setForeground(new Color(244, 50, 0));
		spinner.setBackground(new Color(244, 50, 0));
		spinner.setBounds(356, 450, 100, 23);
		storePanel.add(spinner);
		amount = (int) spinner.getValue();
		System.out.println(amount);
		
		//Makes the Store Menu Label
		JLabel title = new JLabel("Store Menu");
		title.setBounds(227, 12, 189, 31);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 25));
		
		//Makes a food label
		JLabel foodLabel = new JLabel("Food");
		foodLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		foodLabel.setBounds(315, 173, 46, 14);
		
		//A button to sell a base mule (player is buying a base mule)
		JButton buyMule = new JButton("Mule");
		buyMule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	Store.getStore().sellMule(ResourceType.MULE); BASE MULE IMPLEMENTATION NEEDED?
			}
		});
		buyMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		buyMule.setBackground(new Color(244, 50, 0));
		buyMule.setBounds(37, 301, 111, 23);
		
		//Button to sell energy to player (player is buying energy)
		JButton buyEnergy = new JButton("Energy");
		buyEnergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(player.getResourceAmount(ResourceType.ENERGY));
				Store.getStore().sellResources(ResourceType.ENERGY, (int)spinner.getValue());
				System.out.println((int)spinner.getValue()); //store not actually adding to resourceAmount to player
				System.out.println(player.getResourceAmount(ResourceType.ENERGY));
				System.out.println(Store.getStore().getResourceAmount(ResourceType.ENERGY)); //Store isn't adding any resourceAmount. 
																							 //There aren't any resources to begin with so it doesn't ever add
			}
		});
		buyEnergy.setBackground(new Color(244, 50, 0));
		buyEnergy.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		buyEnergy.setBounds(37, 259, 111, 23);
		
		//Button to sell smithore to player (player is buying smithore)
		JButton buySmithore = new JButton("Smithore");
		buySmithore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().sellResources(ResourceType.SMITHORE, amount);
			}
		});
		buySmithore.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		buySmithore.setBackground(new Color(244, 50, 0));
		buySmithore.setBounds(188, 259, 111, 23);
		
		//Button to sell food to player (player is buying food)
		JButton buyFood = new JButton("Food");
		buyFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().sellResources(ResourceType.FOOD, amount);
			}
		});
		buyFood.setBackground(new Color(244, 50, 0));
		buyFood.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		buyFood.setBounds(345, 259, 111, 23);
		
		//Button to sell crystite to player (player is buying crystite)
		JButton buyCrystite = new JButton("Crystite");
		buyCrystite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().sellResources(ResourceType.CRYSTITE, amount);
			}
		});
		buyCrystite.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		buyCrystite.setBackground(new Color(244, 50, 0));
		buyCrystite.setBounds(500, 259, 111, 23);
		
		//adds these components to the panel now
		storePanel.setLayout(null);
		storePanel.add(title);
		storePanel.add(foodLabel);
		storePanel.add(buyEnergy);
		storePanel.add(buySmithore);
		storePanel.add(buyMule);
		storePanel.add(buyCrystite);
		storePanel.add(buyFood);
		
		//Separates the buying title and the buying section
		JSeparator buySeperator = new JSeparator();
		buySeperator.setForeground(new Color(244, 50, 0));
		buySeperator.setBounds(37, 240, 585, 8);
		storePanel.add(buySeperator);
		
		//Makes a label for the Buying section
		JLabel buyTitle = new JLabel("Buy from the Store!");
		buyTitle.setForeground(new Color(0, 153, 51));
		buyTitle.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		buyTitle.setBounds(37, 215, 238, 23);
		storePanel.add(buyTitle);
		
		//Makes a label for the Selling section
		JLabel sellTitle = new JLabel("Sell to the Store!");
		sellTitle.setForeground(new Color(0, 153, 51));
		sellTitle.setBackground(new Color(0, 153, 51));
		sellTitle.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		sellTitle.setBounds(37, 347, 205, 14);
		storePanel.add(sellTitle);
		
		//Separates the selling title and the selling section
		JSeparator sellSeperator = new JSeparator();
		sellSeperator.setForeground(new Color(244, 50, 0));
		sellSeperator.setBounds(37, 372, 585, 8);
		storePanel.add(sellSeperator);
		
		//Button to buy energy from player (player is selling energy)
		JButton sellEnergy = new JButton("Energy");
		sellEnergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().buyResources(ResourceType.ENERGY, amount);
				System.out.println(amount);
			}
		});
		sellEnergy.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		sellEnergy.setBackground(new Color(244, 50, 0));
		sellEnergy.setBounds(37, 391, 111, 23);
		storePanel.add(sellEnergy);
		
		//Button to buy smithore from player (player is selling smithore)
		JButton sellSmithore = new JButton("Smithore");
		sellSmithore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().buyResources(ResourceType.SMITHORE, amount);
			}
		});
		sellSmithore.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		sellSmithore.setBackground(new Color(244, 50, 0));
		sellSmithore.setBounds(188, 391, 111, 23);
		storePanel.add(sellSmithore);
		
		//Button to buy food from player (player is selling food)
		JButton sellFood = new JButton("Food");
		sellFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().buyResources(ResourceType.FOOD, amount);
			}
		});
		sellFood.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		sellFood.setBackground(new Color(244, 50, 0));
		sellFood.setBounds(345, 391, 111, 23);
		storePanel.add(sellFood);
		
		//Button to buy energy from player (player is selling energy)
		JButton button = new JButton("Crystite");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().buyResources(ResourceType.CRYSTITE, amount);
			}
		});
		button.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		button.setBackground(new Color(244, 50, 0));
		button.setBounds(500, 391, 111, 23);
		storePanel.add(button);
		
		//Button to sell energy mule to player 
		JButton buyEnergyMule = new JButton("Energy Mule");
		buyEnergyMule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().sellMule(ResourceType.ENERGY);
			}
		});
		buyEnergyMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		buyEnergyMule.setBackground(new Color(244, 50, 0));
		buyEnergyMule.setBounds(178, 301, 135, 23);
		storePanel.add(buyEnergyMule);
		
		//Button to sell smithore mule to player 
		JButton buyOreMule = new JButton("Ore Mule");
		buyOreMule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().sellMule(ResourceType.SMITHORE);
			}
		});
		buyOreMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		buyOreMule.setBackground(new Color(244, 50, 0));
		buyOreMule.setBounds(487, 301, 135, 23);
		storePanel.add(buyOreMule);
		
		//Button to sell food mule to player 
		JButton buyFoodMule = new JButton("Food Mule");
		buyFoodMule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.getStore().sellMule(ResourceType.FOOD);
			}
		});
		buyFoodMule.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		buyFoodMule.setBackground(new Color(244, 50, 0));
		buyFoodMule.setBounds(339, 301, 126, 23);
		storePanel.add(buyFoodMule);
		
		//Exits the store
		JButton exitButton = new JButton("Exit Store!");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storePanel.setVisible(false);
				frame.dispose();
			}
		});
		exitButton.setBackground(new Color(244, 50, 0));
		exitButton.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		exitButton.setBounds(496, 449, 122, 23);
		storePanel.add(exitButton);
		
		/*
		 * Makes labels for the resources and images
		 */
		JLabel foodIcon = new JLabel("Food");
		foodIcon.setIcon(new ImageIcon(StoreMenu.class.getResource("/edu/gatech/cs2340/ui/FOODFinal.png")));
		foodIcon.setBounds(294, 101, 80, 60);
		storePanel.add(foodIcon);
		
		JLabel energyIcon = new JLabel("Energy");
		energyIcon.setIcon(new ImageIcon(StoreMenu.class.getResource("/edu/gatech/cs2340/ui/EnergyFinal.png")));
		energyIcon.setBounds(58, 100, 39, 62);
		storePanel.add(energyIcon);
		
		JLabel smithoreIcon = new JLabel("Smithore");
		smithoreIcon.setIcon(new ImageIcon(StoreMenu.class.getResource("/edu/gatech/cs2340/ui/SmithoreFinal.png")));
		smithoreIcon.setBounds(160, 97, 72, 71);
		storePanel.add(smithoreIcon);
		
		JLabel crystiteIcon = new JLabel("Crystite");
		crystiteIcon.setIcon(new ImageIcon(StoreMenu.class.getResource("/edu/gatech/cs2340/ui/CrystiteFinal.png")));
		crystiteIcon.setBounds(422, 96, 72, 71);
		storePanel.add(crystiteIcon);
		
		JLabel muleIcon = new JLabel("Mule");
		muleIcon.setIcon(new ImageIcon(StoreMenu.class.getResource("/edu/gatech/cs2340/ui/MuleFinal.png")));
		muleIcon.setBounds(548, 101, 75, 60);
		storePanel.add(muleIcon);
		
		JLabel energyLabel = new JLabel("Energy");
		energyLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		energyLabel.setBounds(52, 172, 72, 14);
		storePanel.add(energyLabel);
		
		JLabel smithoreLabel = new JLabel("Smithore");
		smithoreLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		smithoreLabel.setBounds(171, 172, 82, 14);
		storePanel.add(smithoreLabel);
		
		JLabel crystiteLabel = new JLabel("Crystite");
		crystiteLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		crystiteLabel.setBounds(422, 173, 87, 14);
		storePanel.add(crystiteLabel);
		
		JLabel muleLabel = new JLabel("Mule");
		muleLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		muleLabel.setBounds(566, 173, 56, 14);
		storePanel.add(muleLabel);
		
		JLabel welcomeLabel = new JLabel("Welcome to Irata 1 Thrift Shop!! Best of the Resource Shops!");
		welcomeLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		welcomeLabel.setBounds(84, 55, 509, 14);
		storePanel.add(welcomeLabel);

		JLabel transactionLabel = new JLabel("Please Enter Amount You'd Like to Buy/Sell: ");
		transactionLabel.setForeground(new Color(0, 0, 0));
		transactionLabel.setBackground(new Color(0, 0, 0));
		transactionLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		transactionLabel.setBounds(37, 454, 309, 14);
		storePanel.add(transactionLabel);
	}
}

