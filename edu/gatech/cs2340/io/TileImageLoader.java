package edu.gatech.cs2340.io;

import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;

import edu.gatech.cs2340.data.Tile;
import edu.gatech.cs2340.io.ImageLoader.ImagesNotInitializedException;

/**
 * 
 * @author Stephen
 *
 * Class to load tile images.
 */
public class TileImageLoader extends ImageLoader {
	private static final String[] names = {"hill", "mountain", "peak", "plain", "river", "town"};
	private static HashMap<String, ImageIcon> images;
	private static boolean loaded = false;
	
	/**
	 * Method to asynchronously load image data
	 */
	@Override
	public void loadImages() {
		if (loaded) {
			throw new ImagesAlreadyLoadedException();
		}
		images = new HashMap<String, ImageIcon>();
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Method to load image data
	 */
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

	/**
	 * Method to access image data
	 * @param tile
	 * @return
	 */
	public ImageIcon getImage(Tile tile) {
		if (!loaded) {
			throw new ImagesNotInitializedException();
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
