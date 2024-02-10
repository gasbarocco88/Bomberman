package main.view.panels;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import main.model.Model;
import main.model.actors.Actor;
import main.model.actors.Blast;
import main.model.actors.Bomb;
import main.model.actors.Direction;
import main.model.actors.Enemy;
import main.model.actors.Enemy.EnemyType;
import main.model.actors.Hero;
import main.model.actors.Item;
import main.model.actors.Wall;

public class GamePanel extends JPanel {

	public GamePanel() {
		panelSetup();
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

		CopyOnWriteArrayList<Actor> actors = Model.getInstance().getActors();
		Collections.sort(actors);

		for (Actor a : actors) {
			BufferedImage img;
			Graphics2D g2 = (Graphics2D) g;

			if (a instanceof Hero) {
				Hero x = (Hero) a;
				if (x.getDirection() == Direction.UP)

				{
					if (a.getFrameCounter() < 12) {
						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_up_1.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), Actor.getWidth(), Actor.getHeight(), null);
							g2.fillRect((int) a.getRect().getX(), (int) a.getRect().getY(), a.getRectDimension(),
									a.getRectDimension());
							g2.setColor(Color.pink);

							// g2.drawImage(img,(int) a.getRect().getX(), (int)a.getRect().getY(),
							// Actor.getWidth(), Actor.getHeight(), null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						}
					} else {
						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_up_2.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
							g2.fillRect((int) a.getRect().getX(), (int) a.getRect().getY(), a.getRectDimension(),
									a.getRectDimension());
							g2.setColor(Color.pink);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				else if (x.getDirection() == Direction.DOWN) {
					if (a.getFrameCounter() < 12) {
						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_down_1.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_down_2.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				else if (x.getDirection() == Direction.RIGHT) {
					if (a.getFrameCounter() < 12) {
						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_right_1.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_right_2.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				else {
					if (a.getFrameCounter() < 12) {
						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_left_1.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							img = ImageIO.read(new File(
									"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/boy_left_2.png"));
							g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

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
			}
			else if (a instanceof Enemy) {
				Enemy x = (Enemy) a;
				if (x.getEnemyType()== EnemyType.TIPO3) {

					try {
						img = ImageIO.read(new File(
								"/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/orc_down_1.png"));
						g2.drawImage(img, a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight(), null);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				
				else if (x.getEnemyType()== EnemyType.TIPO1) {

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
