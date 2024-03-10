package main.model.actors;

import main.controller.AudioManager;

import main.controller.PlayerManager;
import main.model.Model;

/**
 * Classe che gestisce gli Item, ovvero i potenziamenti che un Hero può
 * raccogliere. Esistono 4 diversi tipi di potenziamento che vengono generati
 * randomicamente, oltre all'item che serve a superare un livello e che è sempre
 * presente, nascosto sotto un Wall distruttibile.
 */
public class Item extends Actor {
	private final ItemType itemType;

	/**
	 * Costruttore della classe Item, setta alcune parametri interni quali la
	 * dimensione del rettangolo delle collisioni, il frame counter etc
	 * 
	 * @param posX: coordinata x della posizione dell'attore
	 * @param posY: coordinata y della posizione dell'attore
	 * @param type: la tipologia dell'item istanziato
	 */
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

	/**
	 * Metodo chiamato dall'update del model. Esegue il check delle collisioni con
	 * hero in modo da assegnargli il rispettivo bonus a seconda del type dell'item.
	 */
	@Override
	public void update() {
		Hero hero = (Hero) Model.getInstance().getActors().stream().filter(actor -> actor.getName().equals("Hero"))
				.findFirst().orElse(null);

		if (hero != null && Model.getInstance().checkCollision(this, hero, Direction.ANY)) {

			// disattiva item e aumenta il punteggio
			setActive(false);
			Model.getInstance().getGame().setScore(Model.getInstance().getGame().getScore() + 50);

			switch (itemType) {
			case SPEED:
				AudioManager.getInstance().play("item.wav", false);
				if (hero.getSpeed() < 3)
					hero.setSpeed(hero.getSpeed() + 1);
				break;
			case BOMBNUM:
				AudioManager.getInstance().play("item.wav", false);
				if (hero.getMaxBombs() < 3)
					hero.setMaxBombs(hero.getMaxBombs() + 1);
				break;
			case BOMBSTRENGHT:
				AudioManager.getInstance().play("item.wav", false);
				if (hero.getBombStrenght() < 5)
					hero.setBombStrenght(hero.getBombStrenght() + 1);
				break;
			case LIFE:
				AudioManager.getInstance().play("item.wav", false);
				int currentLifes = Model.getInstance().getGame().getLifes();
				Model.getInstance().getGame().setLifes(currentLifes + 1);
				break;
			case WIN:
				AudioManager.getInstance().play("win.wav", false);
				Model.getInstance().getGame().setLevelFinish(true);
				Model.getInstance().getGame().setScore(Model.getInstance().getGame().getScore() + 1000);
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
