package main.model.actors;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import main.controller.InputSystem;
import main.model.Model;

public class Hero extends DynamicActor {
	private InputSystem inputSystem;
	private int maxBombs = 3;
	private int bombsCreated = 0;
	private int bombStrenght = 1;
	// AttackTimer
	private long lastTime;
	private long countdown; // countdown
	private final long attackCooldown = 400; // wait time
	private ArrayList<Bomb> bombs;

	public Hero(int posX, int posY) {
		super(posX, posY);
		setName("Hero");
		setPriority(10);
		setFrameCounter(0);
		setActive(true);
		bombs = new ArrayList<Bomb>();
	}

	public Hero(InputSystem inputSystem) {
		super(100, 100);
		this.inputSystem = inputSystem;
	}

	public void update() {

		// check collisione con blast o nemici e se si muori

		// check collisione con muri e se si non ti muovi
		
		ArrayList <Actor> wallsAndRocks= Model.getInstance().getActors().stream().
				filter(actor -> actor.getName()=="Wall" ||actor.getName()=="Rock")
				.collect(Collectors .toCollection(ArrayList::new));

		if (inputSystem.isUpPressed() == true) {
			setDirection(Direction.UP);
//			for(Actor a: Model.getInstance().getActors()) {
//				if(!a.equals(this) && checkCollisionUP(a)) return;
//			}
//			
			if (wallsAndRocks.stream().anyMatch(actor -> !actor.equals(this)&& checkCollisionUP(actor))) return;
			setPosY(getPosY() - getSpeed());
			setRectangle();
			System.out.println("suuu");
		} else if (inputSystem.isDownPressed() == true) {
			setDirection(Direction.DOWN);
			if (wallsAndRocks.stream().anyMatch(actor -> !actor.equals(this)&& checkCollisionDowm(actor))) return;
			setPosY(getPosY() + getSpeed());
			setRectangle();
			System.out.println("giùùùù");
		} else if (inputSystem.isLeftPressed() == true) {
			setDirection(Direction.LEFT);
			if (wallsAndRocks.stream().anyMatch(actor -> !actor.equals(this)&& checkCollisionLeft(actor))) return;
			setPosX(getPosX() - getSpeed());
			setRectangle();
			System.out.println("leeeeft");
		} else if (inputSystem.isRightPressed() == true) {
			setDirection(Direction.RIGHT);
			if (wallsAndRocks.stream().anyMatch(actor -> !actor.equals(this)&& checkCollisionRight(actor))) return;
			setPosX(getPosX() + getSpeed());
			setRectangle();
			System.out.println("riiighttt");
		} else if (inputSystem.isSpacePressed() == true) {

			createBomb();
		}
		setFrameCounter(getFrameCounter() + 1);
		if (getFrameCounter() > 24)
			setFrameCounter(0);

	}

	private void createBomb() {
		countdown -= System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		System.out.println(countdown);
		System.out.println(bombsCreated);
		if (countdown < 0) {
			if (bombsCreated < maxBombs) {
				System.out.println("boooooooooooooooooooooom");
				bombs.add(new Bomb(getPosX(), getPosY(), bombStrenght));
				bombsCreated++;
			}
			countdown = attackCooldown;
		} else {
			System.out.println("aspetta");
		}
	}
	
	private boolean checkCollisionUP(Actor a) {
		//check angolo a sinistra in alto e check angolo a destra in alto
		if (a.getRect().contains(getRect().getX(),getRect().getY()) ||
				a.getRect().contains(getRect().getX()+getRectDimension(),getRect().getY())
				)
			return true;
		return false;
	}
	private boolean checkCollisionDowm(Actor a) {
		// check angolo a sinistra in basso e check angolo a destra in basso
		if (a.getRect().contains(getRect().getX(),getRect().getY()+getRectDimension()) ||
				a.getRect().contains(getRect().getX()+getRectDimension(),getRect().getY()+getRectDimension())
				)
			return true;
		return false;
	}
	private boolean checkCollisionRight(Actor a) {
		// check angolo a destra in alto e check angolo a destra in basso
		if (a.getRect().contains(getRect().getX()+getRectDimension(),getRect().getY()) ||
				a.getRect().contains(getRect().getX()+getRectDimension(),getRect().getY()+getRectDimension())
				)
			return true;
		return false;
	}
	private boolean checkCollisionLeft(Actor a) {
		//check angolo a sinistra in alto e check angolo a sinistra in basso
		if (a.getRect().contains(getRect().getX(),getRect().getY()) ||
				a.getRect().contains(getRect().getX(),getRect().getY()+getRectDimension())
				)
			return true;
		return false;
	}
	
	
	
	

	public int getBombsCreated() {
		return bombsCreated;
	}

	public ArrayList<Bomb> getBombs() {
		return bombs;
	}

	public void setBombs(ArrayList<Bomb> bombs) {
		this.bombs = bombs;
	}

	public void setBombsCreated(int bombsCreated) {
		this.bombsCreated = bombsCreated;
	}

	public InputSystem getInputSystem() {
		return inputSystem;
	}

	public void setInputSystem(InputSystem inputSystem) {
		this.inputSystem = inputSystem;
	}

}