package main.model;

//implementa observer sul game per aggiornare i risultati una volta finito?
public class Player {
	private final String nickname;
	private final String avatar;
	private int totGamesPlayed;
	private int totGamesWon;
	private int totLevelWon;
	private int highestScore;
	private int accumulatedScore;

	public Player(String nickname, String avatar, int totGamesPlayed, int totGamesWon, int totLevelWon,
			int highestScore, int accumulatedScore) {
		super();
		this.nickname = nickname;
		this.avatar = avatar;
		this.totGamesPlayed = totGamesPlayed;
		this.totGamesWon = totGamesWon;
		this.totLevelWon = totLevelWon;
		this.highestScore = highestScore;
		this.accumulatedScore = accumulatedScore;
	}

	@Override
	public String toString() {
		return "Player [nickname=" + nickname + ", avatar=" + avatar + ", totGamesPlayed=" + totGamesPlayed
				+ ", totGamesWon=" + totGamesWon + ", totLevelWon=" + totLevelWon + ", highestScore=" + highestScore
				+ ", accumulatedScore=" + accumulatedScore + "]";
	}

	public String getNickname() {
		return nickname;
	}

	public int getTotGamesPlayed() {
		return totGamesPlayed;
	}

	public void setTotGamesPlayed(int totGamesPlayed) {
		this.totGamesPlayed = totGamesPlayed;
	}

	public int getTotGamesWon() {
		return totGamesWon;
	}

	public void setTotGamesWon(int totGamesWon) {
		this.totGamesWon = totGamesWon;
	}

	public int getTotLevelWon() {
		return totLevelWon;
	}

	public void setTotLevelWon(int totLevelWon) {
		this.totLevelWon = totLevelWon;
	}

	public int getHighestScore() {
		return highestScore;
	}

	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}

	public int getAccumulatedScore() {
		return accumulatedScore;
	}

	public void setAccumulatedScore(int accumulatedScore) {
		this.accumulatedScore = accumulatedScore;
	}

	public String getAvatar() {
		return avatar;
	}

}
