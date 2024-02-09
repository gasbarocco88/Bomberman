package main.model.actors;

import main.model.Model;

public class Item extends Actor {
	private ItemType itemType;
	public Item(int posX, int posY, ItemType type) {
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
				System.out.println(hero.getSpeed());
				hero.setSpeed(hero.getSpeed()+2);
				System.out.println("più veloce");
				System.out.println(hero.getSpeed());
				setActive(false);
				break;
			case BOMBNUM:
				System.out.println(hero.getMaxBombs());
				hero.setMaxBombs(hero.getMaxBombs()+1);
				System.out.println("più bombe");
				System.out.println(hero.getMaxBombs());
				setActive(false);
				break;
			case BOMBSTRENGHT:
				System.out.println(hero.getBombStrenght());
				hero.setBombStrenght(hero.getBombStrenght()+2);
				System.out.println("più forza");
				System.out.println(hero.getBombStrenght());
				setActive(false);
				break;
			case LIFE:
				System.out.println("aumenta vite");
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

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
	
	
}
