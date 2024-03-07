package main.controller;

import main.model.Model;
import main.view.View;

/**
 * Classe singleton che rappresenta il controller del pattern MVC; istanzia
 * model, view e le altri classi di gestione dell'audio, dell'input e dei
 * players. Contiene al suo interno il game loop del gioco.
 */
public class Controller implements Runnable {
	static private Controller instance;
	private final Model model;
	private final View view;
	private final InputSystem inputSystem;
	long oldTime = System.currentTimeMillis();
	Thread gameThread;

	/**
	 * Metodo per istanziare la classe Controller o ricevere l'istanza già creata
	 * 
	 * @return l'istanza della classe Controller
	 */
	static public Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	/**
	 * Costruttore del Controller, istanzia al suo interno model, view e le altri
	 * classi di gestione dell'audio, dell'input e dei players. Si occupa inoltre di
	 * iscrivere la view come observer del model, secondo il pattern OO.
	 */
	private Controller() {
		inputSystem = new InputSystem();
		AudioManager.getInstance();
		PlayerManager.getInstance();
		model = Model.getInstance();
		model.setInputSystem(inputSystem);
		view = View.getInstance();
		view.getFrame().addKeyListener(inputSystem);
		model.addObserver(view);
	}

	/**
	 * Metodo per istanziare un nuovo thread e chiamare automaticamente il metodo
	 * run dell'interfaccia runnable implementata dal controller
	 */
	public void startThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * Metodo che contiene il game loop del gioco, temporizzato a 60fps. Nel game
	 * loop viene semplicemente chiamato il metodo update del model.
	 */
	@Override
	public void run() {
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		long lastTime = System.nanoTime();
		double delta = 0;
		long now;

		while (gameThread != null) {
			if (Model.getInstance().getGame().isRunning()) {

				now = System.nanoTime();
				delta += (double) (now - lastTime) / timePerTick; // number between 0 and 1
				lastTime = now;
				if (delta >= 1) {
					model.update();
					delta = 0;
				}
			}
		}
	}
}
