package main.model.actors;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import main.model.Model;
import main.model.actors.Item;
import main.model.actors.Enemy.EnemyType;
import main.model.actors.Item.ItemType;

public class Wall extends Actor {
	private final boolean isDestructible;

	public Wall(int posX, int posY, final boolean isDestructible) {
		super(posX, posY);
		this.isDestructible = isDestructible;
		setName("Wall");
		setPriorityByDesctructible(isDestructible);
		setFrameCounter(0);
		setActive(true);
	}

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
