package kiloboltgame;

import java.util.ArrayList;

/* This class will do the following:
 * 	1. Manage x, y coordinates and speed.
 * 	2. Make updates to speed and position.
 * 	3. Allow other classes to retrieve its x, y related variables
 * 		
 * 		Additionally the methods in the class will provide:
 * 			1. Constantly updated methods that are called on each iteration of the game loop.
 * 			2. Methods that are only called upon player input.
 * 			3. Helper methods that retrieve and change the value of variables in the class.
 * */
public class Hero {
	// In Java, Class Variables should be private so that only its methods can
	// change them.

	/*
	 * Variables: 1. centerX, centerY are the x, y coordinates of our robot
	 * character's center. 2. speedX, speed Y are the rate at which these x and
	 * y positions change. 3. jumped changes to true if the character is in the
	 * air, and reverts to false when grounded.
	 */
	// private int centerX = 100;
	// private int centerY = 382;

	/*
	 * NOTE: Individual pixel coordinates are given in (x,y); however, by
	 * convention and technological limitations, we choose the top left corner
	 * as (0,0). On a screen with resolution 1920 x 1080, then, the bottom right
	 * pixel has coordinates (1919, 1079) NOT (1920, 1080). This is because we
	 * begin counting from zero, not one, like all other computer constructs
	 * dealing with arrays, indexes, and sets etc.
	 */
	// added and changed for background
	// Constants are Here
	final int JUMPSPEED = -23;
	final int MOVESPEED = 5;
	final static int GROUND = 573;
	// end of background changes
	private static int centerX = 160;
	// using the center of the robot the ground is approx defined as y=660
	// changed for background private static int centerY = 573;
	private static int centerY = GROUND;
	// 'jumped' will be a state which is constant and change upon event or state
	// change
	private boolean jumped = false;
	// added and changed for background
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;

	private static Background bkground1 = StartingClass.getBkground1();
	private static Background bkground2 = StartingClass.getBkground2();

	// end changes for background
	
	private int speedX = 0;
	private int speedY = 1;
	
	// arraylist to handle the "bullets" each time the button to shoot is pressed
	// the bullets will be in an expanding array and store the projectile objects
	private ArrayList<Projectiles> projectiles = new ArrayList<Projectiles>();// arrayList of 'projectiles'

	
	public void update() {

		// Moves Character or Scrolls Background accordingly.
		// [NOTE: Speed can be negative, which means that a character
		// with negative speedX would move to the left.]

		/*
		 * In order for the game/application to draw the character in the proper
		 * location, centerX must constantly be updated; however, if the
		 * character is not moving (speedX == 0), then there is no need to
		 * update centerX by adding speed to it
		 */
		if (speedX < 0) {
			centerX += speedX;
		} // else
		if (speedX == 0 || speedX < 0) {/* changes for background */
			// background changes -
			// System.out.println("Do not scroll the background.");
			bkground1.setSpeedX(0);
			bkground2.setSpeedX(0);
		}
		/*
		 * else { once the character starts moving to the right, the character
		 * stays at the same location while the background scrolls. This is what
		 * we are doing here. If speed is zero, then we will not scroll the
		 * background. If the character's centerX coordinate is less than 240,
		 * the character can move freely. Else, the background wills scroll and
		 * stop moving the character.
		 */
		// if (centerX <= 150) {
		if (centerX <= 650 /* changes for background */ && speedX > 0) { 
			centerX += speedX;
		} /*
		 * else { System.out.println("Scroll Background Here"); } }
		 */
		if (speedX > 0 && centerX > 650){
			bkground1.setSpeedX(-MOVESPEED);
			bkground2.setSpeedX(-MOVESPEED);
		}
		
		// Updates Y Position
		// this is "gravity" constantly making sure the character constantly is
		// centered around position 'Y'
		// changes for background
		centerY += speedY;
		if (centerY + speedY >= GROUND /* 573 */) {
			centerY = GROUND /* 573 */;
		} /*
		 * else { centerY += speedY; }
		 */
		// Handles Jumping
		if (jumped == true) {
			speedY += 1;
			if (centerY + speedY >= GROUND /* 573 */) {
				centerY = GROUND /* 573 */;
				speedY = 0;
				jumped = false;
			}
		}
		// Prevents going beyond X coordinate of 0
		// if (centerX + speedX <= 60) {
		if (centerX + speedX <= 96) {
			// centerX = 61;
			centerX = 98;
		}
	}

	public void moveRight() {
		// speedX = 6;
		// speedX = 10;
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		// speedX = -6;
		// speedX = -10;
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}
	
	public void stopLeft(){
		setMovingLeft(false);
		stop();
	}
	public void stopRight(){
		setMovingRight(false);
		stop();
	}
	public void stop() {
		// set originally to: speedX = 0;
		
		//NOTE: the 
		if (isMovingRight() == false && isMovingLeft() == false) {
            speedX = 0;
        }

        if (isMovingRight() == false && isMovingLeft() == true) {
            moveLeft();
        }

        if (isMovingRight() == true && isMovingLeft() == false) {
            moveRight();
        }
	}

	public void jump() {
		if (jumped == false) {
			// speedY = -15;
			speedY = JUMPSPEED;
			jumped = true;
		}
	}
	/*
	 * This method simply creates a new Projectile, labels it p, and adds
	 * it to the projectiles ArrayList. We create this 50 pixels to the right 
	 * and 25 pixels above the center of the robot, which is where the gun is
	 * */
	public void shoot(){
		Projectiles heroBullet = new Projectiles(centerX + 50, centerY - 25);
		projectiles.add(heroBullet);
	}
	
	/*
	 * Getters and setters allow the private variables to be access as the
	 * private variables are only accessible by this class and its methods
	 * directly and cannot be used unless getters and setters are made to allow
	 * access indirectly
	 */

	public static int getCenterX() {
		return centerX;
	}

	public static int getCenterY() {
		return centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		Hero.centerX = centerX;
		// was this 'Hero'.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		Hero.centerY = centerY;
		// was this 'Hero'.centerY = centerY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	public boolean isDucked() {
        return ducked;
    }

    public void setDucked(boolean ducked) {
        this.ducked = ducked;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

	public ArrayList<Projectiles> getProjectiles() {
		return projectiles;
	}  
}
