package main.view.imageFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.model.Model;
import main.model.actors.Actor;
import main.model.actors.Wall;

/**
 * Classe che gestisce le immagini di un Muro.
 */
public class WallImageFactory implements ImageFactory {

	private BufferedImage destr_wall;
	private BufferedImage non_destr_wall;

	/**
	 * Costruttore della classe WallImageFactory, carica le immagini da disco.
	 */
	public WallImageFactory() {
		try {
			destr_wall = ImageIO.read(new File("./src/main/resources/images/wall/wall_destructible.png"));
			non_destr_wall = ImageIO.read(new File("./src/main/resources/images/wall/wall.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo che ritorna l'immagine del muro a seconda della tipologia
	 */
	@Override
	public BufferedImage returnImage(Actor a) {

		String avatar = Model.getInstance().getGame().getPlayer().getAvatar();

		if (a instanceof Wall) {
			Wall x = (Wall) a;
			if (x.isDestructible())
				return destr_wall;
			else
				return non_destr_wall;
		}
		return null;
	}
}