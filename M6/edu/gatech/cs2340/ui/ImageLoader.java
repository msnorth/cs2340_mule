package edu.gatech.cs2340.ui;

public abstract class ImageLoader implements Runnable{
	protected static final String resourcesPath = "M6/edu.gatech.cs2340.res";
	
	public abstract void loadImages();
	
}
