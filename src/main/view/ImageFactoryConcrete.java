package main.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.model.actors.Actor;
import main.model.actors.Direction;
import main.model.actors.Hero;

public class ImageFactoryConcrete implements ImageFactory {

	private BufferedImage heroUpOne;
	private BufferedImage heroUpTwo;
	private BufferedImage heroDownOne;
	private BufferedImage heroDownTwo;
	private BufferedImage heroLeftOne;
	private BufferedImage heroLeftTwo;
	private BufferedImage heroRightOne;
	private BufferedImage heroRightTwo;
	private BufferedImage enemyOneUpOne;
	private BufferedImage enemyOneUpTwo;
	private BufferedImage enemyOneDownOne;
	private BufferedImage enemyOneDownTwo;
	private BufferedImage enemyOneLeftOne;
	private BufferedImage enemyOneLeftTwo;
	private BufferedImage enemyOneRightOne;
	private BufferedImage enemyOneRightTwo;
	private BufferedImage enemyTwoUpOne;
	private BufferedImage enemyTwoUpTwo;
	private BufferedImage enemyTwoDownOne;
	private BufferedImage enemyTwoDownTwo;
	private BufferedImage enemyTwoLeftOne;
	private BufferedImage enemyTwoLeftTwo;
	private BufferedImage enemyTwoRightOne;
	private BufferedImage enemyTwoRightTwo;
	private BufferedImage enemyThreeUpOne;
	private BufferedImage enemyThreeUpTwo;
	private BufferedImage enemyThreeDownOne;
	private BufferedImage enemyThreeDownTwo;
	private BufferedImage enemyThreeLeftOne;
	private BufferedImage enemyThreeLeftTwo;
	private BufferedImage enemyThreeRightOne;
	private BufferedImage enemyThreeRightTwo;
	private BufferedImage bombSmall;
	private BufferedImage bombMedium;
	private BufferedImage bombBig;
	private BufferedImage blastSmall;
	private BufferedImage blastMedium;
	private BufferedImage blastBig;
	private BufferedImage wallDestructible;
	private BufferedImage wallNotDestructible;
	
	

	public ImageFactoryConcrete() {
		try {
			heroUpOne = ImageIO.read(new File("./src/main/resources/images/boy_up_1.png"));
			heroUpTwo = ImageIO.read(new File("./src/main/resources/images/boy_up_2.png"));
			heroDownOne = ImageIO.read(new File("./src/main/resources/images/boy_down_1.png"));
			heroDownTwo = ImageIO.read(new File("./src/main/resources/images/boy_down_2.png"));
			heroLeftOne = ImageIO.read(new File("./src/main/resources/images/boy_left_1.png"));
			heroLeftTwo = ImageIO.read(new File("./src/main/resources/images/boy_left_2.png"));
			heroRightOne = ImageIO.read(new File("./src/main/resources/images/boy_right_1.png"));
			heroRightTwo = ImageIO.read(new File("./src/main/resources/images/boy_right_2.png"));
			
			enemyOneUpOne = ImageIO.read(new File("./src/main/resources/images/boy_up_1.png"));
			heroUpTwo = ImageIO.read(new File("./src/main/resources/images/boy_up_2.png"));
			heroDownOne = ImageIO.read(new File("./src/main/resources/images/boy_down_1.png"));
			heroDownTwo = ImageIO.read(new File("./src/main/resources/images/boy_down_2.png"));
			heroLeftOne = ImageIO.read(new File("./src/main/resources/images/boy_left_1.png"));
			heroLeftTwo = ImageIO.read(new File("./src/main/resources/images/boy_left_2.png"));
			heroRightOne = ImageIO.read(new File("./src/main/resources/images/boy_right_1.png"));
			heroRightTwo = ImageIO.read(new File("./src/main/resources/images/boy_right_2.png"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public BufferedImage returnImage(Actor a) {

		if (a instanceof Hero) {
			Hero x = (Hero) a;
			if (x.getDirection() == Direction.UP) return a.getFrameCounter() < 12 ? heroUpOne : heroUpTwo;
			else if (x.getDirection() == Direction.DOWN) return a.getFrameCounter() < 12 ? heroDownOne : heroDownTwo;
			else if (x.getDirection() == Direction.LEFT)return a.getFrameCounter() < 12 ? heroLeftOne : heroLeftTwo;
			else if (x.getDirection() == Direction.RIGHT) return a.getFrameCounter() < 12 ? heroRightOne : heroRightTwo;
			else return heroDownOne;
		}

		
		
		return null;
	}

}
