package main.view;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.controller.AudioManager;
import main.controller.PlayerManager;
import main.model.Model;
import main.model.Player;
import main.view.panels.GamePanel;
import main.view.panels.MenuPanel;
import main.view.panels.PausePanel;
import main.view.panels.PlayerPanel;

/**
 * Classe singleton che rappresenta la View del pattern MVC; si occupa della
 * gestione della parte grafica e dei vari menu di gioco. Istanzia un unico
 * JFrame e tutti i diversi pannelli (menu, game, pause e player) che vengono
 * switchati ogni volta che ne viene richiesto l'utilizzo. Si occupa di settare
 * inoltre le funzioni dei vari pulsanti presenti in ciascun pannello.
 */
public class View implements Observer {
	static private View instance;
	private JFrame frame;
	private static GamePanel gamePanel;
	private static MenuPanel menuPanel;
	private static PausePanel pausePanel;
	private static PlayerPanel playerPanel;
	final int screenWidth = 800;
	final int screenHeight = 600;

	/**
	 * Metodo per istanziare la classe View o ricevere l'istanza già creata
	 * 
	 * @return l'istanza della classe View
	 */
	static public View getInstance() {
		if (instance == null)
			instance = new View();
		return instance;
	}

	private View() {
		AudioManager.getInstance().play("musicMenu.wav", true);
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
		gamePanel = new GamePanel();
		menuPanel = new MenuPanel();
		pausePanel = new PausePanel();
		playerPanel = new PlayerPanel();
		addActionListeners();
		setPanel(menuPanel);
		frameSetUp();
	};

	/**
	 * Metodo che viene chiamato ogni volta che il Model notifica il suo cambiamento
	 * di stato ai propri observer. Viene chiamato il metodo repaint per ridisegnare
	 * a schermo la nuova situazione di gioco. Nel caso in cui il gioco sia messo in
	 * pausa o sia game over, viene switchato al rispettivo pannello.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (Model.getInstance().getGame().isGameOver()) {
			Model.getInstance().getGame().setGameOver(false);
			Model.getInstance().getGame().setLevelPlaying(1);
			AudioManager.getInstance().switchMusic("musicMenu.wav", true);
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
		playerPanel.getDeletePlayerButton().addActionListener(e -> deletePlayer());
		playerPanel.getBackMenuButton().addActionListener(e -> setPanel(menuPanel));

		pausePanel.getResumeButton().addActionListener(e -> resumeAction());
		pausePanel.getExitButton().addActionListener(e -> exitGame());
	}

	private void refreshPlayerPanel() {
		playerPanel.importPlayers();
		setPanel(playerPanel);
	}

	private void createPlayer() {
		if (playerPanel.getGroup().getSelection() == null) {
			playerPanel.getErrorsText().setText("Seleziona un personaggio!!");
			playerPanel.getErrorsText().setVisible(true);
		} else if (playerPanel.getPlayerName().getText().isEmpty()) {
			playerPanel.getErrorsText().setText("Inserisci un nickname!!");
			playerPanel.getErrorsText().setVisible(true);
		} else {
			String nickname = playerPanel.getPlayerName().getText();
			String avatar = playerPanel.getGroup().getSelection().getActionCommand();
			Player p = PlayerManager.getInstance().createPlayer(nickname, avatar);
			if (p == null) {
				playerPanel.getErrorsText().setText("Player già esistente!!!");
				playerPanel.getErrorsText().setVisible(true);
			} else {
				Model.getInstance().startGame(p);
				setPanel(gamePanel);
			}
		}
	}

	private void deletePlayer() {
		if (playerPanel.getTablePlayer().getSelectedValue() != null) {
			String x = (String) playerPanel.getTablePlayer().getSelectedValue();
			PlayerManager.getInstance().deletePlayer(x);
			playerPanel.importPlayers();
		}
	}

	private void loadPlayer() {
		String id = (String) playerPanel.getTablePlayer().getSelectedValue();
		if (id == null) {
			playerPanel.getErrorsText().setText("Seleziona un nickname");
			playerPanel.getErrorsText().setVisible(true);
			return;
		}
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
		AudioManager.getInstance().switchMusic("musicMenu.wav", true);
		setPanel(menuPanel);
	}

	private void setPanel(JPanel panel) {
		frame.setContentPane(panel);
		frame.revalidate();
	}

	// setters and getters
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

}
