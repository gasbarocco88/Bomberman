package main.model.actors;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Comparator;
import java.util.Objects;

public abstract class Actor implements Comparable<Actor>{
	private String name;
	private int posY, posX;
	private final static int WIDTH  = 32;
	private final static int HEIGHT  = 32;
	private int priority;
	private int frameCounter;
	private boolean active;
	private Rectangle rect = new Rectangle();
	private final int rectDimension = 28;
	


	public Actor(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
		setRectangle();
	}

	public abstract void update();

	public void setRectangle() {
		rect.setRect(posX+2, posY+2, rectDimension,rectDimension);
	}
	public int compareTo(Actor st){  
		if(priority==st.priority)  
		return 0;  
		else if(priority>st.priority)  
		return 1;  
		else  
		return -1;  }
		
		
	@Override
	public int hashCode() {
		return Objects.hash(active, name, posX, posY, priority);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return active == other.active && Objects.equals(name, other.name) && posX == other.posX && posY == other.posY
				&& priority == other.priority;
	}

	public Rectangle getRect() {
		return rect;
	}
	
	public Point getActorMidpoint() {
		int xCoord = getPosX()+(getWidth()/2);
		int yCoord = getPosY()+(getHeight()/2);
		return new Point(xCoord,yCoord);
	}
	
	//esempio baricentro sta a (30,34) cioè cella (0,1) a coordinate (0,32)
	// int 30/32 = 0  -> 0*32 = 0
	// int 34/32 = 1  -> 1*32 = 32
	public Point getTileCoordinates(Point center) {
		int xCoord =((int) center.getX()/getWidth()) * getWidth();
		int yCoord = ((int) center.getY()/getHeight()) * getHeight();
		return new Point(xCoord,yCoord);	
	}
	
	
	public void setActorInTile(Actor a){
		a.setPosX(posX);
	}


	public int getRectDimension() {
		return rectDimension;
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

	public int getFrameCounter() {
		return frameCounter;
	}

	public void setFrameCounter(int frameCounter) {
		this.frameCounter = frameCounter;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}


	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


}
