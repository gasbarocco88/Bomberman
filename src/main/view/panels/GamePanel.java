package main.view.panels;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import main.model.Model;
import main.model.actors.Actor;
import main.model.actors.Hero;

public class GamePanel extends JPanel {

	private Hero h;

	// da fare singleton
//	public GamePanel(Model model) {
//		this.model = model;
//		panelSetup();
//	}
	public GamePanel() {
		panelSetup();
	}

	public void panelSetup() {
		setBackground(Color.black);
		setOpaque(true);
		setFocusable(true); // serve a mettere il famePanel focused per ricevere gli input
		setDoubleBuffered(true);
		requestFocus();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		ArrayList<Actor> actors = Model.getInstance().getActors();
		//System.out.println(actors);
		for (Actor a : actors) {
			if (a instanceof Hero) {
				Hero x = (Hero)a;
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.cyan);
				g2.setColor(Color.cyan);
				g2.fillOval(x.getPosX(),x.getPosY(),48, 48);
				g2.dispose();
//				BufferedImage img;
//				try {
//					img = ImageIO.read(new File("/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/hero.png"));
//					Graphics2D g2g = img.createGraphics();
//					g2g.drawRect(x.getPosX(), x.getPosY(), 60, 60);
//					g2g.dispose();
//					super.paintComponent(g2g);
//					g2g.drawImage(img, x.getPosX(), x.getPosY(), this);
//					//System.out.println(x.getInputSystem());
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			else {
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.red);
				g2.fillOval(a.getPosX(),a.getPosY(),48, 48);
				g2.dispose();
//				BufferedImage img;
//				try {
//					img = ImageIO.read(new File("/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/wall.png"));
//					Graphics2D g2g = img.createGraphics();
//					g2g.drawRect(a.getPosX(), a.getPosY(), 60, 60);
//					g2g.setColor(Color.cyan);
//					g2g.fillOval(a.getPosX(),a.getPosY(),48, 48);
//					g2g.dispose();
//
//					super.paintComponent(g2g);
//					g2g.drawImage(img, a.getPosX(), a.getPosY(), this);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}

		}
//		
//		g2.setColor(Color.cyan);
//		g2.fillOval(h.getPosX(),h.getPosY(),48, 48);
//		
	}

	public Hero getH() {
		return h;
	}

	public void setH(Hero h) {
		this.h = h;
	}

}
