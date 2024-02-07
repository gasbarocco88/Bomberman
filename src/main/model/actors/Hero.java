package main.model.actors;

import java.util.Objects;

import main.controller.InputSystem;

public class Hero extends Actor{
	private InputSystem inputSystem;
	private int speed = 4;
	
	public Hero(int x, int y) {
		super(x,y);
	}

	public Hero(InputSystem inputSystem) {
		super(100,100);
		this.inputSystem = inputSystem;
	}



	public void update() {
		if (inputSystem.isUpPressed() == true) {
			setPosY(getPosY() - speed);

		//	System.out.println("wiiiii");
		} else if (inputSystem.isDownPressed() == true) {
			setPosY(getPosY() + speed);

			//System.out.println("wiiiii");
		} else if (inputSystem.isLeftPressed() == true) {
			setPosX(getPosX() - speed);

//			System.out.println("wiiiii");
		} else if (inputSystem.isRightPressed() == true) {
			setPosX(getPosX() + speed);
			System.out.println("wiiiii");
		}
	}
	public Hero update2() {
		if (inputSystem.isUpPressed() == true) {
			setPosY(getPosY() - speed);

		//	System.out.println("wiiiii");
		} else if (inputSystem.isDownPressed() == true) {
			setPosY(getPosY() + speed);

			//System.out.println("wiiiii");
		} else if (inputSystem.isLeftPressed() == true) {
			setPosX(getPosX() - speed);

//			System.out.println("wiiiii");
		} else if (inputSystem.isRightPressed() == true) {
			setPosX(getPosX() + speed);
			System.out.println("wiiiii");
		}
		return this;
	}



	public InputSystem getInputSystem() {
		return inputSystem;
	}

	public void setInputSystem(InputSystem inputSystem) {
		this.inputSystem = inputSystem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(speed);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hero other = (Hero) obj;
		return speed == other.speed;
	}
	
	
	
}