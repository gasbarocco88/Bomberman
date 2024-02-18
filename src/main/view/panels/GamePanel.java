package main.view.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.controller.PlayerManager;
import main.model.Model;
import main.model.actors.Actor;
import main.model.actors.Blast;
import main.model.actors.Bomb;
import main.model.actors.Direction;
import main.model.actors.Enemy;
import main.model.actors.Enemy.EnemyType;
import main.view.ImageFactoryConcrete;
import main.view.View;
import main.model.actors.Hero;
import main.model.actors.Item;
import main.model.actors.Wall;

public class GamePanel extends JPanel {
	private ImageFactoryConcrete imgFactory;
	private BufferedImage backgroundImage;
	private BufferedImage hittedImage;
	private BufferedImage gameOverImage;
	private BufferedImage levelWonImage;
	private int counter;

	public GamePanel() {
		panelSetup();
		imgFactory = new ImageFactoryConcrete();
		try {
			backgroundImage = ImageIO.read(new File("/home/rocco/Immagini/giraffa.jpg"));
			hittedImage = ImageIO.read(new File("./src/main/resources/images/hitted.png"));
			gameOverImage = ImageIO.read(new File("./src/main/resources/images/gameOver.png"));
			levelWonImage = ImageIO.read(new File("./src/main/resources/images/gameOver.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
	}

	public void panelSetup() {
		setBackground(Color.gray);
		setOpaque(true);
		setFocusable(true);
		setDoubleBuffered(true);
		requestFocus();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!Model.getInstance().getGame().isGameOver()) {

			g.drawImage(backgroundImage, 0, 0, this);

			CopyOnWriteArrayList<Actor> actors = Model.getInstance().getActors();
			Collections.sort(actors);

			for (Actor a : actors) {
				BufferedImage img;
				Graphics2D g2 = (Graphics2D) g;

				if (a instanceof Hero) {
					img = imgFactory.returnImage(a);
					g2.drawImage(img, a.getPosX(), a.getPosY(), Actor.getWidth(), Actor.getHeight(), null);
				}

				else if (a instanceof Bomb) {
					try {
						img = ImageIO.read(new File(
								"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/blueheart.png"));
						g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (a instanceof Blast) {
					try {
						img = ImageIO.read(new File(
								"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/redslime_down_2.png"));
						g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (a instanceof Item) {

					Item item = (Item) a;

					if (item.getItemType() == Item.ItemType.SPEED) {
						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/pickaxe.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							e.printStackTrace();
						}

					} else if (item.getItemType() == Item.ItemType.BOMBNUM) {
						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/potion_red.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							e.printStackTrace();
						}

					} else if (item.getItemType() == Item.ItemType.BOMBSTRENGHT) {
						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/lantern.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (item.getItemType() == Item.ItemType.LIFE) {
						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/key.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/key.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}

				else if (a instanceof Wall) {

					Wall x = (Wall) a;
					if (!x.isDestructible()) {

						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/shield_blue.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							e.printStackTrace();
						}

					} else {

						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/shield_wood.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else if (a instanceof Enemy) {
					Enemy x = (Enemy) a;
					if (x.getEnemyType() == EnemyType.TIPO3) {

						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/orc_down_1.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							e.printStackTrace();
						}

					}

					else if (x.getEnemyType() == EnemyType.TIPO1) {

						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/oldman_down_2.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							e.printStackTrace();
						}

					}

				}

			}
			if (Model.getInstance().getGame().isHitted() || Model.getInstance().getGame().isLastHitted()
					|| Model.getInstance().getGame().isLevelFinish()) {

				g.setColor(Color.black);
				if (counter <= View.getInstance().getScreenWidth()) {
					g.drawRect(0, 0, counter, View.getInstance().getScreenHeight());
					g.fillRect(0, 0, counter, View.getInstance().getScreenHeight());
					counter += 50;

				} else if (counter < View.getInstance().getScreenWidth() + 51) {

					Graphics2D g2 = (Graphics2D) g;
					if (Model.getInstance().getGame().isLastHitted()) {
						g2.drawImage(gameOverImage, 0, -150, View.getInstance().getScreenWidth(),
								View.getInstance().getScreenWidth(), null);
					} else if (Model.getInstance().getGame().isLevelFinish()) {
						g2.drawImage(levelWonImage, 0, -50, View.getInstance().getScreenWidth(),
								View.getInstance().getScreenWidth(), null);
					} else {
						g2.drawImage(hittedImage, 0, -50, View.getInstance().getScreenWidth(),
								View.getInstance().getScreenWidth(), null);

					}
					counter += 50;

				} else {
					System.out.println(counter);
					counter = 0;
					System.out.println(counter);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (Model.getInstance().getGame().isHitted() && (!Model.getInstance().getGame().isLastHitted())) {
						System.out.println("hittato");
						//Model.getInstance().loadLevel(Model.getInstance().getGame().getLevelPlaying());
						Model.getInstance().getGame().setHitted(false);

					} else if (Model.getInstance().getGame().isLevelFinish()) {
						System.out.println("livellofinito");
						Model.getInstance().getGame().setLevelFinish(false);
						Model.getInstance().updatePlayerPoints(false);
						if (Model.getInstance().getGame().getLevelPlaying() == 2) {
							PlayerManager.getInstance().updatePlayerStats(Model.getInstance().getGame().getPlayer());
							Model.getInstance().getGame().setGameOver(true);
						} 
						else Model.getInstance().loadLevel(Model.getInstance().getGame().getLevelPlaying() + 1);
						Model.getInstance().getGame().setLevelPlaying(Model.getInstance().getGame().getLevelPlaying() + 1);
					}
					else {
						System.out.println("gameover");
						//Model.getInstance().updatePlayerPoints(true);
						//PlayerManager.getInstance().updatePlayerStats(Model.getInstance().getGame().getPlayer());
						Model.getInstance().getGame().setGameOver(true);
						Model.getInstance().getGame().setLastHitted(false);
						Model.getInstance().getGame().setHitted(false);

					}
					
				}
			}
		}

	}

}

//try {
//int frameCounter = a.getFrameCounter();
//Direction direction = a.getDirection();
//
//switch (direction) {
//case UP:
//	if (frameCounter < 12) {
//		img = ImageIO.read(new File(
//				"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_up_1.png"));
//		break;
//	} else {
//		img = ImageIO.read(new File(
//				"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_up_2.png"));
//		break;
//	}
//case DOWN:
//	if (frameCounter < 12) {
//		img = ImageIO.read(new File(
//				"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_down_1.png"));
//		break;
//	} else {
//		img = ImageIO.read(new File(
//				"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_down_2.png"));
//		break;
//	}
//case LEFT:
//	if (frameCounter < 12) {
//		img = ImageIO.read(new File(
//				"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_left_1.png"));
//		break;
//	} else {
//		img = ImageIO.read(new File(
//				"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_left_2.png"));
//		break;
//	}
//
//case RIGHT:
//	if (frameCounter < 12) {
//		img = ImageIO.read(new File(
//				"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_right_1.png"));
//		break;
//	} else {
//		img = ImageIO.read(new File(
//				"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_right_2.png"));
//		break;
//	}
//default:
//	img = ImageIO.read(new File(
//			"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/orc_down_1.png"));
//
//}
