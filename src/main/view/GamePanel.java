package main.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Currency;

import javax.swing.JPanel;

import main.controller.InputSystem;
import main.model.Model;

public class GamePanel extends JPanel{
	
	final int screenWidth = 768;
	final int screenHeight = 576;
	Thread gameThread;
	private final Model model;
	
	//da fare singleton
	public GamePanel(Model model) {
		this.model = model;
//		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
//		this.setBackground(Color.black);
//		this.setDoubleBuffered(true);
//		this.addKeyListener(inputSystem);
//		this.setFocusable(true);
		panelSetup();
	}
	
	private void panelSetup(){
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(model.inputSystem);
		this.setFocusable(true); //serve a mettere il famePanel focused per ricevere gli input
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//casting
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.cyan);
		g2.fillOval(model.posX,model.posY,48, 48);
		g2.dispose();
	}
	
	//	public void startGame() {
//		gameThread = new Thread(this);
//		gameThread.start();
//	}
//	@Override
//	public void run() {
//		
//		double renderInterval = 1_000_000_000/60;
//		double delta = 0;
//		long lastTime = System.nanoTime();
//		long currentTime;
//		
//		while(gameThread != null) {
//			
//			currentTime = System.nanoTime();
//			delta += (currentTime - lastTime)/renderInterval;
//			lastTime = currentTime;
//			
//			if (delta>=1){
//			update();
//			repaint();
//			delta=0;
//			}
//		
//		}
//		
//	}

	
}
