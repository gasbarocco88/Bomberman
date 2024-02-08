package main.model.actors;


public class Rock extends Actor {

	public Rock(int posX, int posY) {
		super(posX, posY);
		setName("Rock");
		setPriority(0);
		setFrameCounter(0);
		setActive(true);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
