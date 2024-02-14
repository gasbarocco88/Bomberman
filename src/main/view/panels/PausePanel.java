package main.view.panels;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PausePanel extends JPanel  {
	
	private final JButton resumeButton;
	
	public PausePanel() {

		resumeButton = new JButton();
		resumeButton.setBackground(Color.red);
		this.add(resumeButton);
		this.setDoubleBuffered(true);
		setFocusable(true);
		setVisible(true);
		

	}

	public JButton getResumeButton() {
		return resumeButton;
	}

}
