package main.model.actors;

import main.model.Model;

public class Item extends Actor {
	private final ItemType itemType;

	public Item(int posX, int posY, final ItemType type) {
		super(posX, posY);
		this.itemType = type;
		setName("Item");
		setPriority(1);
		setFrameCounter(0);
		setActive(true);
	}

	@Override
	public void update() {
		Hero hero = (Hero) Model.getInstance().getActors().stream().filter(actor -> actor.getName().equals("Hero"))
				.findFirst().orElse(null);

		if (hero != null && Model.getInstance().checkCollision(this, hero, Direction.ANY)) {
			
			//disattiva item e aumenta il punteggio
			setActive(false);
			Model.getInstance().getGame().setScore(Model.getInstance().getGame().getScore()+50);
			
			
			switch (itemType) {
			case SPEED:
				hero.setSpeed(hero.getSpeed() + 1);
				break;
			case BOMBNUM:
				hero.setMaxBombs(hero.getMaxBombs() + 1);
				break;
			case BOMBSTRENGHT:
				hero.setBombStrenght(hero.getBombStrenght() + 2);
				break;
			case LIFE:
				int currentLifes = Model.getInstance().getGame().getLifes();
				Model.getInstance().getGame().setLifes(currentLifes + 1);
				break;
			case WIN:
				Model.getInstance().getGame().setScore(Model.getInstance().getGame().getScore()+1000);
				Model.getInstance().changeLevel();
				break;

			default:
				return;
			}

		}

	}

	public enum ItemType {
		SPEED, BOMBNUM, BOMBSTRENGHT, LIFE, WIN
	}

	public ItemType getItemType() {
		return itemType;
	}

}
