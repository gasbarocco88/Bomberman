package main.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import main.model.actors.Actor;
import main.model.actors.Enemy;
import main.model.actors.Enemy.EnemyType;
import main.model.actors.Hero;
import main.model.actors.Item;
import main.model.actors.Item.ItemType;
import main.model.actors.Wall;

/**
 * Classe factory che si occupa della creazione e caricamento di una mappa di
 * gioco, letta da un file txt.
 */
public class LevelFactoryText implements LevelFactory {

	/**
	 * Metodo per la creazione di un livello di gioco. La tipologia e la posizione
	 * degli attori da creare viene letta da un file txt e tali informazioni vengono
	 * passate a un metodo di appoggio che crea effettivamente l'attore. Lo shift
	 * delle posizioni dei vari attori viene calcolato sulla base della dimensione
	 * degli attori stessi.
	 * 
	 * 
	 * @param level il numero del livello da caricare
	 */
	public CopyOnWriteArrayList<Actor> loadLevel(int level) {
		CopyOnWriteArrayList<Actor> actors = new CopyOnWriteArrayList<Actor>();
		BufferedReader reader;
		String path = MessageFormat.format("./src/main/resources/level_{0}.txt", level);
		try {
			reader = new BufferedReader(new FileReader(path));
			int ch;
			int x = 0;
			int y = 0;
			while ((ch = reader.read()) != -1) {
				char c = (char) ch;
				if (c == '-') {
					x += Actor.getWidth();
				} else if (c == '\r') {
					continue;
				} else if (c == '\n') {
					x = 0;
					y += Actor.getHeight();
				} else {
					Actor actor = createActor(c, x, y);
					if (actor instanceof Item) {
						actors.add(actor);
						actors.add(new Wall(actor.getPosX(), actor.getPosY(), true));
					} else {
						actors.add(actor);
					}
					x += Actor.getWidth();
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actors;
	}

	/**
	 * Metodo di appoggio per la creazione degli attori del gioco, rispetta la
	 * seguente legenda
	 * 
	 * H: hero
	 * W: un muro non distruttibile
	 * M: un muro distruttibile
	 * G: nemico Ginew
	 * F: nemico Freezer
	 * J: nemico Jeeth
	 * I: l'item che permette di superare il livello
	 * -: niente
	 * 
	 * @param c il carattere letto dal file txt che rappresenta la tipologia di
	 *          attore da creare
	 * @param x la coordinata x della posizione dell'attore
	 * @param y la coordinata y della posizione dell'attore
	 * @return l'attore creato
	 * @throws Exception
	 */
	private Actor createActor(char c, int x, int y) throws Exception {
		switch (c) {
		case 'H':
			Hero h = new Hero(x, y);
			return h;
		case 'W':
			Wall w = new Wall(x, y, false);
			return w;
		case 'M':
			Wall r = new Wall(x, y, true);
			return r;
		case 'G':
			Enemy eg = new Enemy(x, y, EnemyType.GINEW);
			return eg;
		case 'F':
			Enemy ef = new Enemy(x, y, EnemyType.FREEZER);
			return ef;
		case 'J':
			Enemy ej = new Enemy(x, y, EnemyType.JEETH);
			return ej;
		case 'I':
			Item i = new Item(x, y, ItemType.WIN);
			return i;
		default:
			throw new Exception("Errore nel file del livello");
		}

	}

}
