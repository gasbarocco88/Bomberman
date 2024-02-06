package main.model.actors;

import main.controller.InputSystem;

public class Hero extends Actor{
	private InputSystem inputSystem;
	private int speed = 4;
	
	

	public Hero(InputSystem inputSystem) {
		super(100,100);
		this.inputSystem = inputSystem;
	}



	public void update() {
		if (inputSystem.isUpPressed() == true) {
			setPosY(getPosY() - speed);

			System.out.println("wiiiii");
		} else if (inputSystem.isDownPressed() == true) {
			setPosY(getPosY() + speed);

			System.out.println("wiiiii");
		} else if (inputSystem.isLeftPressed() == true) {
			setPosX(getPosX() - speed);

			System.out.println("wiiiii");
		} else if (inputSystem.isRightPressed() == true) {
			setPosX(getPosX() + speed);
			System.out.println("wiiiii");
		}
	}

}