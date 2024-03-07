package main.view.imageFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.model.Model;
import main.model.actors.Actor;
import main.model.actors.Blast;
import main.model.actors.Bomb;

/**
 * Classe che gestisce le immagini di una esplosione
 */
public class BlastImageFactory implements ImageFactory {

	private BufferedImage blast_1;
	private BufferedImage blast_2;
	private BufferedImage blast_3;
	private BufferedImage blast_4;

	/**
	 * Costruttore della classe BlastImageFactory, carica le immagini da disco.
	 */
	public BlastImageFactory() {
		try {
			blast_1 = ImageIO.read(new File("./src/main/resources/images/blast/blast_1.png"));
			blast_2 = ImageIO.read(new File("./src/main/resources/images/blast/blast_2.png"));
			blast_3 = ImageIO.read(new File("./src/main/resources/images/blast/blast_3.png"));
			blast_4 = ImageIO.read(new File("./src/main/resources/images/blast/blast_4.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo che ritorna l'immagine di una esplosione a seconda del countdown
	 * passato dal momento in cui quell'esplosione è stata generata.
	 */
	@Override
	public BufferedImage returnImage(Actor a) {

		String avatar = Model.getInstance().getGame().getPlayer().getAvatar();

		if (a instanceof Blast) {
			Blast x = (Blast) a;

			if (x.getCountdown() < 250 || x.getCountdown() == 1000.0)
				return blast_1;
			else if (x.getCountdown() < 500)
				return blast_2;
			else if (x.getCountdown() < 750)
				return blast_3;
			else
				return blast_4;
		}

		return null;
	}
}