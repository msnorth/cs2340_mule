package edu.gatech.cs2340.io;

/**
 * 
 * @author Stephen
 *
 * Parent of all image loader classes. Contains path data and static method to run all loaders.
 */
public abstract class ImageLoader implements Runnable {
	protected static final String resourcesPath = "edu.gatech.cs2340.res";
	
	/**
	 * Method to load all resources
	 */
	public static void loadAllImages() {
		ImageLoader[] loaders = {//new StartScreenImageLoader(),
								 new SpriteImageLoader(), 
								 new TileImageLoader(), 
								 new TownImageLoader(), 
								 new StoreImageLoader()};
		
		for (ImageLoader loader : loaders) {
			loader.loadImages();
		}		
	}
	
	/**
	 * Required method to load the images
	 */
	public abstract void loadImages();
	
	
	/**
	 * @author Stephen Conway
	 * 
	 * Exception to be thrown when images are attempted to be accessed before they are loaded.
	 */
	public static class ImagesNotInitializedException extends RuntimeException {
		
	}
	
	/**
	 * @author Stephen Conway
	 * 
	 * Exception to be thrown when images are attempted to be loaded a second time.
	 */
	public static class ImagesAlreadyLoadedException extends RuntimeException {
		
	}
}
