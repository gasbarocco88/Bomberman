package main.model.actors;

import java.awt.Point;
import java.util.ArrayList;
import java.util.stream.Collectors;

import main.controller.AudioManager;
import main.controller.InputSystem;
import main.controller.PlayerManager;
import main.model.Model;

/**
 * Classe che gestisce Hero, ovvero il personaggio comandato dall'utente. Hero
 * può muoversi nelle 4 direzioni previste, può creare bombe e raccogliere
 * potenziamenti (classe Item)
 */
public class Hero extends DynamicActor {
	private int maxBombs = 1;
	private int bombsCreated = 0;
	private int bombStrenght = 2;
	// attack timer
	private long lastTime;
	private long countdown;
	private final long attackCooldown = 400;

	/**
	 * Costruttore della classe Hero, setta alcune parametri interni quali la
	 * dimensione del rettangolo delle collisioni, il frame counter etc
	 * 
	 * @param posX: coordinata x della posizione dell'attore
	 * @param posY: coordinata y della posizione dell'attore
	 */
	public Hero(int posX, int posY) {
		super(posX, posY);
		setName("Hero");
		setPriority(10);
		setFrameCounter(0);
		setActive(true);
		setRectHeightDimension(22);
		setRectWidthDimension(16);
		setRectangle();
	}

	/**
	 * Metodo chiamato dall'update del model. Esegue il check delle collisioni con
	 * nemici e esplosioni in modo da gestire la perdita di una vita o della
	 * partita. Esegue il check delle collisioni con i muri in modo da impedirne il
	 * movimento. Gestisce gli input in modo da poter spostare il personaggio e
	 * fargli piazzare la bomba. Aggiorna il frame counter per le animazioni.
	 */
	public void update() {

		// non fa nessun update se il game è in stato di hitted, lastHitted o game over
		if (!Model.getInstance().getGame().isHitted() && !Model.getInstance().getGame().isLastHitted()
				&& !Model.getInstance().getGame().isGameOver() && !Model.getInstance().getGame().isLevelFinish()) {

			// check collisione blast e nemici
			boolean blastsEnemiesCollision = Model.getInstance().getActors().stream()
					.filter(actor -> actor.getName() == "Blast" || (actor.getName() == "Enemy"))
					.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.ANY));

			if (blastsEnemiesCollision) {
				Model.getInstance().getGame().setHitted(true);
				Model.getInstance().getGame().setLifes(Model.getInstance().getGame().getLifes() - 1);
				// se è l'ultima vita, setta last hitted a true
				if (Model.getInstance().getGame().getLifes() <= 0) {
					AudioManager.getInstance().play("gameOver.wav", false);

					Model.getInstance().getGame().setLastHitted(true);
					Model.getInstance().getGame().setHitted(false);
					Model.getInstance().updatePlayerPoints(true);
					PlayerManager.getInstance().updatePlayerStats(Model.getInstance().getGame().getPlayer());
				} else {
					AudioManager.getInstance().play("hitted.wav", false);
					// Model.getInstance().loadLevel(Model.getInstance().getGame().getLevelPlaying());
				}

			}

			InputSystem inputSystem = Model.getInstance().getInputSystem();

			// creazione bombe
			if (inputSystem.isSpacePressed() == true) {
				createBomb();
				AudioManager.getInstance().play("bomb.wav", false);
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