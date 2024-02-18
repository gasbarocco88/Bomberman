package main.view.panels;

import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
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
	private ImageIcon gokuImage;
	private ImageIcon vegetaImage;
	private ImageIcon piccoloImage;
	private ButtonGroup group;
	private JTextField playerName;
	private JTextField errorsText;
	private JButton newPlayerButton;
	private JButton loadPlayerButton;
	private JButton deletePlayerButton;
	private JButton backMenuButton;
	private JList tablePlayer;
	private JTable tableRanking;

	public PlayerPanel() {

		setButtons();
		setDoubleBuffered(true);
		setFocusable(true);
		setVisible(true);
		importPlayers();
	}

	private void setButtons() {
		group = new ButtonGroup();
		gokuButton = new JToggleButton("Goku");
		gokuButton.setActionCommand("Goku");
		gokuButton.setBounds(244, 30, 154, 120);

		vegetaButton = new JToggleButton("Vegeta");
		vegetaButton.setActionCommand("Vegeta");
		vegetaButton.setBounds(576, 30, 154, 120);

		piccoloButton = new JToggleButton("Piccolo");
		piccoloButton.setActionCommand("Piccolo");
		piccoloButton.setBounds(410, 30, 154, 120);

		group.add(gokuButton);
		group.add(vegetaButton);
		group.add(piccoloButton);

		add(gokuButton);
		add(vegetaButton);
		add(piccoloButton);
		setLayout(null);
		
		newPlayerButton = new JButton("New Player");
		newPlayerButton.setBounds(66, 58, 117, 40);
		add(newPlayerButton);

		loadPlayerButton = new JButton("Load Player");
		loadPlayerButton.setBounds(66, 215, 117, 40);
		add(loadPlayerButton);

		deletePlayerButton = new JButton("Delete Player");
		deletePlayerButton.setBounds(200, 215, 117, 40);
		add(deletePlayerButton);
		
		backMenuButton  = new JButton("Go Back");
		backMenuButton.setBounds(0, 215, 117, 40);
		add(backMenuButton);

		playerName = new JTextField();
		playerName.setBounds(42, 110, 162, 25);
		playerName.setColumns(2);
//		playerName.addFocusListener(new FocusListener() {
//		    public void focusGained(FocusEvent e) {
//		    	playerName.setText("");
//		    }
//		    public void focusLost(FocusEvent e) {
//		    }
//		});
		add(playerName);

		errorsText = new JTextField("Player already exist!");
		errorsText.setBounds(42, 147, 162, 25);
		errorsText.setVisible(false);
		add(errorsText);

		tablePlayer = new JList();
		tablePlayer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePlayer.setBounds(42, 275, 162, 265);
		add(tablePlayer);

		tableRanking = new JTable();
		tableRanking.setBounds(244, 275, 486, 265);
		tableRanking.setRowSelectionAllowed(false);
		tableRanking.setShowGrid(false);
		add(tableRanking);
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

	public ImageIcon getGokuImage() {
		return gokuImage;
	}

	public void setGokuImage(ImageIcon gokuImage) {
		this.gokuImage = gokuImage;
	}

	public ImageIcon getVegetaImage() {
		return vegetaImage;
	}

	public void setVegetaImage(ImageIcon vegetaImage) {
		this.vegetaImage = vegetaImage;
	}

	public ImageIcon getPiccoloImage() {
		return piccoloImage;
	}

	public void setPiccoloImage(ImageIcon piccoloImage) {
		this.piccoloImage = piccoloImage;
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

	public JTextField getErrorsText() {
		return errorsText;
	}

	public void setErrorsText(JTextField errorsText) {
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

	}
}
