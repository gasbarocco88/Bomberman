package main.model.actors;

import java.util.Random;

/**
 * Classe che gestisce un attore dinamico, che possiede in più una velocità e
 * una direzione rispetto a un attore generico
 */
public abstract class DynamicActor extends Actor {

	private int speed;
	private Direction direction;

	/**
	 * Costruttore della classe DynamicActor, setta la posizione di coordinate x e y
	 * dell'attore, la sua velocità e direzione di default
	 * 
	 * @param posX: coordinata x della posizione dell'attore
	 * @param posY: coordinata y della posizione dell'attore
	 */
	public DynamicActor(int posX, int posY) {
		super(posX, posY);
		this.speed = 1;
		this.direction = Direction.DOWN;
	}

	/**
	 * Metodo che ritorna una direzione qualsiasi tra UP, DOWN, LEFT, RIGHT.
	 * Utilizzato nella gestione del movimento dei nemici di tipologia Freezer.
	 * 
	 * @return una nuova direzione
	 */
	public Direction chooseRandomDirection() {
		Random ran = new Random();
		int x = ran.nextInt(Direction.values().length - 1);
		Direction direct = Direction.values()[x];
		return direct;
	}

	/**
	 * Metodo che aggiorna la posizione di coordinate (x,y) di un attore che si è
	 * spostato con una certa velocità in una certa direzione; setta adeguatamente
	 * anche il rettangolo dell'attore stesso.
	 */
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
