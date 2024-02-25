package main.view.imageFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.model.Model;
import main.model.actors.Actor;
import main.model.actors.Direction;
import main.model.actors.Enemy;
import main.model.actors.Enemy.EnemyType;
import main.model.actors.Hero;

public class EnemyImageFactory implements ImageFactory {

	private BufferedImage freezerUpOne;
	private BufferedImage freezerUpTwo;
	private BufferedImage freezerDownOne;
	private BufferedImage freezerDownTwo;
	private BufferedImage freezerLeftOne;
	private BufferedImage freezerLeftTwo;
	private BufferedImage freezerRightOne;
	private BufferedImage freezerRightTwo;
	
	private BufferedImage jeethUpOne;
	private BufferedImage jeethUpTwo;
	private BufferedImage jeethDownOne;
	private BufferedImage jeethDownTwo;
	
	private BufferedImage ginewLeftOne;
	private BufferedImage ginewLeftTwo;
	private BufferedImage ginewRightOne;
	private BufferedImage ginewRightTwo;
	
	public EnemyImageFactory() {
		try {
			freezerUpOne = ImageIO.read(new File("./src/main/resources/images/enemy/freezer_up_1.png"));
			freezerUpTwo = ImageIO.read(new File("./src/main/resources/images/enemy/freezer_up_2.png"));
			freezerDownOne = ImageIO.read(new File("./src/main/resources/images/enemy/freezer_down_1.png"));
			freezerDownTwo = ImageIO.read(new File("./src/main/resources/images/enemy/freezer_down_2.png"));
			freezerLeftOne = ImageIO.read(new File("./src/main/resources/images/enemy/freezer_left_1.png"));
			freezerLeftTwo = ImageIO.read(new File("./src/main/resources/images/enemy/freezer_left_2.png"));
			freezerRightOne = ImageIO.read(new File("./src/main/resources/images/enemy/freezer_right_1.png"));
			freezerRightTwo = ImageIO.read(new File("./src/main/resources/images/enemy/freezer_right_2.png"));

			jeethUpOne = ImageIO.read(new File("./src/main/resources/images/enemy/jeeth_up_1.png"));
			jeethUpTwo = ImageIO.read(new File("./src/main/resources/images/enemy/jeeth_up_2.png"));
			jeethDownOne = ImageIO.read(new File("./src/main/resources/images/enemy/jeeth_down_1.png"));
			jeethDownTwo = ImageIO.read(new File("./src/main/resources/images/enemy/jeeth_down_2.png"));
			
			ginewLeftOne = ImageIO.read(new File("./src/main/resources/images/enemy/ginew_left_1.png"));
			ginewLeftTwo = ImageIO.read(new File("./src/main/resources/images/enemy/ginew_left_2.png"));
			ginewRightOne = ImageIO.read(new File("./src/main/resources/images/enemy/ginew_right_1.png"));
			ginewRightTwo = ImageIO.read(new File("./src/main/resources/images/enemy/ginew_right_2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public BufferedImage returnImage(Actor a) {

		String avatar = Model.getInstance().getGame().getPlayer().getAvatar();

		if (a instanceof Enemy) {
			Enemy x = (Enemy) a;

			if (x.getEnemyType()== EnemyType.FREEZER) {

				if (x.getDirection() == Direction.UP)
					return a.getFrameCounter() < 12 ? freezerUpOne : freezerUpTwo;
				else if (x.getDirection() == Direction.DOWN)
					return a.getFrameCounter() < 12 ? freezerDownOne : freezerDownTwo;
				else if (x.getDirection() == Direction.LEFT)
					return a.getFrameCounter() < 12 ? freezerLeftOne : freezerLeftTwo;
				else if (x.getDirection() == Direction.RIGHT)
					return a.getFrameCounter() < 12 ? freezerRightOne : freezerRightTwo;
				else
					return freezerDownOne;
			}
			
			else if (x.getEnemyType()== EnemyType.JEETH) {

				if (x.getDirection() == Direction.UP)
					return a.getFrameCounter() < 12 ? jeethUpOne : jeethUpTwo;
				else if (x.getDirection() == Direction.DOWN)
					return a.getFrameCounter() < 12 ? jeethDownOne : jeethDownTwo;
				else
					return jeethDownOne;
			}
			
			else if (x.getEnemyType()== EnemyType.GINEW) {

				if (x.getDirection() == Direction.RIGHT)
					return a.getFrameCounter() < 12 ? ginewRightOne : ginewRightTwo;
				else if (x.getDirection() == Direction.LEFT)
					return a.getFrameCounter() < 12 ? ginewLeftOne : ginewLeftTwo;
				else
					return ginewRightOne;
			}
			
		}

		return null;
	}

}
