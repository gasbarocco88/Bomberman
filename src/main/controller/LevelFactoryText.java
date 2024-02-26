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

public class LevelFactoryText implements LevelFactory {


	public CopyOnWriteArrayList<Actor> loadLevel(int level) {
		CopyOnWriteArrayList<Actor> actors = new CopyOnWriteArrayList<Actor>();
		BufferedReader reader;
		String path = MessageFormat
				.format("/home/rocco/Documenti/università/bombermanWindow/src/main/resources/level_{0}.txt", level);
		try {
			reader = new BufferedReader(new FileReader(path));
			int ch;
			int x = 0;
			int y = 0;
			while ((ch =reader.read()) != -1) {
				char c = (char) ch;
				if (c == '-') {
					x += Actor.getWidth();
				} 
				else if(c=='\r') {
					continue;
				}
				else if(c=='\n') {
					x = 0;
					y += Actor.getHeight();
				}
				
				else {

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
		System.out.println(actors);

		return actors;
	}

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
