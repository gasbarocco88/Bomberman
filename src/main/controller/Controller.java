package main.controller;

import main.model.Model;
import main.view.View;

public class Controller implements Runnable {
	private final Model model;
	private final View view;
	private final InputSystem inputSystem;
	//private final AudioManager audioManager;
	long oldTime = System.currentTimeMillis();
	Thread gameThread;


	public Controller() {
		inputSystem = new InputSystem();
		AudioManager.getInstance();
		PlayerManager.getInstance();
		model = Model.getInstance();
		model.setInputSystem(inputSystem);
		view = View.getInstance();
		view.getFrame().addKeyListener(inputSystem);
		model.addObserver(view);
	}
	
	public void startThread() {
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
