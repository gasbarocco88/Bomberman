package main.view.panels;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import main.model.Model;

public class GamePanel extends JPanel{
	
	private final Model model;
	
	//da fare singleton
	public GamePanel(Model model) {
		this.model = model;
		panelSetup();
		//this.addKeyListener(model.inputSystem);
	}
	
	private void panelSetup(){
		this.setBackground(Color.black);
		this.setOpaque(true);
		this.setDoubleBuffered(true);
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
