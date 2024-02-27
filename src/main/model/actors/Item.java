package main.model.actors;

import main.controller.AudioManager;
import main.controller.PlayerManager;
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
		setRectHeightDimension(16);
		setRectWidthDimension(16);
		setRectangle();
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
				AudioManager.getInstance().play("item.wav",false);
				if(hero.getSpeed()<3)
				hero.setSpeed(hero.getSpeed() + 1);
				break;
			case BOMBNUM:
				AudioManager.getInstance().play("item.wav",false);
				if(hero.getMaxBombs()<3)
				hero.setMaxBombs(hero.getMaxBombs() + 1);
				break;
			case BOMBSTRENGHT:
				AudioManager.getInstance().play("item.wav",false);
				if (hero.getBombStrenght()<5)
				hero.setBombStrenght(hero.getBombStrenght() + 1);
				break;
			case LIFE:
				AudioManager.getInstance().play("item.wav",false);
				int currentLifes = Model.getInstance().getGame().getLifes();
				Model.getInstance().getGame().setLifes(currentLifes + 1);
				break;
			case WIN:
				AudioManager.getInstance().play("win.wav",false);
				Model.getInstance().getGame().setLevelFinish(true);
				Model.getInstance().getGame().setScore(Model.getInstance().getGame().getScore()+1000);
				Model.getInstance().updatePlayerPoints(false);
				if (Model.getInstance().getGame().getLevelPlaying() == 2) {
					PlayerManager.getInstance().updatePlayerStats(Model.getInstance().getGame().getPlayer());
				}
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
