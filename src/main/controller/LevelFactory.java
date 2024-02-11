package main.controller;

import java.util.concurrent.CopyOnWriteArrayList;

import main.model.actors.Actor;

public interface LevelFactory {
	
	public CopyOnWriteArrayList<Actor> loadLevel(int level);

}
