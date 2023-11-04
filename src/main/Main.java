package main;
import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		
		
		//Jframe da fare singleton
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setTitle("Bomberman");
		window.setVisible(true);
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		gamePanel.startGame();
		
	}

}
