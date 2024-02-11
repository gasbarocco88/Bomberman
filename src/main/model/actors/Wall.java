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
				Model.getInstance().getGame().setPoint(Model.getInstance().getGame().getPoint()+100);
				setActive(false); // mette il muro disattivo per poterlo cancellare dall'array
				if (!itemCollision) {
					// spawn di un Item il 30% delle volte,
					Random ran = new Random();
					int x = ran.nextInt(10) + 1;
					if (x <= 10) {
						// scelgo la tipologia di item da spawnare prendendolo per un indice generato
						// casualmente
						ItemType itemType = Item.ItemType.values()[ran.nextInt(Item.ItemType.values().length)];
						// la aggiungo agli actors
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
