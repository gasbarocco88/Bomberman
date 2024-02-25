package main.model.actors;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import main.model.Model;
import main.model.actors.Item.ItemType;

public class Enemy extends DynamicActor {
	private long lastTime;
	private float countdown;
	private float waitTime;
	private final EnemyType enemyType;

	public Enemy(int posX, int posY, final EnemyType enemyType) {
		super(posX, posY);
		this.enemyType = enemyType;
		setName("Enemy");
		setFrameCounter(0);
		setActive(true);
		setPriorityByEnemyType(enemyType);
		setDirectionAndSpeedByEnemyType(enemyType);
		lastTime = System.currentTimeMillis();
		waitTime = 500f;
		countdown = waitTime;
		setRectHeightDimension(28);
		setRectWidthDimension(26);
		setRectangle();
	}

	@Override
	public void update() {

		// non fa nessun update se il game è in stato di hitted, lastHitted o game over
		if (!Model.getInstance().getGame().isHitted() && !Model.getInstance().getGame().isLastHitted()
				&& !Model.getInstance().getGame().isGameOver()) {

			// collisioni con blast -> enemy disattivato e aggiornamento punteggio
			boolean blastsCollision = Model.getInstance().getActors().stream()
					.filter(actor -> actor.getName() == "Blast")
					.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.ANY));
			if (blastsCollision) {
				setActive(false);
				if (enemyType == EnemyType.FREEZER) {
					Model.getInstance().getGame().setScore(Model.getInstance().getGame().getScore() + 200);
				} else {
					Model.getInstance().getGame().setScore(Model.getInstance().getGame().getScore() + 300);
				}
				return;
			}

			// collisione con bombe, altri nemici e muri
			if (enemyType == EnemyType.GINEW || enemyType == EnemyType.JEETH) {
				ArrayList<Actor> wallsBombsEnemy = Model.getInstance().getActors().stream()
						.filter(actor -> actor.getName() == "Wall" || actor.getName() == "Rock"
								|| actor.getName() == "Bomb" || actor.getName() == "Enemy")
						.collect(Collectors.toCollection(ArrayList::new));
				turnbackLogicMovement(wallsBombsEnemy);

			} else {
				// nemico tipo 3 ha logica collisioni e movimento diverse
				// collide solo con i muri e
				// ogni X secondi cambia di direzione randomicamente
				ArrayList<Actor> walls = Model.getInstance().getActors().stream()
						.filter(actor -> actor.getName() == "Wall" && !((Wall) actor).isDestructible())
						.collect(Collectors.toCollection(ArrayList::new));
				turnbackLogicMovement(walls);
				countdown -= System.currentTimeMillis() - lastTime;
				lastTime = System.currentTimeMillis();
				if (countdown < 0 && getPosX() % getWidth() == 0 && getPosY() % getHeight() == 0) {
					setDirection(chooseRandomDirection());
					countdown = waitTime;
				}
			}
			//si muove sempre perchè ormai ha cambiato direzione
			move();
			updateFrameCounter();

		}
	}
	
	
	private void turnbackLogicMovement(ArrayList<Actor> actors) {
		if (getDirection() == Direction.UP
				&& actors.stream().anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.UP))) {
			setDirection(Direction.DOWN);
		}
		else if (getDirection() == Direction.DOWN
				&& actors.stream().anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.DOWN))) {
			setDirection(Direction.UP);
		} else if (getDirection() == Direction.LEFT
				&& actors.stream().anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.LEFT))) {
			setDirection(Direction.RIGHT);
		} else if (getDirection() == Direction.RIGHT && actors.stream()
				.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.RIGHT))) {
			setDirection(Direction.LEFT);
		}
	}
	
	private void setPriorityByEnemyType(EnemyType enemyType) {
		if (enemyType == EnemyType.FREEZER) {
			setPriority(5);
		} else
			setPriority(4);
	}

	private void setDirectionAndSpeedByEnemyType(EnemyType enemyType) {
		if (enemyType == EnemyType.GINEW) {
			setDirection(Direction.RIGHT);
			setSpeed(getSpeed() + 1);
		} else {
			setDirection(Direction.DOWN);
		}
	}

	public enum EnemyType {
		GINEW, JEETH, FREEZER
	}

	public EnemyType getEnemyType() {
		return enemyType;
	}

}
