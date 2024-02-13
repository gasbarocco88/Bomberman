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
import main.controller.LevelFactoryText;
import main.model.actors.Actor;
import main.model.actors.Bomb;
import main.model.actors.Direction;
import main.model.actors.Hero;

public class Model extends Observable {

	static private Model instance;
	private InputSystem inputSystem;
	private CopyOnWriteArrayList<Actor> actors = new CopyOnWriteArrayList<>();
	private Game game;

	static public Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;

	}

	private Model() {
		this.game = new Game();
	}


	public void update() {

		//eliminiamo gli attori che non sono più attivi (ad esempio le bombe esplose o i muri distrutti
		actors = actors.stream().filter(actor->actor.isActive()).collect(Collectors 
				.toCollection(CopyOnWriteArrayList::new));
		
		
		for (int i = 0; i < actors.size(); i++) {
			Actor actor = actors.get(i);
			actor.update();
		}
	
		setChanged();
		notifyObservers();
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
	
	public void startGame(Player player, String avatar) {
		this.game = new Game();
		this.game.setPlayer(player);
		this.game.getPlayer().setAvatar(avatar);
		loadLevel(1);
	}
	
	public void loadLevel(int level) {
		LevelFactoryText lft = new LevelFactoryText();
		CopyOnWriteArrayList<Actor> actors = lft.loadLevel(level);
    	Model.getInstance().setActors(actors);
	}
	
	public void changeLevel() {
		//game.getPlayer().levelwon + 1
		//if this level == 2 -> player partita vinta +1 e torna al menu
		
		// mette gioco in pausa ??
		
		// fa partire transizione hai vinto
		
		// resetta actor e passa al livello dopo loadLevel(this level + 1)
		
		// se è ultimo livello, aggiorna le stats di player e dumpa nuovo file?
		
	}
	
	///serve?
//	public void loadLevel() {
//		LevelFactoryText lft = new LevelFactoryText();
//		CopyOnWriteArrayList<Actor> actors = lft.loadLevel(this.game.getLevel());
//    	Model.getInstance().setActors(actors);
//	}
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
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