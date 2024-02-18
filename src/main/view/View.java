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
		
		if (Model.getInstance().getGame().isGameOver()) {
			Model.getInstance().getGame().setGameOver(false);
			Model.getInstance().getGame().setLevelPlaying(1);
			setPanel(menuPanel);
		}


		if (!Model.getInstance().getGame().isRunning()) {
			setPanel(pausePanel);
		}
		frame.repaint();
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
		menuPanel.getStartButton().addActionListener(e -> refreshPlayerPanel());
		menuPanel.getQuitButton().addActionListener(e -> System.exit(0));
	
		playerPanel.getNewPlayerButton().addActionListener(e -> createPlayer());
		playerPanel.getLoadPlayerButton().addActionListener(e -> loadPlayer());
		playerPanel.getDeletePlayerButton().addActionListener(e-> deletePlayer());
		playerPanel.getBackMenuButton().addActionListener(e->setPanel(menuPanel) );


		pausePanel.getResumeButton().addActionListener(e -> resumeAction());
		pausePanel.getExitButton().addActionListener(e -> exitGame());
	}


	private void refreshPlayerPanel() {
		playerPanel.importPlayers();
		setPanel(playerPanel);
	}

	private void createPlayer() {
	
		if(playerPanel.getGroup().getSelection()==null) {
			playerPanel.getErrorsText().setText("Seleziona un personaggio");
			playerPanel.getErrorsText().setVisible(true);
			System.out.println("ciao2");
		}
		
		else if(playerPanel.getPlayerName().getText().isEmpty()) {
			playerPanel.getErrorsText().setText("Inserisci un nickname");
			playerPanel.getErrorsText().setVisible(true);
			System.out.println("ciao");}
		
		else {
			String nickname = playerPanel.getPlayerName().getText();
			String avatar = playerPanel.getGroup().getSelection().getActionCommand();
			System.out.println(nickname);
			System.out.println(avatar);

			Player p = PlayerManager.getInstance().createPlayer(nickname, avatar);
			if(p== null) {
				playerPanel.getErrorsText().setText("Player già esistente");
				playerPanel.getErrorsText().setVisible(true);
			}
			else Model.getInstance().startGame(p);
			setPanel(gamePanel);
		}
		

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
	
	private void exitGame() {
		Model.getInstance().updatePlayerPoints(true);
		PlayerManager.getInstance().updatePlayerStats(Model.getInstance().getGame().getPlayer());

		setPanel(menuPanel);
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
