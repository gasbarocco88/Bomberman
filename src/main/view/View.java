package main.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.controller.InputSystem;
import main.model.Model;
import main.view.panels.GamePanel;
import main.view.panels.MenuPanel;

public class View {
	private final Model model;
	private InputSystem inputSystem;
	private JFrame frame;
	private JPanel panel;
	private static GamePanel gamePanel;
    private static MenuPanel menuPanel;
    
	final int screenWidth = 768;
	final int screenHeight = 576;

//	public View(InputSystem inputSystem, Model model) {
//		this.inputSystem = inputSystem;
//		this.model = model;
//		frame = new JFrame();		
//		gamePanel = new GamePanel(model);
//		menuPanel = new MenuPanel();
//		setPanel(gamePanel);
//		frameSetUp();
//
//	};
	
	public View(Model model) {
		this.model = model;
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		gamePanel = new GamePanel(model);
		menuPanel = new MenuPanel();

		addActionListeners();
		setPanel(menuPanel);
		frameSetUp();
	};
	private void frameSetUp() {
		frame.setTitle("Bomberman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.setVisible(true);
		frame.requestFocus();
	}

	

    private void addActionListeners()
    {
        menuPanel.getStartButton().addActionListener(e -> setUpActionButton());
        menuPanel.getQuitButton().addActionListener(e -> System.exit(0));
    }
    

    private void setUpActionButton()
    {
    	System.out.println("ciaooooooooo");
    	  setPanel(gamePanel);
    }

 
	
	public void repaint() {
		frame.repaint();

	}

	private void setPanel(JPanel panel) {
		this.panel = panel;
		frame.setContentPane(panel);
		frame.revalidate();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public InputSystem getInputSystem() {
		return inputSystem;
	}

	public void setInputSystem(InputSystem inputSystem) {
		this.inputSystem = inputSystem;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public Model getModel() {
		return model;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}
	
	
}
