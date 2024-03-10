package main.model.actors;

import main.controller.AudioManager;
import main.model.Model;

/**
 * Classe che gestisce una bomba generata dal personaggio comandato dall'utente.
 * La bomba a sua volta genera un'esplosione (classe Blast).
 */
public class Bomb extends Actor {

	private long lastTime;
	private float countdown;
	private float waitTime;
	private int strength;
	// private ArrayList<Blast> blast;

	/**
	 * Costruttore della classe Bomb, setta alcune parametri interni quali la il
	 * countdown dell'esplosione, la forza dell'esplosione etc
	 * 
	 * @param posX:     coordinata x della posizione dell'attore
	 * @param posY:     coordinata y della posizione dell'attore
	 * @param strenght: la forza dell'esplosione e.g. quante esplosioni deve
	 *                  generare nelle 4 direzioni
	 */
	public Bomb(int posX, int posY, int strenght) {
		super(posX, posY);
		this.strength = strenght;
		lastTime = System.currentTimeMillis();
		waitTime = 3500f;
		countdown = waitTime;
		setName("Bomb");
		setPriority(2);
		setFrameCounter(0);
		setActive(true);
	}

	/**
	 * Metodo chiamato dall'update del model. Calcola quanto tempo è passato
	 * dall'inizio dell'esplosione per disattivarla allo scadere del waitTime. Allo
	 * scadere del countdown, crea anche le esplosioni nelle 4 direzioni.
	 */
	@Override
	public void update() {
		countdown -= System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (countdown < 0) {
			setActive(false);
			countdown = waitTime;
			Hero h = (Hero) Model.getInstance().getActors().stream().filter(actor -> actor.getName().equals("Hero"))
					.findFirst().orElseThrow();
			h.setBombsCreated(h.getBombsCreated() - 1);
			createBlasts();
			AudioManager.getInstance().play("blast.wav", false);
		}

		updateFrameCounter();
	}

	/**
	 * Metodo che istanzia 4 Blast, una in ciascuna direzione
	 */
	private void createBlasts() {
		Model.getInstance().getActors()
				.add((new Blast(getPosX(), getPosY() - getHeight(), strength - 1, Direction.UP)));
		Model.getInstance().getActors()
				.add((new Blast(getPosX(), getPosY() + getHeight(), strength - 1, Direction.DOWN)));
		Model.getInstance().getActors()
				.add((new Blast(getPosX() - getWidth(), getPosY(), strength - 1, Direction.LEFT)));
		Model.getInstance().getActors()
				.add((new Blast(getPosX() + getWidth(), getPosY(), strength - 1, Direction.RIGHT)));
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public float getCountdown() {
		return countdown;
	}

}
