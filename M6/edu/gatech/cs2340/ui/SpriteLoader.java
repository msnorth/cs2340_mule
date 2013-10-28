package edu.gatech.cs2340.ui;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.net.URI;
import java.util.HashMap;

import javax.swing.ImageIcon;

import edu.gatech.cs2340.data.Player;

/**
 * 
 * @author Stephen Conway Function group: View
 * 			created for M8. 
 * 
 * 
 * 
 *         Purpose: Graphic of the inside of the town.
 */
public class SpriteLoader implements Runnable{
	public static Color[] colors = { Color.BLUE, Color.YELLOW, Color.GREEN, Color.RED };
	
	private static HashMap<String, HashMap<Color, ImageIcon>> images;
	private static boolean initialized;
	
	private SpriteLoader() {};
	
	public static void initialize() {
		images = new HashMap<String, HashMap<Color, ImageIcon>>();
		SpriteLoader spriteLoader = new SpriteLoader();
		Thread thread = new Thread(spriteLoader);
		thread.start();
	}

	@Override
	public void run() {
		String resources = "M6/edu.gatech.cs2340.res";
		File file = new File(resources);
		if (!file.exists() || !file.isDirectory()) {
			throw new RuntimeException("Cannot locate " + file.getAbsolutePath() + ".");
		}
		
		
		String[] races = {"bonzoid", "buzzite", "flapper", "human", "ugaite"};
		String[] color_png = {"_blue.png", "_gold.png", "_green.png", "_red.png"};
		for (String race : races) {
			HashMap<Color, ImageIcon> raceImages = new HashMap<Color, ImageIcon>();
			for (int i=0; i<4; i++) {
				File imageFile = new File(String.format("%s/edu.gatech.cs2340.res.%s/%s%s",resources,race,race,color_png[i]));
				if (!imageFile.exists()) {
					throw new RuntimeException("Cannot locate " + imageFile.getAbsolutePath() + ".");
				}
				ImageIcon ii = new ImageIcon(imageFile.getAbsolutePath());
				raceImages.put(colors[i], ii);
			}
			images.put(race, raceImages);
		}
		initialized = true;
	}
	
	public static ImageIcon getSprite(Player player) {
		if (!initialized) {
			throw new RuntimeException("Must initialize SpriteLoader first!");
		}
		
		String race = player.getRace().toLowerCase();
		Color color = player.getPlayerColor();
		return images.get(race).get(color);
	}
	
	
}
