package main.view.panels;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;
import main.model.Model;
import main.model.actors.Actor;
import main.model.actors.Blast;
import main.model.actors.Bomb;
import main.view.View;
import main.view.imageFactory.BlastImageFactory;
import main.view.imageFactory.BombImageFactory;
import main.view.imageFactory.EnemyImageFactory;
import main.view.imageFactory.HeroImageFactory;
import main.view.imageFactory.ItemImageFactory;
import main.view.imageFactory.WallImageFactory;
import main.model.actors.Hero;
import main.model.actors.Item;
import main.model.actors.Wall;

/**
 * Classe che gestisce il pannello di Game, ovvero della vera e propria partita giocata dall'utente.
 */
public class GamePanel extends JPanel {
	private HeroImageFactory heroImgFactory;
	private ItemImageFactory itemImgFactory;
	private WallImageFactory wallImgFactory;
	private EnemyImageFactory enemyImgFactory;
	private BombImageFactory bombImgFactory;
	private BlastImageFactory blastImgFactory;
	private BufferedImage levelOneBackgroundImage;
	private BufferedImage levelTwoBackgroundImage;
	private BufferedImage hittedImage;
	private BufferedImage gameOverImage;
	private BufferedImage levelWonImage;
	private JTextField playerName;
	private int counter;

	/**
	 * Costruttore della classe GamePanel, istanzia tutte le classi factory per il
	 * caricamento delle immagini dei vari attori. Carica inoltre le immagini di
	 * sfondo per le transizioni tra i livelli o quando si perde una vita
	 */
	public GamePanel() {
		panelSetup();
		heroImgFactory = new HeroImageFactory();
		itemImgFactory = new ItemImageFactory();
		wallImgFactory = new WallImageFactory();
		enemyImgFactory = new EnemyImageFactory();
		bombImgFactory = new BombImageFactory();
		blastImgFactory = new BlastImageFactory();
		try {
			levelOneBackgroundImage = ImageIO.read(new File("./src/main/resources/images/background/level_1.jpg"));
			levelTwoBackgroundImage = ImageIO.read(new File("./src/main/resources/images/background/level_2.jpg"));
			hittedImage = ImageIO.read(new File("./src/main/resources/images/background/lost.png"));
			gameOverImage = ImageIO.read(new File("./src/main/resources/images/background/gameOver.png"));
			levelWonImage = ImageIO.read(new File("./src/main/resources/images/background/won.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
	}

	private void panelSetup() {
		setOpaque(true);
		setFocusable(true);
		setDoubleBuffered(true);
		requestFocus();
	}

	private void drawStats(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(600, 0, 200, View.getInstance().getScreenHeight());
		g.setColor(Color.white);
		g.setFont(new Font("Ink Free", Font.BOLD, 30));
		g.drawString("Player", 620, 30);
		g.drawString("Avatar", 620, 150);
		g.drawString("Lifes", 620, 270);
		g.drawString("Score", 620, 390);
		g.setFont(new Font("Ink Free", Font.BOLD, 20));
		g.setColor(Color.cyan);
		g.drawString(Model.getInstance().getGame().getPlayer().getNickname(), 620, 60);
		g.drawString(Model.getInstance().getGame().getPlayer().getAvatar(), 620, 180);
		g.drawString(String.valueOf(Model.getInstance().getGame().getLifes()), 620, 300);
		g.drawString(String.valueOf(Model.getInstance().getGame().getScore()), 620, 420);
	}

	/**
	 * Metodo di paint del Jpanel; disegna l'immagine di ciascun actor ottenuta
	 * dalla rispettiva factory, disegna inoltre le immagini di sfondo dei livelli e
	 * quelle di transizione tra un livello e l'altro o dopo la perdita di una vita
	 * o della partita, e disegna infine alcune statistiche della partita in corso.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!Model.getInstance().getGame().isGameOver()) {
			// draw background and stats
			if (Model.getInstance().getGame().getLevelPlaying() == 1) {
				g.drawImage(levelOneBackgroundImage, 0, 0, View.getInstance().getScreenWidth(),
						View.getInstance().getScreenHeight(), null);
			} else {
				g.drawImage(levelTwoBackgroundImage, 0, 0, View.getInstance().getScreenWidth(),
						View.getInstance().getScreenHeight(), null);
			}
			drawStats(g);

			//draw actors
			CopyOnWriteArrayList<Actor> actors = Model.getInstance().getActors();
			Collections.sort(actors);

			for (Actor a : actors) {
				BufferedImage img;
				Graphics2D g2 = (Graphics2D) g;

				if (a instanceof Hero)
					img = heroImgFactory.returnImage(a);
				else if (a instanceof Item)
					img = itemImgFactory.returnImage(a);
				else if (a instanceof Bomb)
					img = bombImgFactory.returnImage(a);
				else if (a instanceof Blast)
					img = blastImgFactory.returnImage(a);
				else if (a instanceof Wall)
					img = wallImgFactory.returnImage(a);
				else
					img = enemyImgFactory.returnImage(a);
				g2.drawImage(img, a.getPosX(), a.getPosY(), Actor.getWidth(), Actor.getHeight(), null);
			}

			// draw schermate di transizione
			if (Model.getInstance().getGame().isHitted() || Model.getInstance().getGame().isLastHitted()
					|| Model.getInstance().getGame().isLevelFinish()) {

				g.setColor(Color.black);
				if (counter <= View.getInstance().getScreenWidth()) {
					g.fillRect(0, 0, counter, View.getInstance().getScreenHeight());
					counter += 50;

				} else if (counter < View.getInstance().getScreenWidth() + 241) {

					Graphics2D g2 = (Graphics2D) g;
					if (Model.getInstance().getGame().isLastHitted()) {
						g2.drawImage(gameOverImage, 0, 0, View.getInstance().getScreenWidth(),
								View.getInstance().getScreenHeight(), null);
					} else if (Model.getInstance().getGame().isLevelFinish()) {
						g2.drawImage(levelWonImage, 0, 0, View.getInstance().getScreenWidth(),
								View.getInstance().getScreenHeight(), null);
					} else {
						g2.drawImage(hittedImage, 0, 0, View.getInstance().getScreenWidth(),
								View.getInstance().getScreenHeight(), null);

					}
					counter += 1;

				} else {
					counter = 0;
					if (Model.getInstance().getGame().isHitted()) {
						Model.getInstance().loadLevel(Model.getInstance().getGame().getLevelPlaying());
						Model.getInstance().getGame().setHitted(false);

					} else if (Model.getInstance().getGame().isLevelFinish()) {
						Model.getInstance().getGame().setLevelFinish(false);
						if (Model.getInstance().getGame().getLevelPlaying() == 2) {
							Model.getInstance().getGame().setGameOver(true);
						} else
							Model.getInstance().loadLevel(Model.getInstance().getGame().getLevelPlaying() + 1);
						Model.getInstance().getGame()
								.setLevelPlaying(Model.getInstance().getGame().getLevelPlaying() + 1);
					} else {
						Model.getInstance().getGame().setGameOver(true);
						Model.getInstance().getGame().setLastHitted(false);
						Model.getInstance().getGame().setHitted(false);
					}
				}
			}
		}
	}
}
