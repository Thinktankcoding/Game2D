package kiloboltgame;

public class Enemy {

	// enemy class for characteristics of basic enemy
	/* enemy should have these characteristics:
	 * Status: 
	 * 		Max Health
	 * 		Current Health
	 * Power (damage output)
	 * Movement:
	 * 		Speed
	 * 	Position:
	 * 		X coordinate
	 * 		Y coordinate
	 */
	
	private int maxHealth, currrentHealth, power, speedX, speedY,centerX, centerY;
	
	// background variable to allow the movement to continue in the 
	// same direction as the "hero"
	
	private Background bg = StartingClass.getBkground1(); 
	
	
	// determines "behavior" of the enemy class
	public void update(){
		
		centerX += speedX;
		//pull the information from the background object
		speedX = bg.getSpeedX();
	}
	public void attack(){
		
	}
	public void death(){
		
	}
	
	//these will allow the manipulation of encapsulated variables(i.e. "private int var;")
	public int getMaxHealth() {
		return maxHealth;
	}
	public int getCurrrentHealth() {
		return currrentHealth;
	}
	public int getPower() {
		return power;
	}
	public int getSpeedX() {
		return speedX;
	}
	public int getSpeedY() {
		return speedY;
	}
	public int getCenterX() {
		return centerX;
	}
	public int getCenterY() {
		return centerY;
	}
	public Background getBg() {
		return bg;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public void setCurrrentHealth(int currrentHealth) {
		this.currrentHealth = currrentHealth;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}
	public void setCenterY(int centerY) {
		this.centerX = centerY;
	}
	public void setBg(Background bg) {
		this.bg = bg;
	}
	
	// the getters & setters will allow the class to retrieve and manipulate the variables 
	// declared in this class
	
}
