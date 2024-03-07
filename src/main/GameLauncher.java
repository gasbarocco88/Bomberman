package main;

import main.controller.Controller;

/**
 * Questa classe funge da punto di accesso del programma, contenendone il metodo
 * main. Istanzia il controller e fa partire il thread.
 */
public class GameLauncher {

	public static void main(String[] args) {
		Controller controller = Controller.getInstance();
		controller.startThread();
	}
}
