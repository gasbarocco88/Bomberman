package main.view.imageFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.model.Model;
import main.model.actors.Actor;
import main.model.actors.Bomb;

/**
 * Classe che gestisce le immagini di una bomba
 */
public class BombImageFactory implements ImageFactory {

	private BufferedImage bomb_1;
	private BufferedImage bomb_2;
	private BufferedImage bomb_3;

	/**
	 * Costruttore della classe BombImageFactory, carica le immagini da disco.
	 */
	public BombImageFactory() {
		try {
			bomb_1 = ImageIO.read(new File("./src/main/resources/images/bomb/bomb_1.png"));
			bomb_2 = ImageIO.read(new File("./src/main/resources/images/bomb/bomb_2.png"));
			bomb_3 = ImageIO.read(new File("./src/main/resources/images/bomb/bomb_3.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo che ritorna l'immagine di una bomba a seconda del countdown
	 * passato dal momento in cui quella bomba è stata generata.
	 */
	@Override
	public BufferedImage returnImage(Actor a) {

		String avatar = Model.getInstance().getGame().getPlayer().getAvatar();

		if (a instanceof Bomb) {
			Bomb x = (Bomb) a;

			if (x.getCountdown() < 1500)
				return bomb_3;
			else if (x.getCountdown() < 2500)
				return bomb_2;
			else
				return bomb_1;
		}
		return null;
	}
}
