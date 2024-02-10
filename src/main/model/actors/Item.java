package main.model.actors;

import main.model.Model;

public class Item extends Actor {
	private final ItemType itemType;
	public Item(int posX, int posY, final ItemType type) {
		super(posX, posY);
		this.itemType = type;
		setName("Item");
		setPriority(5);
		setFrameCounter(0);
		setActive(true);
	}

	@Override
	public void update() {
		Hero hero = (Hero) Model.getInstance().getActors().stream().
				filter(actor -> actor.getName().equals("Hero")).findFirst().orElse(null);
		
		if (hero != null &&
				Model.getInstance().checkCollision(this,hero,Direction.ANY)
				) {
			switch(itemType) {
			case SPEED:
								hero.setSpeed(hero.getSpeed()+2);
				
				setActive(false);
				break;
			case BOMBNUM:
			
				hero.setMaxBombs(hero.getMaxBombs()+1);
			
				setActive(false);
				break;
			case BOMBSTRENGHT:
				
				hero.setBombStrenght(hero.getBombStrenght()+2);
				setActive(false);
				break;
			case LIFE:
				setActive(false);
				break;
			default:
				return;
			}
			
			
		}

	}
	
	public enum ItemType{
		SPEED,BOMBNUM,BOMBSTRENGHT,LIFE
	}

	public ItemType getItemType() {
		return itemType;
	}


	
	
}

