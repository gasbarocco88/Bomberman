package main.model.actors;

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
}
