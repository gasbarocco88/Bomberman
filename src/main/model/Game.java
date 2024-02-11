package main.model;


public class Game {
	
	private Player player;
	private String avatar;
	private int lifes;
	private int point;
	private boolean isRunning; 
	private int levelPlaying;
	
	
	
	public Game() {
		setLifes(3);
		setPoint(0);
		setRunning(true);	
		setLevelPlaying(1);
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	

}
