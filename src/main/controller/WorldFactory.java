package main.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import main.model.actors.Actor;
import main.model.actors.Enemy;
import main.model.actors.Enemy.EnemyType;
import main.model.actors.Hero;
import main.model.actors.Wall;

public class WorldFactory {

	static CopyOnWriteArrayList<Actor> actors = new CopyOnWriteArrayList<Actor>();

	public static CopyOnWriteArrayList<Actor> loadWorld(String path, InputSystem inputSystem) {
		BufferedReader r;
		try {
			r = new BufferedReader(new FileReader(path));
			int ch;
			int x = 0;
			int y = 0;
			while ((ch = r.read()) !=-1) {
				char c = (char) ch;
				if (c == '-') {
					x += Actor.getWidth();
				}
				else if (c != '\n')
				{
					Actor actor = createActor(c, x, y, inputSystem);
					System.out.println(actor.getPosX());
					actors.add(actor);
					x += Actor.getWidth();
				}else {
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

	private static Actor createActor(char c, int x, int y, InputSystem inputSystem) throws Exception {
		switch (c) {
		case 'H':
			Hero h = new Hero(x, y);
			h.setInputSystem(inputSystem);
			System.out.println(h.getInputSystem());
			return h;
		case 'W':
			Wall w = new Wall(x,y,false);
			return w;
		case 'M':
			Wall r = new Wall(x,y,true);
			return r;
		case 'A':
			Enemy ea = new Enemy(x, y, EnemyType.TIPO1);
			return ea;
		case 'B':
			Enemy eb = new Enemy(x, y, EnemyType.TIPO3);
			return eb;
		default: throw new Exception("opoooooooooooooooooooooooooooo");
		}
		
	}

}
