package main.controller;

import main.model.Model;
import main.view.View;

public class Controller implements Runnable{
	private final Model model;
	private final View view;
	Thread gameThread;
	//InputSystem inputSystem = new InputSystem();
	
	/**
	 * Constructor of Controller Class
	 * @param model
	 * @param view2
	 */
	public Controller(Model model, View view2) {
		this.model = model;
		this.view = view2;
		
	}
	

	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	
	@Override
	public void run() {
		while (true) {
		model.update();
		view.repaint();
		// sincronizzazione
		}
	}




}
