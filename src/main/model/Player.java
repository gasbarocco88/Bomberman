package main.model;

//implementa observer sul game per aggiornare i risultati una volta finito?
public class Player {
	private String name;
	private String avatar;
	private int totGamesPlayed;
	private int totGamesWon;
	private int totLevelWon;
	private int highestRecord;
	private int accumulatedPoint;

	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
