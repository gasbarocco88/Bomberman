package main.model.actors;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import main.controller.InputSystem;
import main.model.Model;

public class Hero extends DynamicActor {
	private int maxBombs = 3;
	private int bombsCreated = 0;
	private int bombStrenght = 3;
	// attack timer
	private long lastTime;
	private long countdown;
	private final long attackCooldown = 400;

	public Hero(int posX, int posY) {
		super(posX, posY);
		setName("Hero");
		setPriority(10);
		setFrameCounter(0);
		setActive(true);
		setRectDimension(26);
	}

	public void update() {

		// non fa nessun update se il game è in stato di hitted, lastHitted o game over
		if (!Model.getInstance().getGame().isHitted() && !Model.getInstance().getGame().isLastHitted()
				&& !Model.getInstance().getGame().isGameOver()) {

			// check collisione blast e nemici
			boolean blastsEnemiesCollision = Model.getInstance().getActors().stream()
					.filter(actor -> actor.getName() == "Blast" || (actor.getName() == "Enemy"))
					.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.ANY));

			if (blastsEnemiesCollision) {
				Model.getInstance().getGame().setHitted(true);
				Model.getInstance().getGame().setLifes(Model.getInstance().getGame().getLifes() - 1);
				// se è l'ultima vita, setta last hitted a true
				if (Model.getInstance().getGame().getLifes() <= 0) {
					Model.getInstance().getGame().setLastHitted(true);
				}

			}

			InputSystem inputSystem = Model.getInstance().getInputSystem();

			// creazione bombe
			if (inputSystem.isSpacePressed() == true) {
				createBomb();
			}

			// tasto pausa
			if (inputSystem.isPausePressed() == true) {
				Model.getInstance().getGame().setRunning(false);
			}

			// movimento e check collisione muri e bombe
			ArrayList<Actor> wallsBombs = Model.getInstance().getActors().stream()
					.filter(actor -> actor.getName() == "Wall"
							|| (actor.getName() == "Bomb" && ((Bomb) actor).getCountdown() < 1500))
					.collect(Collectors.toCollection(ArrayList::new));

			if (inputSystem.isUpPressed() == true) {
				setDirection(Direction.UP);
				if (wallsBombs.stream()
						.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.UP)))
					return;
				move();
			} else if (inputSystem.isDownPressed() == true) {
				setDirection(Direction.DOWN);
				if (wallsBombs.stream()
						.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.DOWN)))
					return;
				move();
			} else if (inputSystem.isLeftPressed() == true) {
				setDirection(Direction.LEFT);
				if (wallsBombs.stream()
						.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.LEFT)))
					return;
				move();
			} else if (inputSystem.isRightPressed() == true) {
				setDirection(Direction.RIGHT);
				if (wallsBombs.stream()
						.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.RIGHT)))
					return;
				move();
			}

			updateFrameCounter();
		}
	}

	private void createBomb() {
		countdown -= System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (countdown < 0) {
			if (bombsCreated < maxBombs) {
				placeBomb(bombStrenght);
			}
			countdown = attackCooldown;
		}
	}

	private void placeBomb(int bombStrenght) {
		Point p = getTileCoordinates(getActorMidpoint());
		Model.getInstance().getActors().add(new Bomb((int) p.getX(), (int) p.getY(), bombStrenght));
		bombsCreated++;
	}

	// getters and setters
	public int getBombsCreated() {
		return bombsCreated;
	}

	public int getMaxBombs() {
		return maxBombs;
	}

	public void setMaxBombs(int maxBombs) {
		this.maxBombs = maxBombs;
	}

	public int getBombStrenght() {
		return bombStrenght;
	}

	public void setBombStrenght(int bombStrenght) {
		this.bombStrenght = bombStrenght;
	}

	public void setBombsCreated(int bombsCreated) {
		this.bombsCreated = bombsCreated;
	}

}