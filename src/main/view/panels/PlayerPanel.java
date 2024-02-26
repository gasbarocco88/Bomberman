package main.view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import main.controller.PlayerManager;
import main.model.Player;

public class PlayerPanel extends JPanel {

	// stringa testo invisibile per errore
	private JToggleButton gokuButton;
	private JToggleButton vegetaButton;
	private JToggleButton piccoloButton;
	private ButtonGroup group;
	private JTextField playerName;
	private JLabel errorsText;
	private JLabel rankingText;
	private JButton newPlayerButton;
	private JButton loadPlayerButton;
	private JButton deletePlayerButton;
	private JButton backMenuButton;
	private JList tablePlayer;
	private JTable tableRanking;
	private JScrollPane scrollPaneRanking;
	private JScrollPane scrollPanePlayer;
	private BufferedImage backgroundImage;

	public PlayerPanel() {
		try {
			backgroundImage = ImageIO.read(new File("./src/main/resources/images/background/player.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		setButtons();
		setDoubleBuffered(true);
		setFocusable(true);
		setVisible(true);
		importPlayers();
	}

	private void setButtons() {
		group = new ButtonGroup();
		gokuButton = new JToggleButton("Goku");
		gokuButton.setActionCommand("goku");
		gokuButton.setBounds(244, 30, 120, 100);
		gokuButton.setIcon(new ImageIcon("./src/main/resources/images/button/goku.png"));

		vegetaButton = new JToggleButton("Vegeta");
		vegetaButton.setActionCommand("vegeta");
		vegetaButton.setBounds(586, 30, 120, 100);
		vegetaButton.setIcon(new ImageIcon("./src/main/resources/images/button/vegeta.png"));
		
		piccoloButton = new JToggleButton("Piccolo");
		piccoloButton.setActionCommand("piccolo");
		piccoloButton.setBounds(420, 30, 120, 100);
		piccoloButton.setIcon(new ImageIcon("./src/main/resources/images/button/piccolo.png"));
		
		group.add(gokuButton);
		group.add(vegetaButton);
		group.add(piccoloButton);

		add(gokuButton);
		add(vegetaButton);
		add(piccoloButton);
		setLayout(null);
		
		newPlayerButton = new JButton("New Player");
		newPlayerButton.setBounds(66, 30, 120, 40);
		newPlayerButton.setIcon(new ImageIcon("./src/main/resources/images/button/create.png"));
		add(newPlayerButton);

		loadPlayerButton = new JButton("Load Player");
		loadPlayerButton.setBounds(66, 170, 120, 40);
		loadPlayerButton.setIcon(new ImageIcon("./src/main/resources/images/button/load.png"));
		add(loadPlayerButton);

		deletePlayerButton = new JButton("Delete Player");
		deletePlayerButton.setBounds(66, 220, 120, 40);
		deletePlayerButton.setIcon(new ImageIcon("./src/main/resources/images/button/delete.png"));
		add(deletePlayerButton);
		
		backMenuButton  = new JButton("Go Back");
		backMenuButton.setBounds(340, 510, 120, 40);
		backMenuButton.setIcon(new ImageIcon("./src/main/resources/images/button/back.png"));
		add(backMenuButton);

		playerName = new JTextField();
		playerName.setBounds(42, 80, 162, 25);
		playerName.setBackground(new Color(245, 127, 10));
		playerName.setForeground(Color.black);
		playerName.setFont(new Font("Ink Free", Font.BOLD, 12));
		add(playerName);

		rankingText = new JLabel("Ranking");
		rankingText.setBounds(350, 105, 350, 250);
		rankingText.setFont(new Font("Ink Free", Font.BOLD, 60));
		rankingText.setForeground(new Color(245, 127, 10));
		rankingText.setVisible(true);
		add(rankingText);

		
		errorsText = new JLabel();
		errorsText.setBounds(42, 110, 200, 25);
		errorsText.setFont(new Font("Ink Free", Font.BOLD, 12));
		errorsText.setForeground(new Color(245, 127, 10));
		errorsText.setVisible(false);
		add(errorsText);

		tablePlayer = new JList();
		tablePlayer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePlayer.setBackground(new Color(245, 127, 10));
		tablePlayer.setFont(new Font("Ink Free", Font.BOLD, 12));
		//tablePlayer.setBounds(42, 275, 162, 265);
		//add(tablePlayer);

		tableRanking = new JTable();
		tableRanking.setDefaultEditor(Object.class, null);
		//tableRanking.setBounds(244, 275, 486, 265);
		tableRanking.setRowSelectionAllowed(false);
		tableRanking.setShowGrid(false);
		tableRanking.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableRanking.setBackground(new Color(245, 127, 10));
		tableRanking.setFont(new Font("Ink Free", Font.BOLD, 12));
		//		add(tableRanking);
		
		scrollPaneRanking= new JScrollPane(tableRanking);
		scrollPaneRanking.setVisible(true);
		scrollPaneRanking.setBounds(244, 275, 500, 220);
		
        add(scrollPaneRanking);
        
        scrollPanePlayer= new JScrollPane(tablePlayer);
        scrollPanePlayer.setVisible(true);
        scrollPanePlayer.setBounds(42, 275, 180, 220);
        add(scrollPanePlayer);
        
        
	}

	public JButton getBackMenuButton() {
		return backMenuButton;
	}

	public void setBackMenuButton(JButton backMenuButton) {
		this.backMenuButton = backMenuButton;
	}

	public void importPlayers() {
		HashMap<String, Player> ps = PlayerManager.getInstance().getPlayerstats();
		String[][] players = PlayerManager.getInstance().loadRanks();
		Object columnNamesRanks[] = { "Nickname", "Personaggio", "Games Played", "Games Won", "Levels Won",
				"Highest Score", "Total Score" };
		Object columnNamesPlayer[] = { "Nickname", "Personaggio" };
		DefaultTableModel modelRanks = new DefaultTableModel(columnNamesRanks, 0);
		DefaultListModel<String> modelPlayer = new DefaultListModel<String>();

		for (int i = 0; i < players.length; i++) {
			modelRanks.addRow(players[i]);
			modelPlayer.addElement(players[i][0] + "-" + players[i][1]);
		}
		tableRanking.setModel(modelRanks);
		tablePlayer.setModel(modelPlayer);

	}

	public JButton getDeletePlayerButton() {
		return deletePlayerButton;
	}

	public void setDeletePlayerButton(JButton deletePlayerButton) {
		this.deletePlayerButton = deletePlayerButton;
	}

	public JButton getNewPlayerButton() {
		return newPlayerButton;
	}

	public JButton getLoadPlayerButton() {
		return loadPlayerButton;
	}

	public JToggleButton getGokuButton() {
		return gokuButton;
	}

	public void setGokuButton(JToggleButton gokuButton) {
		this.gokuButton = gokuButton;
	}

	public JToggleButton getVegetaButton() {
		return vegetaButton;
	}

	public void setVegetaButton(JToggleButton vegetaButton) {
		this.vegetaButton = vegetaButton;
	}

	public JToggleButton getPiccoloButton() {
		return piccoloButton;
	}

	public void setPiccoloButton(JToggleButton piccoloButton) {
		this.piccoloButton = piccoloButton;
	}


	public ButtonGroup getGroup() {
		return group;
	}

	public void setGroup(ButtonGroup group) {
		this.group = group;
	}

	public JTextField getPlayerName() {
		return playerName;
	}

	public void setPlayerName(JTextField playerName) {
		this.playerName = playerName;
	}

	public JLabel getErrorsText() {
		return errorsText;
	}

	public void setErrorsText(JLabel errorsText) {
		this.errorsText = errorsText;
	}

	public JList getTablePlayer() {
		return tablePlayer;
	}

	public void setTablePlayer(JList tablePlayer) {
		this.tablePlayer = tablePlayer;
	}

	public JTable getTableRanking() {
		return tableRanking;
	}

	public void setTableRanking(JTable tableRanking) {
		this.tableRanking = tableRanking;
	}

	public void setNewPlayerButton(JButton newPlayerButton) {
		this.newPlayerButton = newPlayerButton;
	}

	public void setLoadPlayerButton(JButton loadPlayerButton) {
		this.loadPlayerButton = loadPlayerButton;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}
}
