package main.view;

import java.awt.image.BufferedImage;

import main.model.actors.Actor;

@FunctionalInterface
public interface ImageFactory {
	
	
	public abstract BufferedImage returnImage(Actor a);
	

}
