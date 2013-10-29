package edu.gatech.cs2340.data;

import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;

/**
 * Class to load and store store images.
 * @author Stephen
 *
 */
public class StoreImageLoader extends ImageLoader{
	private static final String[] imageNames = {"crystite", "energy", "food", "mule", "smithore"};
	private static final String folder = resourcesPath + "/edu.gatech.cs2340.res.store";
	
	private static HashMap<String, ImageIcon> images;
	private static boolean loaded;
	
	
	/**
	 * Method to asynchronously load images for store GUI
	 */
	public void loadImages() {
		if (loaded) {
			throw new RuntimeException("StoreImageLoader already initialized!");
		}
		images = new HashMap<String, ImageIcon>();
		loaded = false;
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Method to asynchronously load images for store GUI
	 */
	@Override
	public void run() {
		for (String name : imageNames) {
			File imageFile = new File(String.format("%s/%s.png",folder,name));
			if (!imageFile.exists()) {
				throw new RuntimeException(String.format("Cannot locate '%s'", imageFile.getAbsolutePath()));
			}
			ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
			images.put(name, icon);
		}	
		loaded = true;
	}
	
	/**
	 * Method to get images for store GUI
	 */
	public ImageIcon getImage(String name) {
		if (!loaded) {
			throw new RuntimeException("Must initialize StoreImageLoader first!");
		}
		if (!images.containsKey(name)) {
			throw new RuntimeException(String.format("Image does not exist: '%s'", name));
		}
		return images.get(name);
	}
}
