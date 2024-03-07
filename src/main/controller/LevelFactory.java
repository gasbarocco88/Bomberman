package main.controller;

import java.util.concurrent.CopyOnWriteArrayList;

import main.model.actors.Actor;

/**
 * Interfaccia che definisce un metodo per il caricamento di un livello di gioco
 */
@FunctionalInterface
public interface LevelFactory {

	public abstract CopyOnWriteArrayList<Actor> loadLevel(int level);

}
