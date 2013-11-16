package edu.gatech.cs2340.io;

import java.io.File;

import javax.swing.ImageIcon;

/**
 * 
 * @author Stephen Conway
 * 
 * Class to load town image and provide access to it.
 *
 */
public class TownImageLoader extends ImageLoader {
	private static final String imageFileName = "TownRender.png";
	private static ImageIcon townImage;
	private static boolean loaded = false;  
	
	/**
	 * Method to load image(s) asynchronously
	 */
	@Override
	public void loadImages() {
		if (loaded) {
			throw new RuntimeException("TownImageLoader already loaded image(s)!");
		}
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Method to load image(s) asynchronously
	 */
	@Override
	public void run() {
		File imageFile = new File(String.format("%s/edu.gatech.cs2340.res.town/%s",resourcesPath,imageFileName));
		if (!imageFile.exists()) {
			throw new RuntimeException("Cannot locate " + imageFile.getAbsolutePath() + ".");
		}
		townImage = new ImageIcon(imageFile.getAbsolutePath());
		loaded = true;
	}

	/**
	 * Method to get access to the town's image data
	 * @return
	 */
	public ImageIcon getImage() {
		if (!loaded) {
			throw new RuntimeException("Must load images first!");
		}
		return townImage;
	}

}
