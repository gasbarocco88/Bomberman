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

	private CopyOnWriteArrayList<Actor> actors = new CopyOnWriteArrayList<Actor>();

	public CopyOnWriteArrayList<Actor> loadLevel(int level) {
		BufferedReader r;
		String path = MessageFormat
				.format("/home/rocco/Documenti/università/bombermanWindow/src/main/resources/level_{0}.txt", level);
		try {
			r = new BufferedReader(new FileReader(path));
			int ch;
			int x = 0;
			int y = 0;
			while ((ch = r.read()) != -1) {
				char c = (char) ch;
				if (c == '-') {
					x += Actor.getWidth();
				} else if (c != '\n') {

					Actor actor = createActor(c, x, y);
					if (actor instanceof Item) {
						actors.add(actor);
						actors.add(new Wall(actor.getPosX(), actor.getPosY(), true));
					} else {
						actors.add(actor);
					}
					x += Actor.getWidth();
				} else {
					x = 0;
					y += Actor.getHeight();
				}

			}
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
		case 'A':
			Enemy ea = new Enemy(x, y, EnemyType.TIPO1);
			return ea;
		case 'B':
			Enemy eb = new Enemy(x, y, EnemyType.TIPO3);
			return eb;
		case 'I':
			Item i = new Item(x, y, ItemType.WIN);
			return i;
		default:
			throw new Exception("opoooooooooooooooooooooooooooo");
		}

	}

}
