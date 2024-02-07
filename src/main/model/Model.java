package main.model;

import java.util.ArrayList;
import java.util.Observable;

import main.controller.InputSystem;
import main.model.actors.Actor;
import main.model.actors.Hero;

public class Model extends Observable{
	
	static private Model instance;
	private InputSystem inputSystem;
	private ArrayList<Actor> actors = new ArrayList<>();
	private Hero hero;
	public int num = 0;
	
	static public Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;
				
	}
	private Model() {
	}
	
	
	public InputSystem getInputSystem() {
		return inputSystem;
	}
	public void setInputSystem(InputSystem inputSystem) {
		this.inputSystem = inputSystem;
	}
//	public Model(InputSystem inputSystem) {
//		this.inputSystem = inputSystem;
//		
//		hero = new Hero(this.inputSystem);
//		actors.add(hero);
//	}

	public void update() {
		for(Actor actor : actors) {
			actor.update();
		};
//		if (inputSystem.isUpPressed() == true) {
//			num++;
//			System.out.println(num);
//		}
		setChanged();
		notifyObservers(actors);

		clearChanged();
	}
	public ArrayList<Actor> getActors() {
		return actors;
	}
	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}	
	public Hero getHero() {
		return hero;
	}
}