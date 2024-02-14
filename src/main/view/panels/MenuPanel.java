package main.view.panels;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	private final JButton startButton;
	private final JButton quitButton;

	
	public MenuPanel() {

		startButton = new JButton();
		quitButton = new JButton();
		this.add(startButton);
		this.add(quitButton);
		this.setDoubleBuffered(true);
		setFocusable(true);
		setVisible(true);

	}

	public JButton getQuitButton() {
		return quitButton;
	}

	public JButton getStartButton() {
		return startButton;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}
}
