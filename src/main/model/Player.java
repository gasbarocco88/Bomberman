package main.model;

/**
 * Classe per la gestione di un player, ovvero dell'utente che sta giocando la
 * partita, e delle sue statistiche. La combinazione del nickname + l'avatar di
 * gioco (ovvero del personaggio che si vuole comandare) non è ripetibile per
 * più player.
 */
public class Player {
	private final String nickname;
	private final String avatar;
	private int totGamesPlayed;
	private int totGamesWon;
	private int totLevelWon;
	private int highestScore;
	private int accumulatedScore;

	/**
	 * Costruttore del player
	 * 
	 * @param nickname:         nome del player
	 * @param avatar:           personaggio scelto
	 * @param totGamesPlayed:   totale delle partite fatte
	 * @param totGamesWon:      totale delle partite vinte
	 * @param totLevelWon:      totale dei livelli di gioco superati
	 * @param highestScore:     massimo punteggio ottenuto in una partita
	 * @param accumulatedScore: punteggio cumulativo di tutte le partite giocate
	 */
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

	// getters and setters
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
