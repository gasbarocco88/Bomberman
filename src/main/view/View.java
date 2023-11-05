package main.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.controller.InputSystem;
import main.model.Model;
import main.view.panels.GamePanel;

public class View {
	private JFrame frame;
	private JPanel panel;
	private InputSystem inputSystem;
	private final Model model;

	final int screenWidth = 768;
	final int screenHeight = 576;

	public View(InputSystem inputSystem, Model model) {
		this.inputSystem = inputSystem;
		this.model = model;
		frame = new JFrame();
		GamePanel gamePanel = new GamePanel(model);
		gamePanel.addKeyListener(inputSystem);
		setPanel(gamePanel);
		frameSetUp();

	};

	private void frameSetUp() {
		frame.setTitle("Bomberman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.pack();
	}

	public void repaint() {
		frame.repaint();

	}

	private void setPanel(JPanel panel) {
		this.panel = panel;
		frame.setContentPane(panel);
		frame.revalidate();
	}
}
