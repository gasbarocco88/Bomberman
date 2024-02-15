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

	private void readStatsTxt() {
		BufferedReader reader;
		String path = "/home/rocco/Documenti/università/bombermanWindow/src/main/resources/playerStats.txt";
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

	public Optional<Player> createPlayer(String nickname, String avatar) {
		Player p = null;
		if (!checkPlayerExist(nickname, avatar)) {
			p = new Player(nickname, avatar, 0, 0, 0, 0, 0);
			// lo aggiungo alla mappa in modo da poter dumpare la mappa daccapo dopo
			playerstats.put(nickname + "-" + avatar, p);
			saveStatsTxt();
		}
		return Optional.of(p);
	}

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

	public Player loadPlayer(String id) {
		return playerstats.get(id);
	}

	public void deletePlayer(String id) {
		playerstats.remove(id);
		saveStatsTxt();
	}

	public HashMap<String, Player> getPlayerstats() {
		return playerstats;
	}

	public void saveStatsTxt() {
		try {
			PrintWriter writer = new PrintWriter("./src/main/resources/playerStats2.txt", "UTF-8");
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

}
