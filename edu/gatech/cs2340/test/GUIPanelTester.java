package edu.gatech.cs2340.test;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIPanelTester extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GUIPanelTester() {
		this.setLayout(new GridLayout(4, 4, 3, 3));
		ImageIcon plainImage = new ImageIcon(getClass().getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/plain.png"));
//		plainImage = new ImageIcon(
//				this.getClass()
//						.getResource(
//								"../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/plain.png"));
		
		System.out.println("Success in reading image");
		
		for (int i = 0; i < 16; i++) {
			JLabel label = new JLabel(plainImage, JLabel.CENTER);
//			label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
//			 ImageIcon plainImage = new ImageIcon(this.getClass().getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.tile_base/plain.png"));
//			 JLabel label = new JLabel(plainImage);
//			 panel.add(label, JLabel.CENTER);
			add(label, JLabel.CENTER);
		}

	}
}
