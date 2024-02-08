package main.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import main.model.actors.Actor;
import main.model.actors.Hero;
import main.model.actors.Wall;

public class WorldFactory {

	static ArrayList<Actor> actors = new ArrayList<Actor>();

	public static ArrayList<Actor> loadWorld(String path, InputSystem inputSystem) {
		BufferedReader r;
		try {
			r = new BufferedReader(new FileReader(path));
			int ch;
			int x = 0;
			int y = 0;
			while ((ch = r.read()) != -1) {
				char c = (char) ch;
				if (c == '-') {
					x += Actor.getWidth()+1;
				}
				else if (c != '\n')
				{
					Actor actor = createActor(c, x, y, inputSystem);
					System.out.println(actor.getPosX());
					actors.add(actor);
					x += Actor.getWidth()+1;
				}else {
					x = 0;
					y += Actor.getHeight()+1;
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			Wall w = new Wall(x,y);
			return w;
		default: throw new Exception("Exception message");
		}
		
	}

}
