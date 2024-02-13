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
		setPriority(12);
		setFrameCounter(0);
		setActive(true);
		setDirectionAndSpeedByEnemyType(enemyType);

		// setDirection(chooseRandomDirection());
		lastTime = System.currentTimeMillis();
		waitTime = 500f;
		countdown = waitTime;

	}

	@Override
	public void update() {

		if (!Model.getInstance().getGame().isHitted() && !Model.getInstance().getGame().isLastHitted() && !Model.getInstance().getGame().isGameOver()) {

			// collisioni con blast -> muori
			boolean blastsCollision = Model.getInstance().getActors().stream()
					.filter(actor -> actor.getName() == "Blast")
					.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.ANY));
			if (blastsCollision) {
				System.out.println("presooooooooooooo");
				setActive(false);
				if (enemyType == EnemyType.TIPO3) {
					Model.getInstance().getGame().setPoint(Model.getInstance().getGame().getPoint() + 200);
				} else {
					Model.getInstance().getGame().setPoint(Model.getInstance().getGame().getPoint() + 300);
				}

				return;
			}

			if (enemyType == EnemyType.TIPO1 || enemyType == EnemyType.TIPO2) {

				ArrayList<Actor> wallsBombsEnemy = Model.getInstance().getActors().stream()
						.filter(actor -> actor.getName() == "Wall" || actor.getName() == "Rock"
								|| actor.getName() == "Bomb" || actor.getName() == "Enemy")
						.collect(Collectors.toCollection(ArrayList::new));

				turnbackLogicMovement(wallsBombsEnemy);

			} else {

				// se collide cambia direzione all'indietro
				ArrayList<Actor> walls = Model.getInstance().getActors().stream()
						.filter(actor -> actor.getName() == "Wall" && !((Wall) actor).isDestructible())
						.collect(Collectors.toCollection(ArrayList::new));
				turnbackLogicMovement(walls);
				// ogni mezzo secondo sceglie una direzione
				countdown -= System.currentTimeMillis() - lastTime;
				lastTime = System.currentTimeMillis();
				if (countdown < 0 && getPosX() % getWidth() == 0 && getPosY() % getHeight() == 0) {
					setDirection(chooseRandomDirection());
					countdown = waitTime;
				}
//			// se collide con muri torna indietro
//			ArrayList<Actor> walls = Model.getInstance().getActors().stream()
//					.filter(actor -> actor.getName() == "Wall" && !((Wall) actor).isDestructible())
//					.collect(Collectors.toCollection(ArrayList::new));
//			turnbackLogicMovement(walls);

//			
//			if ( Model.getInstance().getActors().stream()
//					.filter(actor -> actor.getName() == "Wall" && !((Wall) actor).isDestructible())
//					.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.ANY))) {
//				return;
//			};
			}
			move();
			setFrameCounter(getFrameCounter() + 1);
			if (getFrameCounter() > 24)
				setFrameCounter(0);

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

//	
//	private void turn90degrees(ArrayList<Actor> actors) {
//		Random ran = new Random();
//		int x = ran.nextInt(2);
//		if (getDirection() == Direction.UP
//				&& actors.stream().anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.UP))) {
//			if(x==0) {
//				setDirection(Direction.LEFT);
//			}
//			else setDirection(Direction.RIGHT);
//			setPosY(getPosY());
//		}
//
//		else if (getDirection() == Direction.DOWN
//				&& actors.stream().anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.DOWN))) {
//			if(x==0) {
//				setDirection(Direction.LEFT);
//			}
//			else setDirection(Direction.RIGHT);
//			setPosY(getPosY());
//			System.out.println("collide");
//		} else if (getDirection() == Direction.LEFT
//				&& actors.stream().anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.LEFT))) {
//			if(x==0) {
//				setDirection(Direction.UP);
//			}
//			else setDirection(Direction.DOWN);
//			setPosX(getPosX());
//		} else if (getDirection() == Direction.RIGHT && actors.stream()
//				.anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.RIGHT))) {
//			if(x==0) {
//				setDirection(Direction.UP);
//			}
//			else setDirection(Direction.DOWN);
//			setPosX(getPosX());
//		}
//		setRectangle();
//
//	}
	private void setPriorityByEnemyType(EnemyType enemyType) {
		if (enemyType == EnemyType.TIPO3) {
			setPriority(5);
		} else
			setPriority(4);
	}

	private void setDirectionAndSpeedByEnemyType(EnemyType enemyType) {
		if (enemyType == EnemyType.TIPO1) {
			setDirection(Direction.RIGHT);
			setSpeed(getSpeed() + 1);
		} else {
			setDirection(Direction.DOWN);
		}
	}

	public enum EnemyType {
		TIPO1, TIPO2, TIPO3
	}

	public EnemyType getEnemyType() {
		return enemyType;
	}

}
