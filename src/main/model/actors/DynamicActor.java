package main.model.actors;

import java.util.Random;

public abstract class DynamicActor extends Actor {

	private int speed;
	private Direction direction;

	public DynamicActor(int posX, int posY) {
		super(posX, posY);
		this.speed = 1;
		this.direction = Direction.DOWN;
	}

	public Direction chooseRandomDirection() {
		Random ran = new Random();
		int x = ran.nextInt(Direction.values().length - 1);
		Direction direct = Direction.values()[x];
		return direct;
	}
	


	public void move() {

		if (getDirection() == Direction.UP) {
			setPosY(getPosY() - getSpeed());

		} else if (getDirection() == Direction.DOWN) {
			setPosY(getPosY() + getSpeed());

		} else if (getDirection() == Direction.LEFT) {
			setPosX(getPosX() - getSpeed());

		} else if (getDirection() == Direction.RIGHT) {
			setPosX(getPosX() + getSpeed());

		}
		setRectangle();

	}
	
	
//	public void change90DegreesDirection(Direction lastDirection) {
//		Random ran = new Random();
//		int x = ran.nextInt(2);
//		
//		switch (lastDirection){
//		case UP:
//			//setPosY(getPosY()-2);
//			if(x==0) {
//				setDirection(Direction.LEFT);
//			}
//			else setDirection(Direction.RIGHT);
//			//setRectangle();
//			return;
//		case DOWN:
//			//setPosY(getPosY()+2);
//			if(x==0) {
//				setDirection(Direction.LEFT);
//			}
//			else setDirection(Direction.RIGHT);
//			//setRectangle();
//			return;
//		case LEFT:
//			//setPosX(getPosX()+2);
//			if(x==0) {
//				setDirection(Direction.UP);
//			}
//			else setDirection(Direction.DOWN);
//			//setRectangle();
//			return;
//		case RIGHT:
//			//setPosX(getPosX()-2);
//			if(x==0) {
//				setDirection(Direction.UP);
//			}
//			else setDirection(Direction.DOWN);
//			//setRectangle();
//			return;
//		default:
//			return;
//		}
//		
//	}

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
