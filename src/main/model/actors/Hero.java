package main.model.actors;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import main.controller.InputSystem;
import main.model.Model;

public class Hero extends DynamicActor {
	private InputSystem inputSystem;
	private int maxBombs = 3;
	private int bombsCreated = 0;
	private int bombStrenght = 3;
	// AttackTimer
	private long lastTime;
	private long countdown; // countdown
	private final long attackCooldown = 400; // wait time
//	private ArrayList<Bomb> bombs;

	public Hero(int posX, int posY) {
		super(posX, posY);
		setName("Hero");
		setPriority(100);
		setFrameCounter(0);
		setActive(true);
	//	bombs = new ArrayList<Bomb>();
	}


	public void update() {

		// check collisione con blast o nemici e se si muori
		

			
		// check collisione con muri e se si non ti muovi
		
		
		if (inputSystem.isSpacePressed() == true) {

			createBomb();}
		
		ArrayList <Actor> wallsBombs= Model.getInstance().getActors().stream().
				filter(actor -> actor.getName()=="Wall" ||actor.getName()=="Rock"||actor.getName()=="Bomb")
				.collect(Collectors .toCollection(ArrayList::new));
		
		
		
		if (inputSystem.isUpPressed() == true) {
			setDirection(Direction.UP);
		
			if (wallsBombs.stream().anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.UP))) return;
			move();
			//setPosY(getPosY() - getSpeed());
			//setRectangle();
			System.out.println("suuu");
		} else if (inputSystem.isDownPressed() == true) {
			setDirection(Direction.DOWN);
			if (wallsBombs.stream().anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.DOWN))) return;
			move();
//			if (wallsAndRocks.stream().anyMatch(actor -> !actor.equals(this)&& checkCollisionDowm(actor))) return;
			//setPosY(getPosY() + getSpeed());
			//setRectangle();
			System.out.println("giùùùù");
		} else if (inputSystem.isLeftPressed() == true) {
			setDirection(Direction.LEFT);
			if (wallsBombs.stream().anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.LEFT))) return;
			move();
//			if (wallsAndRocks.stream().anyMatch(actor -> !actor.equals(this)&& checkCollisionLeft(actor))) return;
			//setPosX(getPosX() - getSpeed());
			//setRectangle();
			System.out.println("leeeeft");
		} else if (inputSystem.isRightPressed() == true) {
			setDirection(Direction.RIGHT);
			if (wallsBombs.stream().anyMatch(actor -> Model.getInstance().checkCollision(this, actor, Direction.RIGHT))) return;
			move();
//			if (wallsAndRocks.stream().anyMatch(actor -> !actor.equals(this)&& checkCollisionRight(actor))) return;
			//setPosX(getPosX() + getSpeed());
			//setRectangle();
			System.out.println("riiighttt");
	} 
		
			//else if (inputSystem.isSpacePressed() == true) {
//
//			createBomb();
//		}
		setFrameCounter(getFrameCounter() + 1);
		if (getFrameCounter() > 24)
			setFrameCounter(0);

	}
	
	
	
	
	

	private void createBomb() {
		countdown -= System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if (countdown < 0) {
			if (bombsCreated < maxBombs) {
				System.out.println("boooooooooooooooooooooom");
				placeBomb(bombStrenght);
//				bombs.add(new Bomb(getPosX()-16, getPosY(), bombStrenght));
//				setPosX(getPosX()+16);
//				bombsCreated++;
			}
			countdown = attackCooldown;
		} else {
			System.out.println("aspetta");
		}
	}
	
	private void placeBomb(int bombStrenght) {
		if (getDirection() == Direction.RIGHT) {
			//bombs.add(new Bomb(getPosX()+1+getRectDimension()/2, getPosY(), bombStrenght));
			Model.getInstance().getActors().add(new Bomb(getPosX()+1+getRectDimension()/2, getPosY(), bombStrenght));
			setPosX(getPosX()-getRectDimension()/2);
			setRectangle();
			bombsCreated++;
		};
		if (getDirection() == Direction.LEFT) {
			//bombs.add(new Bomb(getPosX()-1-getRectDimension()/2, getPosY(), bombStrenght));
			Model.getInstance().getActors().add(new Bomb(getPosX()-1-getRectDimension()/2, getPosY(), bombStrenght));
			setPosX(getPosX()+getRectDimension()/2);
			setRectangle();
			bombsCreated++;
		};
		if (getDirection() == Direction.UP) {
			//bombs.add(new Bomb(getPosX(), getPosY()-1-getRectDimension()/2, bombStrenght));
			Model.getInstance().getActors().add(new Bomb(getPosX(), getPosY()-1-getRectDimension()/2, bombStrenght));
			setPosY(getPosY()+getRectDimension()/2);
			setRectangle();
			bombsCreated++;
		};
		if (getDirection() == Direction.DOWN) {
			//bombs.add(new Bomb(getPosX(), getPosY()+1+getRectDimension()/2, bombStrenght));
			Model.getInstance().getActors().add(new Bomb(getPosX(), getPosY()+1+getRectDimension()/2, bombStrenght));
			setPosY(getPosY()-getRectDimension()/2);
			setRectangle();
			bombsCreated++;
		};
	}
	
	
//	
//	private boolean checkCollisionUP(Actor a) {
//		//check angolo a sinistra in alto e check angolo a destra in alto
//		if (a.getRect().contains(getRect().getX(),getRect().getY()) ||
//				a.getRect().contains(getRect().getX()+getRectDimension(),getRect().getY())
//				)
//			return true;
//		return false;
//	}
//	private boolean checkCollisionDowm(Actor a) {
//		// check angolo a sinistra in basso e check angolo a destra in basso
//		if (a.getRect().contains(getRect().getX(),getRect().getY()+getRectDimension()) ||
//				a.getRect().contains(getRect().getX()+getRectDimension(),getRect().getY()+getRectDimension())
//				)
//			return true;
//		return false;
//	}
//	private boolean checkCollisionRight(Actor a) {
//		// check angolo a destra in alto e check angolo a destra in basso
//		if (a.getRect().contains(getRect().getX()+getRectDimension(),getRect().getY()) ||
//				a.getRect().contains(getRect().getX()+getRectDimension(),getRect().getY()+getRectDimension())
//				)
//			return true;
//		return false;
//	}
//	private boolean checkCollisionLeft(Actor a) {
//		//check angolo a sinistra in alto e check angolo a sinistra in basso
//		if (a.getRect().contains(getRect().getX(),getRect().getY()) ||
//				a.getRect().contains(getRect().getX(),getRect().getY()+getRectDimension())
//				)
//			return true;
//		return false;
//	}
	
	
	
	

	public int getBombsCreated() {
		return bombsCreated;
	}

//	public ArrayList<Bomb> getBombs() {
//		return bombs;
//	}
//
//	public void setBombs(ArrayList<Bomb> bombs) {
//		this.bombs = bombs;
//	}

	public int getMaxBombs() {
		return maxBombs;
	}

	public void setMaxBombs(int maxBombs) {
		this.maxBombs = maxBombs;
	}

	public int getBombStrenght() {
		return bombStrenght;
	}

	public void setBombStrenght(int bombStrenght) {
		this.bombStrenght = bombStrenght;
	}

	public void setBombsCreated(int bombsCreated) {
		this.bombsCreated = bombsCreated;
	}

	public InputSystem getInputSystem() {
		return inputSystem;
	}

	public void setInputSystem(InputSystem inputSystem) {
		this.inputSystem = inputSystem;
	}

}