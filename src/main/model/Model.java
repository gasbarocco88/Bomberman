package main.model;

import java.util.ArrayList;

import main.controller.InputSystem;
import main.model.actors.Actor;
import main.model.actors.Hero;

public class Model {
	public InputSystem inputSystem;
	public int posX = 100;
	public int posY = 100;
	public int speed = 4;
	private ArrayList<Actor> actors = new ArrayList<>();
	private Hero hero;

	public Model(InputSystem inputSystem) {
		this.inputSystem = inputSystem;
		
		hero = new Hero(this.inputSystem);
		actors.add(hero);
	}

	public void update() {
		for(Actor actor : actors) {
			actor.update();
		};		
	}
	
	public Hero getHero() {
		return hero;
	}
}