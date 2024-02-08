package main.model.actors;

import java.util.ArrayList;
import java.util.Objects;

import main.controller.InputSystem;
import main.model.Model;

public class Hero extends Actor {
	private InputSystem inputSystem;
	private int maxBombs = 3;
	private int bombsCreated = 0;
	private int bombStrenght = 1;
	// AttackTimer
	private long lastTime;
	private long countdown; // countdown
	private final long attackCooldown = 400; // wait time
	private ArrayList<Bomb> bombs;

	public Hero(int x, int y) {
		super(x, y);
		setDirection(Direction.DOWN);
		setName("Hero");
		setPriority(10);
		setSpeed(4);
		setFrameCounter(0);
		setActive(true);
		bombs = new ArrayList<Bomb>();
	}

	public Hero(InputSystem inputSystem) {
		super(100, 100);
		this.inputSystem = inputSystem;
	}

	public void update() {
		
		//check collisione con blast o nemici e se si muori
		
		//check collisione con muri e se si non ti muovi
		
		
		if (inputSystem.isUpPressed() == true) {
			setPosY(getPosY() - getSpeed());
			setDirection(Direction.UP);

			System.out.println("suuu");
		} else if (inputSystem.isDownPressed() == true) {
			setPosY(getPosY() + getSpeed());
			setDirection(Direction.DOWN);

			System.out.println("giùùùù");
		} else if (inputSystem.isLeftPressed() == true) {
			setPosX(getPosX() - getSpeed());
			setDirection(Direction.LEFT);
			System.out.println("leeeeft");
		} else if (inputSystem.isRightPressed() == true) {
			setPosX(getPosX() + getSpeed());
			setDirection(Direction.RIGHT);
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
			if (bombsCreated< maxBombs){
			System.out.println("boooooooooooooooooooooom");
			bombs.add(new Bomb( getPosX(),getPosY(), bombStrenght));			
			bombsCreated++;
			}

			countdown = attackCooldown;

		} else {
			System.out.println("aspetta");
		}

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