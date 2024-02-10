package main.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.controller.InputSystem;
import main.controller.WorldFactory;
import main.model.Model;
import main.model.actors.Actor;
import main.view.panels.GamePanel;
import main.view.panels.MenuPanel;

public class View implements Observer{
	static private View instance;
	private InputSystem inputSystem;
	private JFrame frame;
	private JPanel panel;
	private static GamePanel gamePanel;
    private static MenuPanel menuPanel;
    
	final int screenWidth = 768;
	final int screenHeight = 576;

	static public View getInstance() {
		if (instance == null) instance = new View();
		return instance;
	}
//	public View(Model model) {
//		this.model = model;
//		frame = new JFrame();
//		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
//		
//		gamePanel = new GamePanel(model);
//		menuPanel = new MenuPanel();
//
//		addActionListeners();
//		setPanel(menuPanel);
//		frameSetUp();
//	};
	
	private View() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		gamePanel = new GamePanel();
		menuPanel = new MenuPanel();

		addActionListeners();
		setPanel(menuPanel);
		frameSetUp();
	};
	
	@Override
	public void update(Observable o, Object arg) {
		frame.repaint();
		
		
		if (o instanceof Model) {
			Model x = (Model) o;
			//System.out.println(x.num);
			//System.out.println("holaaaaaaaaaaaaaaaa");
			//gamePanel.setH(x.getHero());
			//gamePanel.paintComponent(null, x.getHero());
		}
			
		
	}
	private void frameSetUp() {
		frame.setTitle("Bomberman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.setVisible(true);
		frame.requestFocus();
	}



    private void addActionListeners()
    {
        menuPanel.getStartButton().addActionListener(e -> setUpActionButton());
        menuPanel.getQuitButton().addActionListener(e -> System.exit(0));
    }
    

    private void setUpActionButton()
    {
    	//spostale nel modello
    	CopyOnWriteArrayList<Actor> actors = WorldFactory.loadWorld("/home/rocco/Documenti/università/bombermanWindow/src/main/resources/world1.txt", inputSystem);
    	Model.getInstance().setActors(actors);
    	/////
    	setPanel(gamePanel);
    }

 
	
	public void repaint() {
		frame.repaint();

	}

	private void setPanel(JPanel panel) {
		this.panel = panel;
		frame.setContentPane(panel);
		frame.revalidate();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public InputSystem getInputSystem() {
		return inputSystem;
	}

	public void setInputSystem(InputSystem inputSystem) {
		this.inputSystem = inputSystem;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public JPanel getPanel() {
		return panel;
	}

//	public Model getModel() {
//		return model;
//	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	
	
}
