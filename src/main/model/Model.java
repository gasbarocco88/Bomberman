package main.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.swing.text.html.HTMLDocument.Iterator;

import main.controller.AudioManager;
import main.controller.InputSystem;
import main.controller.LevelFactoryText;
import main.model.actors.Actor;
import main.model.actors.Bomb;
import main.model.actors.Direction;
import main.model.actors.Hero;

/**
 * Classe singleton che rappresenta il Model del pattern MVC, istanzia inoltre
 * la classe Game.
 */
public class Model extends Observable {

	static private Model instance;
	private InputSystem inputSystem;
	private CopyOnWriteArrayList<Actor> actors = new CopyOnWriteArrayList<>();
	private Game game;

	/**
	 * Metodo per istanziare la classe Model o ricevere l'istanza già creata
	 * 
	 * @return l'istanza della classe Model
	 */
	static public Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;
	}

	private Model() {
		this.game = new Game();
	}

	/**
	 * Metodo di update del model, che viene utilizzato nel game loop. Inizialmente
	 * vengono eliminati dall'ArrayList interna tutti gli attori che non sono più
	 * attivi (ad esempio i muri o i nemici colpiti da esplosioni); dopodichè viene
	 * chiamato il metodo update su ognuno degli attori rimasti in modo che ciascuno
	 * di essi aggiorni le proprie informazioni specifiche. Infine viene notificato
	 * alla view il cambiamento dello stato del modello secondo il pattern OO.
	 */
	public void update() {
		// eliminiamo gli attori che non sono più attivi (ad esempio le bombe esplose o
		// i muri distrutti
		actors = actors.stream().filter(actor -> actor.isActive())
				.collect(Collectors.toCollection(CopyOnWriteArrayList::new));
		for (int i = 0; i < actors.size(); i++) {
			Actor actor = actors.get(i);
			actor.update();
		}
		setChanged();
		notifyObservers();
		clearChanged();
	}

	/**
	 * Metodo che gestisce la logica delle collisioni tra due attori, a seconda
	 * della direzione dell'attore a. La collisione avviene quando uno dei quattro
	 * angoli del rettangolo dell'attore a è contenuto all'interno del rettangolo
	 * dell'attore b; quali angoli controllare dipende dalla direzione verso cui si
	 * sta muovendo l'attore a.
	 * 
	 * @param a: l'attore del quale stiamo controllando la collisione
	 * @param b: un altro attore qualsiasi nei confronti del quale controlliamo la
	 *           relativa collisione
	 * @param d: la direzione verso cui si sta muovendo l'attore a
	 * @return true se l'attore a collide con l'attore b, altrimenti false.
	 */
	public boolean checkCollision(Actor a, Actor b, Direction d) {
		if (a.equals(b))
			return false;

		// 4 angoli di actor a
		Point upperLeft = new Point((int) a.getRect().getX(), (int) a.getRect().getY());
		Point upperRight = new Point((int) a.getRect().getX() + a.getRectWidthDimension(), (int) a.getRect().getY());
		Point bottomLeft = new Point((int) a.getRect().getX(), (int) a.getRect().getY() + a.getRectHeightDimension());
		Point bottomRight = new Point((int) a.getRect().getX() + a.getRectWidthDimension(),
				(int) a.getRect().getY() + a.getRectHeightDimension());

		switch (d) {
		case UP:
			if (b.getRect().contains(upperLeft) || b.getRect().contains(upperRight))
				return true;
			else
				return false;
		case DOWN:
			if (b.getRect().contains(bottomLeft) || b.getRect().contains(bottomRight))
				return true;
			else
				return false;
		case LEFT:
			if (b.getRect().contains(upperLeft) || b.getRect().contains(bottomLeft))
				return true;
			else
				return false;
		case RIGHT:
			if (b.getRect().contains(bottomRight) || b.getRect().contains(upperRight))
				return true;
			else
				return false;
		case ANY:
			if (b.getRect().contains(upperLeft) || b.getRect().contains(upperRight) || b.getRect().contains(bottomLeft)
					|| b.getRect().contains(bottomRight))
				return true;
			else
				return false;
		default:
			return false;
		}
	}

	/**
	 * Metodo che crea una nuova partita per il player che gli viene passato come
	 * argomento. Carica il primo livello di gioco.
	 * 
	 * @param player: il player per cui creare una nuova partita
	 */
	public void startGame(Player player) {
		this.game = new Game();
		this.game.setPlayer(player);
		loadLevel(1);
		AudioManager.getInstance().switchMusic("musicGame.wav", true);
	}

	/**
	 * Metodo per il caricamento di un livello di gioco, richiamando l'omonimo
	 * metodo della classe LevelFactory.
	 * 
	 * @param level: il livello da caricare
	 */
	public void loadLevel(int level) {
		LevelFactoryText lft = new LevelFactoryText();
		CopyOnWriteArrayList<Actor> actors = lft.loadLevel(level);
		Model.getInstance().setActors(actors);
	}

	/**
	 * Metodo per l'aggiornamento delle statistiche del player. Viene utilizzato
	 * quando è terminata una partita o un livello di gioco
	 * 
	 * @param loose: true quando il player ha terminato le vite e quindi la partita
	 *               è conclusa, altrimenti false.
	 */
	public void updatePlayerPoints(boolean loose) {
		Player p = game.getPlayer();
		if (loose) {
			p.setAccumulatedScore(p.getAccumulatedScore() + game.getScore());
			p.setTotGamesPlayed(p.getTotGamesPlayed() + 1);
			if (game.getScore() > p.getHighestScore()) {
				p.setHighestScore(game.getScore());
			}
		} else {
			if (game.getLevelPlaying() == 2) {
				p.setAccumulatedScore(p.getAccumulatedScore() + game.getScore());
				p.setTotGamesPlayed(p.getTotGamesPlayed() + 1);
				p.setTotGamesWon(p.getTotGamesWon() + 1);
				p.setTotLevelWon(p.getTotLevelWon() + 1);
				if (game.getScore() > p.getHighestScore()) {
					p.setHighestScore(game.getScore());
				}
			} else {
				p.setTotLevelWon(p.getTotLevelWon() + 1);
			}
		}
	}

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