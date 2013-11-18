package edu.gatech.cs2340.io;

import java.io.File;

import javax.swing.ImageIcon;

/**
 * 
 * @author Stephen Conway
 * 
 * Modified by: Thomas Mark
 * 				Copied core code from town image loader class.
 * 
 * Class to load start screen image and provide access to it.
 *
 */
public class StartScreenImageLoader extends ImageLoader {
	private static final String imageFileName = "StartScreen.png";
	private static ImageIcon startImage;
	private static boolean loaded = false;  
	
	/**
	 * Method to load image(s) asynchronously
	 */
	@Override
	public void loadImages() {
		if (loaded) {
			throw new RuntimeException("StartScreenImageLoader already loaded image(s)!");
		}
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Method to load image(s) asynchronously
	 */
	@Override
	public void run() {
		System.out.println("RUNNING");
		File imageFile = new File(String.format("%s/edu.gatech.cs2340.res.start_screen/%s",resourcesPath,imageFileName));
		if (!imageFile.exists()) {
			throw new RuntimeException("Cannot locate " + imageFile.getAbsolutePath() + ".");
		}
		startImage = new ImageIcon(imageFile.getAbsolutePath());
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
		return startImage;
	}

}
