package main.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.swing.text.html.HTMLDocument.Iterator;

import main.controller.InputSystem;
import main.model.actors.Actor;
import main.model.actors.Bomb;
import main.model.actors.Direction;
import main.model.actors.Hero;

public class Model extends Observable {

	static private Model instance;
	private InputSystem inputSystem;
	//private ArrayList<Actor> actors = new ArrayList<>();
	private CopyOnWriteArrayList<Actor> actors = new CopyOnWriteArrayList<>();
	public int num = 0;

	static public Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;

	}

	private Model() {
	}


	public void update() {

		// prendo blasts dei blast e li appendo in actors e rimuovo dalla lista interna
			
		// prendo blasts dei bomb e li appendo in actors e rimuovo dalla lista interna
//		ArrayList<Actor> bombs = actors.stream().filter(actor -> actor.getName().equals("Bomb")).collect(Collectors 
//				.toCollection(ArrayList::new));
//		if(!bombs.isEmpty()) {
//			for(Actor bomb : bombs) {
//				actors.addAll(((Bomb) bomb).getBlast());
//				((Bomb) bomb).getBlast().clear();
//			}
//		}
//		
		// aggiungo a actors le bombe create da hero, prese dalla sua lista interna; quest'ultima viene svuotata.
//		Hero h = (Hero) actors.stream().filter(actor -> actor.getName().equals("Hero")).findFirst().orElse(null);
//		if (h != null) {
//			ArrayList<Bomb> bombsCreated = h.getBombs();
//			if (!bombsCreated.isEmpty()) {
//				actors.addAll(bombsCreated);
//				h.getBombs().clear();
//			}
//		}
		//eliminiamo gli attori che non sono più attivi (ad esempio le bombe esplose o i muri distrutti
		actors = actors.stream().filter(actor->actor.isActive()).collect(Collectors 
				.toCollection(CopyOnWriteArrayList::new));
		
		
		for (int i = 0; i < actors.size(); i++) {
			Actor actor = actors.get(i);
			actor.update();
		}
	
		setChanged();
		notifyObservers(actors);

		clearChanged();
	}

	
	public boolean checkCollision(Actor a, Actor b, Direction d) {
		if(a.equals(b)) return false;
		
		// 4 angoli di actor a
		Point upperLeft = new Point((int)a.getRect().getX(),
									(int) a.getRect().getY());
		Point upperRight = new Point((int)a.getRect().getX()+a.getRectDimension(),
									(int) a.getRect().getY());
		Point bottomLeft = new Point((int)a.getRect().getX(),(int) a.getRect().getY()+a.getRectDimension());
		Point bottomRight = new Point((int)a.getRect().getX()+a.getRectDimension(),(int) a.getRect().getY()+a.getRectDimension());
		
		switch(d) {
		case UP:
			if (b.getRect().contains(upperLeft) ||b.getRect().contains(upperRight))
				return true;
			else return false;
		case DOWN:
			if (b.getRect().contains(bottomLeft) ||b.getRect().contains(bottomRight))
				return true;
			else return false;
		case LEFT:
			if (b.getRect().contains(upperLeft) ||b.getRect().contains(bottomLeft))
				return true;
			else return false;
		case RIGHT:
			if (b.getRect().contains(bottomRight) ||b.getRect().contains(upperRight))
				return true;
			else return false;
		case ANY:
			if	(b.getRect().contains(upperLeft) ||b.getRect().contains(upperRight) ||
					b.getRect().contains(bottomLeft)|| b.getRect().contains(bottomRight))
			return true;
			else return false;
		default:
			return false;
		
		
		}
		
		
	}
	
	
	public InputSystem getInputSystem() {
		return inputSystem;
	}

	public void setInputSystem(InputSystem inputSystem) {
		this.inputSystem = inputSystem;
	}

	
	public CopyOnWriteArrayList<Actor> getActors() {
		return actors;
	}

	public void setActors(CopyOnWriteArrayList<Actor> actors) {
		this.actors = actors;
	}

	
}