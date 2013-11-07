package edu.gatech.cs2340.data;

public abstract class ImageLoader implements Runnable {
	protected static final String resourcesPath = "edu.gatech.cs2340.res";
	
	/**
	 * Method to load all resources
	 */
	public static void loadAllImages() {
		ImageLoader[] loaders = {new SpriteImageLoader(), new TileImageLoader(), new TownImageLoader(), new StoreImageLoader(), };
		
		for (ImageLoader loader : loaders) {
			loader.loadImages();
		}		
	}
	
	/**
	 * Required method to load the images
	 */
	public abstract void loadImages();
	
}
