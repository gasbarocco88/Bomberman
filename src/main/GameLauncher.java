package main;


import main.controller.Controller;
import main.model.Model;
import main.view.View;

public class GameLauncher {

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.startThread();

	}

}
