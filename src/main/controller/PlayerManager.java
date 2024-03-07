package main.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;

import main.model.Player;

/**
 * Classe singleton che si occupa della gestione dei player e delle loro
 * statistiche di gioco. Tali informazioni vengono lette, all'istanziamento
 * della classe, da un file txt, che viene poi sovrascritto con le nuove
 * statistiche alla fine di ogni partita. Fino a quando la partita non è
 * conclusa, le statistiche dei giocatori vengono mantenute in una Hashmap
 * interna, la cui chiave è composta dal nickname+avatar del player; non è
 * perciò possibile creare un nuovo player che abbia lo stesso nickname e lo
 * stesso avatar se questa combinazione è stata già utilizzata in precedenza.
 */
public class PlayerManager {
	static private PlayerManager instance;
	private HashMap<String, Player> playerstats = new HashMap<String, Player>();

	private PlayerManager() {
		readStatsTxt();
	}

	static public PlayerManager getInstance() {
		if (instance == null)
			instance = new PlayerManager();
		return instance;
	}

	/**
	 * Metodo per la creazione di un nuovo player, con statistiche azzerate, che
	 * viene salvato sul file txt.
	 * 
	 * @param nickname: il nome del player da creare
	 * @param avatar:   il personaggio utilizzato dal player
	 * @return il player appena creato
	 */
	public Player createPlayer(String nickname, String avatar) {
		Player p = null;
		if (!checkPlayerExist(nickname, avatar)) {
			p = new Player(nickname, avatar, 0, 0, 0, 0, 0);
			playerstats.put(nickname + "-" + avatar, p);
			saveStatsTxt();
		}
		return p;
	}

	/**
	 * Metodo per l'aggiornamento delle statistiche di un player che vengono salvate
	 * sul file txt.
	 * 
	 * @param p: il player di cui salvare le statistiche
	 */
	public void updatePlayerStats(Player p) {
		String pKey = p.getNickname() + "-" + p.getAvatar();
		playerstats.put(pKey, p);
		saveStatsTxt();
	}

	/**
	 * Metodo per il caricamento delle statistiche di ciascun player. Viene
	 * utilizzato nella visualizzazione del ranking dei vari player nel Player
	 * Panel.
	 * 
	 * @return un array di arrays: ciascun array interno contiene tutte le
	 *         informazioni di un player, questi sono ordinati per punteggio
	 *         cumulativo
	 */
	public String[][] loadRanks() {
		String[][] players = new String[playerstats.size()][7];
		int index = 0;
		for (Entry<String, Player> entry : playerstats.entrySet()) {
			Player value = entry.getValue();
			String[] pl = new String[7];
			pl[0] = value.getNickname();
			pl[1] = value.getAvatar();
			pl[2] = String.valueOf(value.getTotGamesPlayed());
			pl[3] = String.valueOf(value.getTotGamesWon());
			pl[4] = String.valueOf(value.getTotLevelWon());
			pl[5] = String.valueOf(value.getHighestScore());
			pl[6] = String.valueOf(value.getAccumulatedScore());
			players[index] = pl;
			index++;
		}
		Arrays.sort(players, (a, b) -> Integer.compare(Integer.parseInt(b[6]), Integer.parseInt(a[6])));
		return players;
	}

	/**
	 * Metodo che restituisce il player presente nell'hashmap interna, cercato per id 
	 * @param id: chiave dell'hashmap costituita da "nome" + "-" + "avatar" 
	 * @return il player con tutte le sue statistiche
	 */
	public Player loadPlayer(String id) {
		return playerstats.get(id);
	}

	/**
	 * Metodo per eliminare un player sia dall'hashmap interna che dal file txt.
	 * @param id: chiave dell'hashmap costituita da "nome" + "-" + "avatar" 
	 */
	public void deletePlayer(String id) {
		playerstats.remove(id);
		saveStatsTxt();
	}

	/**
	 * Metodo per il salvataggio delle statistiche aggiornate; legge le informazioni presenti
	 * nell'hashmap interna e le converte in un file txt che sovrascrive quello originale.  
	 */
	public void saveStatsTxt() {
		try {
			PrintWriter writer = new PrintWriter("./src/main/resources/playerStats.txt", "UTF-8");
			StringBuilder headers = new StringBuilder();
			headers.append("Nickname,");
			headers.append("Avatar,");
			headers.append("TotGamesPlayed,");
			headers.append("TotGamesWon,");
			headers.append("TotLevelWon,");
			headers.append("HighestScore,");
			headers.append("AccumulatedScore");
			writer.println(headers);

			for (Entry<String, Player> entry : playerstats.entrySet()) {
				StringBuilder line = new StringBuilder();
				line.append(entry.getValue().getNickname() + ",");
				line.append(entry.getValue().getAvatar() + ",");
				line.append(entry.getValue().getTotGamesPlayed() + ",");
				line.append(entry.getValue().getTotGamesWon() + ",");
				line.append(entry.getValue().getTotLevelWon() + ",");
				line.append(entry.getValue().getHighestScore() + ",");
				line.append(entry.getValue().getAccumulatedScore());
				writer.println(line);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void readStatsTxt() {
		BufferedReader reader;
		String path = "./src/main/resources/playerStats.txt";
		try {
			reader = new BufferedReader(new FileReader(path));
			reader.readLine();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineSplitted = line.split(",");
				String nickname = lineSplitted[0];
				String avatar = lineSplitted[1];
				int totGamesPlayed = Integer.parseInt(lineSplitted[2]);
				int totGamesWon = Integer.parseInt(lineSplitted[3]);
				int totLevelWon = Integer.parseInt(lineSplitted[4]);
				int highestScore = Integer.parseInt(lineSplitted[5]);
				int accumulatedScore = Integer.parseInt(lineSplitted[6]);
				Player p = new Player(nickname, avatar, totGamesPlayed, totGamesWon, totLevelWon, highestScore,
						accumulatedScore);
				String pKey = nickname + "-" + avatar;
				playerstats.put(pKey, p);
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean checkPlayerExist(String nickname, String avatar) {
		String pKey = nickname + "-" + avatar;
		if (playerstats.containsKey(pKey)) {
			return true;
		}
		return false;
	}

	public HashMap<String, Player> getPlayerstats() {
		return playerstats;
	}

}
