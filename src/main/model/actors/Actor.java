package main.model.actors;

import java.util.Objects;

public abstract class Actor {
	private int posY, posX;

	public Actor(int posY, int posX) {
		super();
		this.posY = posY;
		this.posX = posX;
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

	public abstract void update();

	public abstract Actor update2();

	@Override
	public int hashCode() {
		return Objects.hash(posX, posY);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return posX == other.posX && posY == other.posY;
	}
	
	
}
