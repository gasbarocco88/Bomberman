package main.model;

import main.controller.InputSystem;

public class Model {
	public InputSystem inputSystem = new InputSystem();
	
	public int posX = 100;
	public int posY = 100;
	public int speed = 10;
	
	
public Model() {}

public void update() {
	if (inputSystem.upPressed == true) {
		posY -= speed;
		System.out.println("su");}
	else if (inputSystem.downPressed == true) {
		posY += speed;
		System.out.println("giu");}
	else if (inputSystem.leftPressed == true) {
		posX -= speed;
		System.out.println("sinistra");}
	else if (inputSystem.rightPressed == true) {
		posX += speed;
		System.out.println("destra");}
	}
}