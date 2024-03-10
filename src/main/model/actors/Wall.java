package main.model.actors;

import java.util.Random;

import main.model.Model;
import main.model.actors.Item.ItemType;

/**
 * Classe che gestisce i muri presenti in un livello di gioco e che Hero non può
 * attraversare. I muri possono essere sia distruttibili, se collidono con
 * l'esplosione di una bomba, che non distruttibili.
 */
public class Wall extends Actor {
	private final boolean isDestructible;

	public Wall(int posX, int posY, final boolean isDestructible) {
		super(posX, posY);
		this.isDestructible = isDestructible;
		setName("Wall");
		setPriorityByDesctructible(isDestructible);
		setFrameCounter(0);
		setActive(true);
		setRectHeightDimension(28);
		setRectWidthDimension(28);
		setRectangle();
	}

	/**
	 * Metodo chiamato dall'update del model. Esegue il check delle collisioni con
	 * un Blast in modo da essere disattivato in cui sia un Wall distruttibile.
	 * Quando un wall distruttibile viene disattivato, fa spawnare un Item
	 * randomicamente con una probabilità del 30%.
	 */
	@Override
	public void update() {

		// check collision con esplosioni se il muro è distruttibile
		if (isDestructible == true) {
			boolean blastsCollision = Model.getInstance().getActors().stream()
					.filter(actor -> actor.getName() == "Blast")
					.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.ANY));

			boolean itemCollision = Model.getInstance().getActors().stream()
					.filter(actor -> actor.getName() == "Item" && ((Item) actor).getItemType() == ItemType.WIN)
					.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.ANY));

			if (blastsCollision) {
				Model.getInstance().getGame().setScore(Model.getInstance().getGame().getScore() + 100);
				setActive(false);
				// spawn di un item il 30% delle volte
				// tranne se c'era già un item sotto (quello della vittoria del livello)
				if (!itemCollision) {
					Random ran = new Random();
					int x = ran.nextInt(10) + 1;
					if (x <= 3) {
						// scelgo la tipologia di item da spawnare
						// selezionandola attraverso l'indice dell'enum
						// con un valore generato randomicamente
						ItemType itemType = Item.ItemType.values()[ran.nextInt(Item.ItemType.values().length)];
						Model.getInstance().getActors().add((new Item(getPosX(), getPosY(), itemType)));
					}
				}
			}
		}
	}

	private void setPriorityByDesctructible(boolean isDestructible) {
		if (isDestructible) {
			setPriority(3);
		} else
			setPriority(7);
	}

	public boolean isDestructible() {
		return isDestructible;
	}

}
