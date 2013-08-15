package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

/* 
 * (1)'implement Runnable' is required for Threads require a run method that determines what 
 * will happen in that thread 
 * */
public class StartingClass extends Applet implements
		Runnable, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// this line above was added to prevent warnings
	private String sgameTitle = "Q-Bot Alpha";
	// hero character
	private Hero hero;
	// enemy characters
	private Chopper chopper1, chopper2;
	//visual assets such as hero image/animation or background images
	private Image image, character, background, currentSprite, characterDown, characterJumped, chopperStatic; 
	// NOTE: currentSprite, which will dynamically change to character, characterDown 
	// or characterJumped according to what the Robot robot is doing
	private Graphics second;
	// character variable
	private URL base;
	private String characterImage = "data/character.png";
	private String backgroundImage = "data/background.png";
	private String characterJumpedImage = "data/characterup.png";
	private String characterDownImage = "data/characterdown.png";
	private String chopperImage = "data/chopper.png";
	// background variables
	private static Background bkground1, bkground2;
	
	// character and enemy start positions
	private int charX, charY, chopperX, chopperY;
	
	
	/*
	 * (0 @Override ) The @Override tests for errors upon compilation. In this
	 * case, we are using it to annotate that we are overriding methods from a
	 * parent class. It informs you when you make mistakes.
	 */
	@Override
	/*
	 * (3 init() ) When the applet runs for the first time, it will run the
	 * init() method (much like how a typical program runs the main(String
	 * args[]) method)
	 * 
	 * the init method will define parameters for the applet 1. Size of the
	 * applet 2. Background color 3. Applet Title
	 */
	public void init() {
		// size for resolution
		setSize(1280, 720);
		// background color using the Color superclass with ".Black" is the
		// constant importing the color
		setBackground(Color.BLACK);
		// this will make sure your game/application will take immediate focus
		// on start to begin the game loop
		setFocusable(true);
		// this will allow the game/application to pick up key presses and
		// releases for the current Applet
		addKeyListener(this);
		/*
		 * import 'Frame' to create a Frame object called frame. This is
		 * slightly complicated, but just know that the first line assigns the
		 * applet window to the frame variable an the second line just sets the
		 * title to be Q-Bot Alpha
		 */
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle(sgameTitle);
		// Character
		// this will assign a value to 'character'
		try {
			base = getDocumentBase();
		} catch (Exception e) {

		}
		// character & image setup location
		character = getImage(base, characterImage);
		// currentSprite will be dynamic and changes based on events/key presses.
		currentSprite = character;
		background = getImage(base, backgroundImage);
		characterJumped = getImage(base, characterJumpedImage);
		characterDown = getImage(base, characterDownImage);
		chopperStatic = getImage(base, chopperImage);
		// end of character & image setup location
	}

	@Override
	public void start() {
		// a constructor will handle this given the constructors parameters
		// using these coordinates pins the image to the top left
		bkground1 = new Background(0, 0);
		bkground2 = new Background(2160, 0);
		// initialize the object hero created from the Robot.class
		hero = new Hero();
		chopper1 = new Chopper(544, 540);
		chopper2 = new Chopper(1120, 540);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// (1 Thread )'thread' will allow the game/app to run simultaneous
		// process/methods
		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// game loop
		while (true) {
			hero.update();
			// this is going to change the character image based on conditions		
			if (hero.isJumped()){
				currentSprite = characterJumped;
			}else if(hero.isJumped() == false && hero.isDucked() == false){
				currentSprite = character;
			}
			// projectiles 
			ArrayList projectiles = hero.getProjectiles();
			for(int i = 0; i < projectiles.size();i++){
				Projectiles p = (Projectiles) projectiles.get(i);
				if(p.visible == true){
					p.update();
				}else{
					projectiles.remove(i);
				}
				
			}
			// projectiles 
			
			/*
			 * never defined update() in the Chopper class; however, 
			 * since the Chopper class inherited the Enemy class, calling update() 
			 * here will automatically call the update() method in the Enemy class. 
			 * This is inheritance in action
			 * */
			chopper1.update();
			chopper2.update();
			bkground1.updatebkground();
			bkground2.updatebkground();
			/*
			 * (2 repaint() ) built in method - calls the paint method (in which
			 * we draw objects onto the screen), but every 17 milliseconds, the
			 * paint method will be called.
			 */
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	// Character
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(),
					this.getHeight());
			second = image.getGraphics();
		}
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		g.drawImage(image, 0, 0, this);
		}

	// this section will be used to draw the graphics & character or update the
	// character on the screen
	public void paint(Graphics g) {
		// draws background
		g.drawImage(background, bkground1.getBkgroundX(), bkground1.getBkgroundY(), this);
		g.drawImage(background, bkground2.getBkgroundX(), bkground2.getBkgroundY(), this);
		
		// projectiles 
		ArrayList projectiles = hero.getProjectiles();
		for(int i = 0; i < projectiles.size();i++){
			Projectiles p = (Projectiles) projectiles.get(i);
			g.setColor(Color.YELLOW);
			g.fillRect(p.getX(), p.getY(), 16, 8);
		}
		// projectiles 
		// draw the 'hero'
		g.drawImage(currentSprite/*was 'character'*/, Hero.getCenterX() - 98, Hero.getCenterY() - 96, this);
		g.drawImage(chopperStatic/*was 'character'*/, chopper1.getCenterX() + 600, chopper1.getCenterY() - 5, this);
		g.drawImage(chopperStatic/*was 'character'*/, chopper2.getCenterX() + 450, chopper2.getCenterY() - 0, this);
		
	}

	// Character

	@Override
	public void keyPressed(KeyEvent e) {
		// ***************************************************************************************
		// NOTE: this will be for physical keys currently till port to Android
		// ***************************************************************************************
		/*
		 * (3 'e' )'e' is the object or variable you created to associate with
		 * the method keyPressed and KeyEvent class
		 */

		// switch statements compares a 'key' in this instance then checks the
		// matching variable to
		// perform the proper statements or actions and return what was pressed
		// (i.e. Up, Down, etc.)
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("move up");
			hero.jump();
			break;
		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			//System.out.println("Down was pressed");
			if (hero.isJumped() == false){
				hero.setDucked(true);
				hero.setSpeedX(0);
			}
			break;
		case KeyEvent.VK_LEFT:
			hero.moveLeft();
			hero.setMovingLeft(true);
			break;
		case KeyEvent.VK_RIGHT:
			hero.moveRight();
			hero.setMovingRight(true);
			break;
		case KeyEvent.VK_SPACE:
			System.out.println("Jumping");
			hero.jump();
		case KeyEvent.VK_CONTROL:
			if (hero.isDucked() == false && hero.isJumped() == false) {
				hero.shoot();
			}
			break;
		}
		// end of switch statement

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// switch statements compares a 'key' in this instance then checks the
		// matching variable to
		// perform the proper statements or actions and return what was pressed
		// (i.e. Up, Down, etc.)
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;
		case KeyEvent.VK_DOWN:
			// System.out.println("Down was released");
			currentSprite = character; 
			hero.setDucked(false);
			break;
		case KeyEvent.VK_LEFT:
			hero.stopLeft();
			break;
		case KeyEvent.VK_RIGHT:
			hero.stopRight();
			break;
		case KeyEvent.VK_SPACE:
			System.out.println("Stop Jumping. (space)");
			//hero.stop();
			break;
		}
		// end of switch statement

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	public static Background getBkground1() {
		return bkground1;
	}

	public static Background getBkground2() {
		return bkground2;
	}

}
