package edu.gatech.cs2340.test;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import edu.gatech.cs2340.data.ImageLoader;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.ui.StoreMenu;

public class StoreMenuTest {

	private JFrame frame;
	private StoreMenu menu;

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
		frame.setBounds(100, 100, 700, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		ImageLoader.loadAllImages();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu = new StoreMenu(new Player("Bill", "Flapper", Color.BLUE));
		frame.add(panel);
		panel.add(menu);
		frame.pack();
		frame.setVisible(true);
	}
}

