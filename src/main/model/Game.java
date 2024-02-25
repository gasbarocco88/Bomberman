package main.model;


public class Game {
	
	private Player player;
	private int lifes;
	private int score;
	private int levelPlaying;
	private boolean isRunning; 
	private boolean isLevelFinish;
	private boolean isHitted;
	private boolean lastHitted;
	private boolean isGameOver;
	
	
	public Game() {
		setLifes(3);
		setScore(0);
		setLevelPlaying(1);
		setRunning(true);
		setLevelFinish(false);
		setHitted(false);
		setLastHitted(false);
		setGameOver(false);
	}
	
	
	
	@Override
	public String toString() {
		return "Game [player=" + player + ", lifes=" + lifes + ", score=" + score + ", levelPlaying=" + levelPlaying
				+ ", isRunning=" + isRunning + ", isLevelFinish=" + isLevelFinish + ", isHitted=" + isHitted
				+ ", lastHitted=" + lastHitted + ", isGameOver=" + isGameOver + "]";
	}



	public boolean isLastHitted() {
		return lastHitted;
	}



	public boolean isLevelFinish() {
		return isLevelFinish;
	}



	public void setLevelFinish(boolean isLevelFinish) {
		this.isLevelFinish = isLevelFinish;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
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
