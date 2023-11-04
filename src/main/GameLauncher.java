package main;


import main.controller.Controller;
import main.model.Model;
import main.view.View;

public class GameLauncher {

	public static void main(String[] args) {
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model, view);
		controller.startGame();

	}

}
