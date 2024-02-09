package main.model.actors;

import java.util.ArrayList;

import main.model.Model;

public class Bomb extends Actor {

    private long lastTime;
    private float countdown;
    private float waitTime;
    private int strength;
    //private ArrayList<Blast> blast;
    

	public Bomb(int posX, int posY, int strenght) {
		super(posX, posY);
		this.strength = strenght;
		lastTime = System.currentTimeMillis();
        waitTime = 2000f;
        countdown = waitTime;
		setName("Bomb");
		setPriority(8);
		setFrameCounter(0);
		setActive(true);
		//blast = new ArrayList<Blast>();
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
		//blast.add(new Blast(getPosX(),getPosY()-Actor.getHeight() , strength-1, Direction.UP));
		//blast.add(new Blast(getPosX(),getPosY()+Actor.getHeight() , strength-1, Direction.DOWN));
		//blast.add(new Blast(getPosX()-Actor.getWidth(),getPosY() , strength-1, Direction.LEFT));
		//blast.add(new Blast(getPosX()+Actor.getWidth(),getPosY() , strength-1, Direction.RIGHT));
		Model.getInstance().getActors().add((new Blast(getPosX(),getPosY()-Actor.getHeight() , strength-1, Direction.UP)));
		System.out.println(strength-1);
		Model.getInstance().getActors().add((new Blast(getPosX(),getPosY()+Actor.getHeight() , strength-1, Direction.DOWN)));
		System.out.println(strength-1);
		Model.getInstance().getActors().add((new Blast(getPosX()-Actor.getWidth(),getPosY() , strength-1, Direction.LEFT)));
		System.out.println(strength-1);
		Model.getInstance().getActors().add((new Blast(getPosX()+Actor.getWidth(),getPosY() , strength-1, Direction.RIGHT)));
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

//	public ArrayList<Blast> getBlast() {
//		return blast;
//	}
//
//	public void setBlast(ArrayList<Blast> blast) {
//		this.blast = blast;
//	}


	

}
