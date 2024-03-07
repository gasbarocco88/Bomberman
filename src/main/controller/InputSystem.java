package main.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Classe che si occupa della gestione degli input da tastiera, estendendo
 * l'interfaccia KeyListener. Ad ogni tasto premuto o rilasciato, viene
 * switchata a true/false la rispettiva variabile booleana.
 */
public class InputSystem implements KeyListener {

	private boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, pausePressed;

	/**
	 * Switcha a true la rispettiva variabile booleana quando un tasto viene
	 * premuto.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if (code == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
		if (code == KeyEvent.VK_P) {
			pausePressed = true;
		}
	}

	/**
	 * Switcha a false la rispettiva variabile booleana quando un tasto viene
	 * rilasciato.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_SPACE) {
			spacePressed = false;
		}
		if (code == KeyEvent.VK_P) {
			pausePressed = false;
		}
	}

	/**
	 * Metodo dell'interfaccia KeyListener da implementare forzatamente ma che è
	 * inutilizzato.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	// getters
	public boolean isUpPressed() {
		return upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public boolean isSpacePressed() {
		return spacePressed;
	}

	public boolean isPausePressed() {
		return pausePressed;
	}

}
