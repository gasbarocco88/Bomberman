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
import javax.swing.text.PlainDocument;

import main.controller.InputSystem;
import main.controller.LevelFactoryText;
import main.model.Model;
import main.model.Player;
import main.model.actors.Actor;
import main.view.panels.GamePanel;
import main.view.panels.MenuPanel;
import main.view.panels.PlayerPanel;

public class View implements Observer{
	static private View instance;
	private InputSystem inputSystem;
	private JFrame frame;
	private JPanel panel;
	private static PlayerPanel playerPanel;
	private static GamePanel gamePanel;
    private static MenuPanel menuPanel;
    
	final int screenWidth = 768;
	final int screenHeight = 576;

	static public View getInstance() {
		if (instance == null) instance = new View();
		return instance;
	}
	
	private View() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		gamePanel = new GamePanel();
		menuPanel = new MenuPanel();
		playerPanel = new PlayerPanel();
		addActionListeners();
		setPanel(menuPanel);
		frameSetUp();
	};
	
	@Override
	public void update(Observable o, Object arg) {
		frame.repaint();
		
		
		if (o instanceof Model) {
			Model x = (Model) o;
			///gioco in pausa, cambia panel
			if(!x.getGame().isRunning()) {
				setPanel(menuPanel);
			}
			
			//if lifes = 0, transizione hai perso, reset mondo, 
			
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

        menuPanel.riprendi.addActionListener(e -> resumeActionButton());
    }
    

    private void setUpActionButton()
    {
    	
    	//view deve passare un player e un avatar a modello, per creare game
    	Player p = new Player();
    	Model.getInstance().startGame(p, "ciao");
    	//Model.getInstance().loadLevel(1);
    	//setPanel(playerPanel);
    	setPanel(gamePanel);
    }

    private void resumeActionButton() {
    	Model.getInstance().getGame().setRunning(true);
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

//	public InputSystem getInputSystem() {
//		return inputSystem;
//	}
//
//	public void setInputSystem(InputSystem inputSystem) {
//		this.inputSystem = inputSystem;
//	}

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
