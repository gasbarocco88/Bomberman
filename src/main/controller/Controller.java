package main.controller;

import main.model.Model;
import main.view.View;

public class Controller implements Runnable {
	private final Model model;
	private final View view;
	private final InputSystem inputSystem;
	long oldTime = System.currentTimeMillis();
	Thread gameThread;

	/**
	 * Constructor of Controller Class
	 */
	public Controller() {
		inputSystem = new InputSystem();
		this.model = new Model(inputSystem);
		this.view = new View(model);
		view.getFrame().addKeyListener(inputSystem);

	}

	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		long lastTime = System.nanoTime();
		double delta = 0;
		long now;

		while (gameThread != null) {
			now = System.nanoTime();
			delta += (double) (now - lastTime) / timePerTick; // number between 0 and 1
			lastTime = now;
			if (delta >= 1) {
				model.update();
				view.repaint();
				delta=0;
			}
		}
	}
}
