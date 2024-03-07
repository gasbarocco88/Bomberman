package main.view.imageFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.model.Model;
import main.model.actors.Actor;
import main.model.actors.Item;
import main.model.actors.Item.ItemType;

/**
 * Classe che gestisce le immagini di un Item
 */
public class ItemImageFactory implements ImageFactory {

	private BufferedImage speed;
	private BufferedImage bombnum;
	private BufferedImage bombstrenght;
	private BufferedImage life;
	private BufferedImage win;

	/**
	 * Costruttore della classe ItemImageFactory, carica le immagini da disco.
	 */
	public ItemImageFactory() {
		try {
			speed = ImageIO.read(new File("./src/main/resources/images/item/speed.png"));
			bombnum = ImageIO.read(new File("./src/main/resources/images/item/bomb_num.png"));
			bombstrenght = ImageIO.read(new File("./src/main/resources/images/item/bomb_strenght.png"));
			life = ImageIO.read(new File("./src/main/resources/images/item/life.png"));
			win = ImageIO.read(new File("./src/main/resources/images/item/win.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo che ritorna l'immagine dell'item a seconda della tipologia
	 */
	@Override
	public BufferedImage returnImage(Actor a) {

		String avatar = Model.getInstance().getGame().getPlayer().getAvatar();

		if (a instanceof Item) {
			Item x = (Item) a;

			if (x.getItemType() == ItemType.SPEED)
				return speed;
			else if (x.getItemType() == ItemType.BOMBNUM)
				return bombnum;
			else if (x.getItemType() == ItemType.BOMBSTRENGHT)
				return bombstrenght;
			else if (x.getItemType() == ItemType.LIFE)
				return life;
			else if (x.getItemType() == ItemType.WIN)
				return win;
		}

		return null;
	}
}
