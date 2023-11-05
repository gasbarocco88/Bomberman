package main.controller;

import main.model.Model;
import main.view.View;

public class Controller implements Runnable {
	private final Model model;
	private final View view;
	private final InputSystem inputSystem;
	long oldTime =  System.currentTimeMillis();
	Thread gameThread;
	// InputSystem inputSystem = new InputSystem();

	/**
	 * Constructor of Controller Class
	 */
	public Controller() {
		inputSystem = new InputSystem();
		this.model = new Model(inputSystem);
		this.view = new View(inputSystem, model);

	}

	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        long lastTime = System.nanoTime();
        double delta = 0;
        long now;
        //long timer = 0;
        //int ticks = 0;

//		double drawInterval = 1000000000/30;
//		double nextDrawTime = System.nanoTime()+drawInterval;
		
		
		while (gameThread!=null) {
			now = System.nanoTime();
            delta +=(double) (now - lastTime) / timePerTick; // number between 0 and 1
           // timer += now - lastTime;
            lastTime = now;
			//System.out.println(delta);
//			model.update();
//            view.repaint();
//            try {
//            	double remainingTime = nextDrawTime - System.nanoTime();
//            	remainingTime = remainingTime/1000000;
//            	
//            	if (remainingTime<0){remainingTime=0;}
//            	Thread.sleep((long)remainingTime);
//            	nextDrawTime += drawInterval;
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				// TODO: handle exception
//			}

            
            
	           if (delta >= 1)
	            {
	        	   model.update();
	                view.repaint();
	                
	                delta--;
	            }

	            // reset Timer
//	            if (timer >= 1000000000)
//	            {
////	                System.out.println("Ticks and Frames: " + ticks); // shows FPS
//
//	                ticks = 0;
//	                timer = 0;
//	            }	
		}
	}

}
