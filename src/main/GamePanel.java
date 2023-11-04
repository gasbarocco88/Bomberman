package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Currency;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	final int screenWidth = 768;
	final int screenHeight = 576;
	Thread gameThread;
	InputSystem inputSystem = new InputSystem();
	
	int posX = 100;
	int posY = 100;
	int speed = 10;
	
	//da fare singleton
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(inputSystem);
		this.setFocusable(true); //serve a mettere il famePanel focused per ricevere gli input
		
	}
	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		
		double renderInterval = 1_000_000_000/60;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/renderInterval;
			lastTime = currentTime;
			
			if (delta>=1){
			update();
			repaint();
			delta=0;
			}
		
		}
		
	}

	public void update() {
		if (inputSystem.upPressed == true) {
			posY -= speed;}
		else if (inputSystem.downPressed == true) {
			posY += speed;}
		else if (inputSystem.leftPressed == true) {
			posX -= speed;}
		else if (inputSystem.rightPressed == true) {
			posX += speed;
			System.out.println("right");}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//casting
		Graphics2D g2 = (Graphics2D)g;
		
		
		g2.setColor(Color.cyan);
		g2.fillOval(posX,posY,48, 48);
		g2.dispose();
	}
	
}
