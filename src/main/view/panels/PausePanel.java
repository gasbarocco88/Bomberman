package main.view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.view.View;

public class PausePanel extends JPanel  {
	
	private final JButton resumeButton;
	private final JButton exitButton;

	
	public PausePanel() {

		resumeButton = new JButton("Continue");
		resumeButton.setBounds(300, 180, 100, 70);
		this.add(resumeButton);
		exitButton = new JButton("Exit");
		exitButton.setBounds(420, 180, 100, 70);
		
		this.add(resumeButton);
		this.add(exitButton);
		this.setDoubleBuffered(true);
		setFocusable(true);
		setVisible(true);
		setLayout(null);

	}

	public JButton getResumeButton() {
		return resumeButton;
	}
	
	public JButton getExitButton() {
		return exitButton;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.black);		
		g.fillRect(0, 0, View.getInstance().getScreenWidth(),
				View.getInstance().getScreenHeight());
		g.setColor(Color.cyan);
		g.setFont(new Font("Ink Free", Font.BOLD, 60));
		g.drawString("CONTINUE?", 220, 150);
	}

}
