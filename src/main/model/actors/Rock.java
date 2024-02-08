package main.model.actors;

import main.model.actors.Actor.Direction;

public class Rock extends Actor {

	public Rock(int posY, int posX) {
		super(posY, posX);
		setDirection(Direction.DOWN);
		setName("Wall");
		setPriority(0);
		setSpeed(0);
		setFrameCounter(0);
		setActive(true);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
