package main.model.actors;

import java.util.Comparator;
import java.util.Objects;

public abstract class Actor implements Comparable<Actor>{
	private String name;
	private int posY, posX;
	private int priority;
	private Direction direction;
	private int speed;
	private int frameCounter;
	private boolean active;
	


	public Actor(int posY, int posX) {
		super();
		this.posY = posY;
		this.posX = posX;
	}

	public abstract void update();

	public int compareTo(Actor st){  
		if(priority==st.priority)  
		return 0;  
		else if(priority>st.priority)  
		return 1;  
		else  
		return -1;  }
		
	public enum Direction {
	UP, DOWN, LEFT, RIGHT

}

	public int getFrameCounter() {
		return frameCounter;
	}

	public void setFrameCounter(int frameCounter) {
		this.frameCounter = frameCounter;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


}
