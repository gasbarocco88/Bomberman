package main.model.actors;

import java.util.ArrayList;

import main.controller.AudioManager;
import main.model.Model;

public class Bomb extends Actor {

	private long lastTime;
	private float countdown;
	private float waitTime;
	private int strength;
	// private ArrayList<Blast> blast;

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
			AudioManager.getInstance().play("blast.wav",false);
		}

		updateFrameCounter();
	}

	public void createBlasts() {
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
