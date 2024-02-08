package main.model.actors;

import main.model.Model;

public class Bomb extends Actor {

    private long lastTime;
    private float countdown;
    private float waitTime;
    private int strength;

	public Bomb(int posX, int posY, int strenght) {
		super(posX, posY);
		this.strength = strenght;
		lastTime = System.currentTimeMillis();
        waitTime = 2000f;
        countdown = waitTime;
		setName("Bomb");
		setPriority(9);
		setFrameCounter(0);
		setActive(true);
	}

	@Override
	public void update() {

        countdown -= System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(countdown < 0 ) {
            setActive(false);
            countdown = waitTime;
            Hero h = (Hero) Model.getInstance().getActors().stream().
            		filter(actor -> actor.getName().equals("Hero")).
            		findFirst().orElseThrow();
            h.setBombsCreated(h.getBombsCreated()-1);
            createBlasts();
        }

	}
	public void createBlasts() {
		
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}


	

}
