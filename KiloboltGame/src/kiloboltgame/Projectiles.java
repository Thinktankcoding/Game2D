package kiloboltgame;

import java.util.ArrayList;

// this will be used for bullets, bombs, orbs or anything that can be thrown, shot, etc.

public class Projectiles {
	
	// variables for the position of the projectiles and rate of change in movement
	private int x, y, speedX;
	boolean visible;
	
	// constructor with 2 parameters to represent the position of the top left 
	// corner of each painted projectile object
	public Projectiles(int startX,int startY){
		// pass the local/encapsulated variables "x & y" the  
		// methods variables " startX & startY"
		x = startX;
		y = startY;
		//declare the constant rate
		speedX = 21;
		// boolean to be used to declare the state of the projectile
		visible = true;
	}
	public void update(){
		
		x += speedX;
		// this statement will only show the projectile while 
		// on the screen in the x boundaries
		if (x > 1280){
			visible = true;
		}
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getSpeedX() {
		return speedX;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
