package main.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Classe singleton che si occupa della gestione dell'audio di gioco.
 *
 */
public class AudioManager {

	private static AudioManager instance;
	private static Clip clipPlaying;

	public static AudioManager getInstance() {
		if (instance == null)
			instance = new AudioManager();
		return instance;
	}

	private AudioManager() {
	}

	/**
	 * Metodo per avviare la riproduzione di un file audio
	 * 
	 * @param filename il nome del file audio da riprodurre
	 * @param loop     se è true, il file audio viene riprodotto in loop
	 * 
	 */
	public void play(String filename, boolean loop) {
		File f = new File("./src/main/resources/sounds/" + filename);
		AudioInputStream audioIn;
		try {
			audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			if (loop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				clipPlaying = clip;
			}
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo per fermare la riproduzione del file audio in esecuzione ed avviarne
	 * un altro
	 * 
	 * @param filename il nome del file audio da riprodurre
	 * @param loop     se è true, il file audio viene riprodotto in loop
	 */
	public void switchMusic(String filename, boolean loop) {
		clipPlaying.stop();
		play(filename, loop);

	}

}