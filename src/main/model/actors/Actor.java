package main.model.actors;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Comparator;
import java.util.Objects;

/**
 * Classe astratta che gestisce un generico actor
 */
public abstract class Actor implements Comparable<Actor> {
	private String name;
	private int posY, posX;
	private final static int WIDTH = 32;
	private final static int HEIGHT = 32;
	private int priority;
	private int frameCounter;
	private boolean active;
	private Rectangle rect = new Rectangle();
	private int rectWidthDimension = 28;
	private int rectHeightDimension = 28;

	/**
	 * Costruttore della classe Actor, setta le coordinate della propria posizione e
	 * del proprio rettangolo delle collisioni
	 * 
	 * @param posX: coordinata x della posizione dell'attore
	 * @param posY: coordinata y della posizione dell'attore
	 */
	public Actor(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
		setRectangle();
	}

	/**
	 * metodo astratto da implementare dalle classi figlie concrete, viene chiamato
	 * dall'update del model in modo che ogni attore aggiorni singolarmente le
	 * informazioni che lo riguardano
	 */
	public abstract void update();

	/**
	 * Metodo per aggiornare il counter dei frame dell'attore; aumenta di 1 a ogni
	 * frame e viene riazzerato dopo 24 frame
	 */
	public void updateFrameCounter() {
		setFrameCounter(getFrameCounter() + 1);
		if (getFrameCounter() > 24)
			setFrameCounter(0);
	}

	/**
	 * Metodo che setta le coordinate del rettangolo delle collisioni in modo che
	 * sia sempre centrato rispetto alla posizione dell'attore
	 */
	public void setRectangle() {
		int offsetX = (WIDTH - rectWidthDimension) / 2;
		int offsetY = (HEIGHT - rectHeightDimension) / 2;
		rect.setRect(posX + offsetX, posY + offsetY, rectWidthDimension, rectHeightDimension);
	}

	/**
	 * Metodo per comparare due attori e poterli ordinare in una collezione.
	 */
	public int compareTo(Actor st) {
		if (priority == st.priority)
			return 0;
		else if (priority > st.priority)
			return 1;
		else
			return -1;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, name, posX, posY, priority);
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
		return active == other.active && Objects.equals(name, other.name) && posX == other.posX && posY == other.posY
				&& priority == other.priority;
	}

	public Rectangle getRect() {
		return rect;
	}

	/**
	 * Metodo per calcolare il baricentro della posizione di un attore, Viene
	 * utilizzato nel posizionamento delle bombe, in modo che siano centrate nella
	 * giusta tile.
	 * 
	 * @return le coordinate del baricentro dell'attore
	 */
	public Point getActorMidpoint() {
		int xCoord = getPosX() + (getWidth() / 2);
		int yCoord = getPosY() + (getHeight() / 2);
		return new Point(xCoord, yCoord);
	}

	/**
	 * Ritorna le coordinate x,y del tile della scacchiera di gioco, che contiene il
	 * punto dato in input. Ad esempio, se il tile di gioco è 32x32 e il punto dato
	 * ha coordinate (30,34) viene restituita la coordinata dell'angolo in alto a
	 * sinista del tile della prima colonna e seconda riga, cioè (0,32). Il metodo
	 * viene utilizzato in combinazione con getActorMidpoint per ricavare il punto
	 * nel quale disegnare una bomba appena creata.
	 * 
	 * @param center: il punto della schermata di cui ricavare la posizione del tile
	 *                occupato
	 * @return le coordinate dell'angolo in alto a sinistra del tile
	 */
	public Point getTileCoordinates(Point center) {
		int xCoord = ((int) center.getX() / getWidth()) * getWidth();
		int yCoord = ((int) center.getY() / getHeight()) * getHeight();
		return new Point(xCoord, yCoord);
	}

	public void setActorInTile(Actor a) {
		a.setPosX(posX);
	}

	public int getRectWidthDimension() {
		return rectWidthDimension;
	}

	public void setRectWidthDimension(int rectWidthDimension) {
		this.rectWidthDimension = rectWidthDimension;
	}

	public int getRectHeightDimension() {
		return rectHeightDimension;
	}

	public void setRectHeightDimension(int rectHeightDimension) {
		this.rectHeightDimension = rectHeightDimension;
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

	public int getFrameCounter() {
		return frameCounter;
	}

	public void setFrameCounter(int frameCounter) {
		this.frameCounter = frameCounter;
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
