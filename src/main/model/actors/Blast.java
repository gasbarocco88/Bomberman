package main.model.actors;

import java.util.ArrayList;
import java.util.stream.Collectors;

import main.model.Model;

public class Blast extends DynamicActor {

	private long lastTime;
	private float countdown;
	private float waitTime;
	private int strength;
	private boolean blastPropagated;

	public Blast(int posX, int posY, int strenght, Direction d) {
		super(posX, posY);
		this.strength = strenght;
		lastTime = System.currentTimeMillis();
		waitTime = 2000f;
		countdown = waitTime;
		setDirection(d);
		setBlastPropagated(false);
		setName("Blast");
		setPriority(9);
		setFrameCounter(0);
		setActive(true);
		System.out.println(strength);
	}

	@Override
	public void update() {
		System.out.println("attivo");
		// countdown per essere disattivato
		countdown -= System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if (countdown < 0) {
			System.out.println("disattivo");
			setActive(false);
			countdown = waitTime;
		}

		// check collisioni con muro e nemici per fermare la propagazione
		boolean WallsAndEnemyCollision = Model.getInstance().getActors().stream()
				.filter(actor -> actor.getName() == "Wall")
				.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.ANY));
		if (WallsAndEnemyCollision) {
			setBlastPropagated(true);
		}
		//

		// creazione blast a cascata
		if (strength > 0 && !isBlastPropagated()) {
			System.out.println("forza");

			if (getDirection() == Direction.RIGHT) {
				System.out.println("destra bomba");
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

	}

	public boolean isBlastPropagated() {
		return blastPropagated;
	}

	public void setBlastPropagated(boolean blastPropagated) {
		this.blastPropagated = blastPropagated;
	}

}
