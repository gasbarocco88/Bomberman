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
import main.controller.PlayerManager;
import main.model.Model;
import main.model.Player;
import main.model.actors.Actor;
import main.view.panels.Altropanel;
import main.view.panels.GamePanel;
import main.view.panels.MenuPanel;
import main.view.panels.PausePanel;
import main.view.panels.PlaaaaaaaaayerPanel;
import main.view.panels.PlayerPanel;

public class View implements Observer {
	static private View instance;
	private InputSystem inputSystem;
	private JFrame frame;
	private JPanel panel;
	public Altropanel altro;
	private static PlaaaaaaaaayerPanel plaaaaaaaaayerPanel;
	private static GamePanel gamePanel;
	private static MenuPanel menuPanel;
	private static PausePanel pausePanel;
	private static PlayerPanel playerPanel;

	final int screenWidth = 800;
	final int screenHeight = 600;

	static public View getInstance() {
		if (instance == null)
			instance = new View();
		return instance;
	}

	private View() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
		altro = new Altropanel();
		gamePanel = new GamePanel();
		menuPanel = new MenuPanel();
		pausePanel = new PausePanel();
		playerPanel = new PlayerPanel();
		addActionListeners();
		setPanel(menuPanel);
		frameSetUp();
	};

	@Override
	public void update(Observable o, Object arg) {
		frame.repaint();
		
		if (Model.getInstance().getGame().isGameOver()) {
			setPanel(menuPanel);
			Model.getInstance().getGame().setGameOver(false);
		}


		if (!Model.getInstance().getGame().isRunning()) {
			setPanel(menuPanel);
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

	private void addActionListeners() {
		menuPanel.getStartButton().addActionListener(e -> ciao());
		menuPanel.getQuitButton().addActionListener(e -> System.exit(0));
	
		altro.getStaddddddrtButton().addActionListener(e->loadPlayer());
		
		
		playerPanel.getNewPlayerButton().addActionListener(e -> createPlayer());
		playerPanel.getLoadPlayerButton().addActionListener(e -> loadPlayer());
		playerPanel.getDeletePlayerButton().addActionListener(e-> deletePlayer());

		pausePanel.getResumeButton().addActionListener(e -> resumeAction());
	}
private void ciao() {
	System.out.println("ivhrs fisvnsivnreisrvjn");
	setPanel(altro);
	System.out.println("ivhrs fisvnsivnreisrvjn2");

}
	private void setUpActionButton() {

		// view deve leggere player nome e avatar
		// e passarlo a modello a modello, per creare game
		Player p = new Player("ciao", "ciao", 0, 0, 0, 0, 0);

		Model.getInstance().startGame(p);
		setPanel(gamePanel);
	}

	private void createPlayer() {
	
		if(playerPanel.getGroup().getSelection()==null) {
			playerPanel.getErrorsText().setText("Seleziona un personaggio");
			playerPanel.getErrorsText().setVisible(true);
			System.out.println("ciao2");
		};
		
		if(playerPanel.getPlayerName().getText().isEmpty()) {
			playerPanel.getErrorsText().setText("Inserisci un nickname");
			playerPanel.getErrorsText().setVisible(true);
			System.out.println("ciao");}
		
		//check che la combo non esista già

		

	}
	
	private void deletePlayer() {
		if(playerPanel.getTablePlayer().getSelectedValue()!=null) {
			String x = (String) playerPanel.getTablePlayer().getSelectedValue();
			PlayerManager.getInstance().deletePlayer(x);
			playerPanel.importPlayers();
		};
	}

	private void loadPlayer() {
		String id = (String) playerPanel.getTablePlayer().getSelectedValue();
		Player p = PlayerManager.getInstance().loadPlayer(id);
		Model.getInstance().startGame(p);
		setPanel(gamePanel);
	}

	private void resumeAction() {
		Model.getInstance().getGame().setRunning(true);
		setPanel(gamePanel);
	}

	public void repaint() {
		frame.repaint();

	}

	private void setPanel(JPanel panel) {
		System.out.println("helloooo");
		this.panel = panel;
		System.out.println("helloooo2");

		frame.setContentPane(panel);
		System.out.println("helloooo3");

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
