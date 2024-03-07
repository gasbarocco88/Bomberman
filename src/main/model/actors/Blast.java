package main.model.actors;

import main.model.Model;

/**
 * Classe che gestisce un Blast
 */
public class Blast extends DynamicActor {

	private long lastTime;
	private float countdown;
	private float waitTime;
	private int strength;
	private boolean blastPropagated;

	/**
	 * Costruttore della classe Blast, setta alcune parametri interni quali la
	 * dimensione del rettangolo, il countdown dell'esplosione, la direzione e la
	 * forza dell'esplosione etc
	 * 
	 * @param posX:     coordinata x della posizione dell'attore
	 * @param posY:     coordinata y della posizione dell'attore
	 * @param strenght: la forza dell'esplosione e.g. quante esplosioni "figlie"
	 *                  deve generare nella stessa direzione
	 * @param d:        la direzione dell'esplosione
	 */
	public Blast(int posX, int posY, int strenght, Direction d) {
		super(posX, posY);
		this.strength = strenght;
		lastTime = System.currentTimeMillis();
		waitTime = 1000f;
		countdown = waitTime;
		setDirection(d);
		setBlastPropagated(false);
		setName("Blast");
		setPriority(6);
		setFrameCounter(0);
		setActive(true);
		setRectHeightDimension(28);
		setRectWidthDimension(28);
		setRectangle();
	}

	/**
	 * Metodo chiamato dall'update del model. Calcola quanto tempo è passato
	 * dall'inizio dell'esplosione per disattivarla allo scadere del waitTime.
	 * Esegue il check delle collisioni con i muri e i nemici, e in caso positivo
	 * non viene propagata oltre. Crea a cascata altre esplosioni nelle 4 direzioni,
	 * se lo strenght rimanente è ancora > 0.
	 */
	@Override
	public void update() {
		// countdown per essere disattivato
		countdown -= System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if (countdown < 0) {
			setActive(false);
			countdown = waitTime;
		}
		// check collisioni con muro e nemici per fermare la propagazione
		boolean WallsAndEnemyCollision = Model.getInstance().getActors().stream()
				.filter(actor -> actor.getName() == "Wall" || (actor.getName() == "Enemy"))
				.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.ANY));
		if (WallsAndEnemyCollision) {
			setBlastPropagated(true);
		}

		// creazione blast a cascata nelle 4 direzioni
		if (strength > 0 && !isBlastPropagated()) {
			if (getDirection() == Direction.RIGHT) {
				Model.getInstance().getActors()
						.add((new Blast(getPosX() + Actor.getWidth(), getPosY(), strength - 1, Direction.RIGHT)));
			} else if (getDirection() == Direction.LEFT) {
				Model.getInstance().getActors()
						.add((new Blast(getPosX() - Actor.getWidth(), getPosY(), strength - 1, Direction.LEFT)));
			} else if (getDirection() == Direction.UP) {
				Model.getInstance().getActors()
						.add((new Blast(getPosX(), getPosY() - Actor.getHeight(), strength - 1, Direction.UP)));
			} else {
				Model.getInstance().getActors()
						.add((new Blast(getPosX(), getPosY() + Actor.getHeight(), strength - 1, Direction.DOWN)));
			}
		}
		setBlastPropagated(true);
		updateFrameCounter();
	}

	public boolean isBlastPropagated() {
		return blastPropagated;
	}

	private void setBlastPropagated(boolean blastPropagated) {
		this.blastPropagated = blastPropagated;
	}

	public float getCountdown() {
		return countdown;
	}

}
