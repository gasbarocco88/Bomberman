package main.model.actors;


public abstract class DynamicActor  extends Actor {
	
	private int speed;
	private Direction direction;
	
	
	public DynamicActor(int posX, int posY) {
		super(posX, posY);
		this.speed = 2;
		this.direction = Direction.DOWN;		
	}

	
	
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
