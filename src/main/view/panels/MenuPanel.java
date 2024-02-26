package main.view.panels;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	private final JButton startButton;
	private final JButton quitButton;
	private BufferedImage backgroundImage;

	
	public MenuPanel() {
		try {
			backgroundImage = ImageIO.read(new File("./src/main/resources/images/background/menu.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		startButton = new JButton("New Game");
		startButton.setBounds(150, 400, 200, 70);
		startButton.setIcon(new ImageIcon("./src/main/resources/images/button/newgame.png"));
		quitButton = new JButton("Quit");
		quitButton.setBounds(450, 400, 200, 70);
		 quitButton.setIcon(new ImageIcon("./src/main/resources/images/button/quit.png"));
		this.add(startButton);
		this.add(quitButton);
		this.setDoubleBuffered(true);
		setFocusable(true);
		setVisible(true);
		setLayout(null);

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
		g.drawImage(backgroundImage, 0, 0, this);
	}
}
