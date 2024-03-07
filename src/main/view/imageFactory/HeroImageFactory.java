package main.view.imageFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.model.Model;
import main.model.actors.Actor;
import main.model.actors.Direction;
import main.model.actors.Hero;

/**
 * Classe che gestisce le immagini del personaggio utilizzato dal giocatore.
 */
public class HeroImageFactory implements ImageFactory {

	private BufferedImage gokuUpOne;
	private BufferedImage gokuUpTwo;
	private BufferedImage gokuDownOne;
	private BufferedImage gokuDownTwo;
	private BufferedImage gokuLeftOne;
	private BufferedImage gokuLeftTwo;
	private BufferedImage gokuRightOne;
	private BufferedImage gokuRightTwo;

	private BufferedImage piccoloUpOne;
	private BufferedImage piccoloUpTwo;
	private BufferedImage piccoloDownOne;
	private BufferedImage piccoloDownTwo;
	private BufferedImage piccoloLeftOne;
	private BufferedImage piccoloLeftTwo;
	private BufferedImage piccoloRightOne;
	private BufferedImage piccoloRightTwo;

	private BufferedImage vegetaUpOne;
	private BufferedImage vegetaUpTwo;
	private BufferedImage vegetaDownOne;
	private BufferedImage vegetaDownTwo;
	private BufferedImage vegetaLeftOne;
	private BufferedImage vegetaLeftTwo;
	private BufferedImage vegetaRightOne;
	private BufferedImage vegetaRightTwo;

	/**
	 * Costruttore della classe HeroImageFactory, carica le immagini da disco.
	 */
	public HeroImageFactory() {
		try {
			gokuUpOne = ImageIO.read(new File("./src/main/resources/images/hero/goku_up_1.png"));
			gokuUpTwo = ImageIO.read(new File("./src/main/resources/images/hero/goku_up_2.png"));
			gokuDownOne = ImageIO.read(new File("./src/main/resources/images/hero/goku_down_1.png"));
			gokuDownTwo = ImageIO.read(new File("./src/main/resources/images/hero/goku_down_2.png"));
			gokuLeftOne = ImageIO.read(new File("./src/main/resources/images/hero/goku_left_1.png"));
			gokuLeftTwo = ImageIO.read(new File("./src/main/resources/images/hero/goku_left_2.png"));
			gokuRightOne = ImageIO.read(new File("./src/main/resources/images/hero/goku_right_1.png"));
			gokuRightTwo = ImageIO.read(new File("./src/main/resources/images/hero/goku_right_2.png"));

			piccoloUpOne = ImageIO.read(new File("./src/main/resources/images/hero/piccolo_up_1.png"));
			piccoloUpTwo = ImageIO.read(new File("./src/main/resources/images/hero/piccolo_up_2.png"));
			piccoloDownOne = ImageIO.read(new File("./src/main/resources/images/hero/piccolo_down_1.png"));
			piccoloDownTwo = ImageIO.read(new File("./src/main/resources/images/hero/piccolo_down_2.png"));
			piccoloLeftOne = ImageIO.read(new File("./src/main/resources/images/hero/piccolo_left_1.png"));
			piccoloLeftTwo = ImageIO.read(new File("./src/main/resources/images/hero/piccolo_left_2.png"));
			piccoloRightOne = ImageIO.read(new File("./src/main/resources/images/hero/piccolo_right_1.png"));
			piccoloRightTwo = ImageIO.read(new File("./src/main/resources/images/hero/piccolo_right_2.png"));

			vegetaUpOne = ImageIO.read(new File("./src/main/resources/images/hero/vegeta_up_1.png"));
			vegetaUpTwo = ImageIO.read(new File("./src/main/resources/images/hero/vegeta_up_2.png"));
			vegetaDownOne = ImageIO.read(new File("./src/main/resources/images/hero/vegeta_down_1.png"));
			vegetaDownTwo = ImageIO.read(new File("./src/main/resources/images/hero/vegeta_down_2.png"));
			vegetaLeftOne = ImageIO.read(new File("./src/main/resources/images/hero/vegeta_left_1.png"));
			vegetaLeftTwo = ImageIO.read(new File("./src/main/resources/images/hero/vegeta_left_2.png"));
			vegetaRightOne = ImageIO.read(new File("./src/main/resources/images/hero/vegeta_right_1.png"));
			vegetaRightTwo = ImageIO.read(new File("./src/main/resources/images/hero/vegeta_right_2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo che ritorna l'immagine del personaggio utilizzato dal giocatore, a
	 * seconda dell'avatar scelto e del suo frame counter.
	 */
	@Override
	public BufferedImage returnImage(Actor a) {

		String avatar = Model.getInstance().getGame().getPlayer().getAvatar();

		if (a instanceof Hero) {
			Hero x = (Hero) a;

			if (avatar.equals("goku")) {

				if (x.getDirection() == Direction.UP)
					return a.getFrameCounter() < 12 ? gokuUpOne : gokuUpTwo;
				else if (x.getDirection() == Direction.DOWN)
					return a.getFrameCounter() < 12 ? gokuDownOne : gokuDownTwo;
				else if (x.getDirection() == Direction.LEFT)
					return a.getFrameCounter() < 12 ? gokuLeftOne : gokuLeftTwo;
				else if (x.getDirection() == Direction.RIGHT)
					return a.getFrameCounter() < 12 ? gokuRightOne : gokuRightTwo;
				else
					return gokuDownOne;
			}

			else if (avatar.equals("piccolo")) {

				if (x.getDirection() == Direction.UP)
					return a.getFrameCounter() < 12 ? piccoloUpOne : piccoloUpTwo;
				else if (x.getDirection() == Direction.DOWN)
					return a.getFrameCounter() < 12 ? piccoloDownOne : piccoloDownTwo;
				else if (x.getDirection() == Direction.LEFT)
					return a.getFrameCounter() < 12 ? piccoloLeftOne : piccoloLeftTwo;
				else if (x.getDirection() == Direction.RIGHT)
					return a.getFrameCounter() < 12 ? piccoloRightOne : piccoloRightTwo;
				else
					return piccoloDownOne;
			}

			else {

				if (x.getDirection() == Direction.UP)
					return a.getFrameCounter() < 12 ? vegetaUpOne : vegetaUpTwo;
				else if (x.getDirection() == Direction.DOWN)
					return a.getFrameCounter() < 12 ? vegetaDownOne : vegetaDownTwo;
				else if (x.getDirection() == Direction.LEFT)
					return a.getFrameCounter() < 12 ? vegetaLeftOne : vegetaLeftTwo;
				else if (x.getDirection() == Direction.RIGHT)
					return a.getFrameCounter() < 12 ? vegetaRightOne : vegetaRightTwo;
				else
					return vegetaDownOne;
			}

		}

		return null;
	}

}
