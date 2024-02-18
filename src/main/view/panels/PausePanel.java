package main.view.panels;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PausePanel extends JPanel  {
	
	private final JButton resumeButton;
	private final JButton exitButton;

	
	public PausePanel() {

		resumeButton = new JButton("Continue");
		this.add(resumeButton);
		exitButton = new JButton("Exit");
		this.add(resumeButton);
		this.add(exitButton);
		this.setDoubleBuffered(true);
		setFocusable(true);
		setVisible(true);
		

	}

	public JButton getResumeButton() {
		return resumeButton;
	}
	
	public JButton getExitButton() {
		return exitButton;
	}


}
