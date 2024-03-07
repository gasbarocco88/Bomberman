package main.view.imageFactory;

import java.awt.image.BufferedImage;

import main.model.actors.Actor;

/**
 * Interfaccia che definisce un metodo per il caricamento di una immagine di un actor
 */
@FunctionalInterface
public interface ImageFactory {
	
	public abstract BufferedImage returnImage(Actor a);
}
