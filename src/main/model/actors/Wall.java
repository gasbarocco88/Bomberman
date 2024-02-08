package main.model.actors;


public class Wall extends Actor{

	public Wall(int posX, int posY) {
		super(posX, posY);
		setName("Wall");
		setPriority(0);
		setFrameCounter(0);
		setActive(true);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
