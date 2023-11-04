package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.model.Model;

public class View {
	private final Model model;
	private JFrame frame;
	private JPanel panel;
	
	
	public View(Model model){
		this.model = model;
		frame = new JFrame();
		GamePanel gamePanel = new GamePanel(model);
		frame.add(gamePanel);
		frameSetUp();	
		}

	
	private void frameSetUp () {
		frame.setTitle("Bomberman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.pack();
		
	}
	
	public void repaint() {
		frame.repaint();
		
	}
}
