package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.stream.Collectors;

import javax.swing.text.html.HTMLDocument.Iterator;

import main.controller.InputSystem;
import main.model.actors.Actor;
import main.model.actors.Bomb;
import main.model.actors.Hero;

public class Model extends Observable {

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
		actors = actors.stream().filter(actor->actor.isActive()).collect(Collectors 
                .toCollection(ArrayList::new));

		// prendo blasts dei blast e li appendo in actors e rimuovo dalla lista interna
		// prendo blasts dei bomb e li appendo in actors e rimuovo dalla lista interna
		
		// prendo bomb di hero e le appendo in actors e rimuovo dalla lista interna.
		Hero h = (Hero) actors.stream().filter(actor -> actor.getName().equals("Hero")).findFirst().orElse(null);
		if (h != null) {
			ArrayList<Bomb> bombs = h.getBombs();
			if (!bombs.isEmpty()) {
				actors.addAll(bombs);
				h.getBombs().clear();
			}
		}

		for (int i = 0; i < actors.size(); i++) {
			Actor actor = actors.get(i);
			actor.update();
		}
		// Collections.sort(actors);
//		for(Actor actor : actors) {
//			actor.update();
//			//rimuovi gli inattivi
//		
//		};
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