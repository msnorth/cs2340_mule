package edu.gatech.cs2340.data;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class TileImageLoader extends ImageLoader {
	private static final String[] names = {"hill", "mountain", "peak", "plain", "river", "town"};
	private static HashMap<String, ImageIcon> images;
	private static boolean loaded = false;
	
	@Override
	public void loadImages() {
		if (loaded) {
			throw new RuntimeException("TileImageLoader already loaded images!");
		}
		images = new HashMap<String, ImageIcon>();
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		for (String name : names) {
			File imageFile = new File(String.format("%s/edu.gatech.cs2340.res.tile_base/%s",resourcesPath, name + ".png"));
			if (!imageFile.exists()) {
				throw new RuntimeException("Cannot locate " + imageFile.getAbsolutePath() + ".");
			}
			ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
			images.put(name, icon);
		}
		loaded = true;		
	}

	public ImageIcon getImage(Tile tile) {
		if (!loaded) {
			throw new RuntimeException("Must load image(s) first!");
		}
		String name = tile.getName();
		name = name.toLowerCase();
		ImageIcon icon = images.get(name);
		if (icon == null) {
			throw new RuntimeException("No image exists for tile with name " + name);
		}
		return icon;
	}
}
