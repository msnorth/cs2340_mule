package edu.gatech.cs2340.ui;

public abstract class ImageLoader implements Runnable{
	protected static final String resourcesPath = "M6/edu.gatech.cs2340.res";
	
	/**
	 * Method to load all resources
	 */
	public static void loadAllImages() {
		ImageLoader[] loaders = {new SpriteImageLoader(), new StoreImageLoader()};
		
		for (ImageLoader loader : loaders) {
			loader.loadImages();
		}		
	}
	
	/**
	 * Required method to load the images
	 */
	public abstract void loadImages();
	
}
