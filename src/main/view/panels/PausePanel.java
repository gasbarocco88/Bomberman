package main.view.panels;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.view.View;

/**
 * Classe che gestisce il pannello di Pausa durante una partita
 */
public class PausePanel extends JPanel {

	private final JButton resumeButton;
	private final JButton exitButton;

	/**
	 * Costruttore della classe PausePanel, istanzia e setta graficamente i bottoni
	 * del pannello stesso
	 */
	public PausePanel() {

		resumeButton = new JButton("Continue");
		resumeButton.setBounds(300, 270, 100, 70);
		resumeButton.setIcon(new ImageIcon("./src/main/resources/images/button/yes.png"));
		this.add(resumeButton);
		exitButton = new JButton("Exit");
		exitButton.setBounds(420, 270, 100, 70);
		exitButton.setIcon(new ImageIcon("./src/main/resources/images/button/no.png"));
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
	
	/**
	 * Metodo di paint del Jpanel, disegna l'immagine di background del pannello
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.black);
		g.fillRect(0, 0, View.getInstance().getScreenWidth(), View.getInstance().getScreenHeight());
		g.setColor(new Color(245, 127, 10));
		g.setFont(new Font("Ink Free", Font.BOLD, 60));
		g.drawString("CONTINUE?", 220, 250);
	}

}
