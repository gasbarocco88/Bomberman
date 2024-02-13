package main.model;


public class Game {
	
	private Player player;
	private int lifes;
	private int point;
	private int levelPlaying;
	private boolean isRunning; 
	private boolean isHitted;
	private boolean lastHitted;
	private boolean isGameOver;
	
	
	public Game() {
		setLifes(3);
		setPoint(0);
		setLevelPlaying(1);
		setRunning(true);
		setHitted(false);
		setLastHitted(false);
		setGameOver(false);
	}
	
	
	
	public boolean isLastHitted() {
		return lastHitted;
	}



	public void setLastHitted(boolean lastHitted) {
		this.lastHitted = lastHitted;
	}



	public boolean isHitted() {
		return isHitted;
	}



	public void setHitted(boolean isHitted) {
		this.isHitted = isHitted;
	}



	public int getLevelPlaying() {
		return levelPlaying;
	}

	public void setLevelPlaying(int levelPlaying) {
		this.levelPlaying = levelPlaying;
	}

	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getLifes() {
		return lifes;
	}
	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public boolean isRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}



	public boolean isGameOver() {
		return isGameOver;
	}



	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	

}
