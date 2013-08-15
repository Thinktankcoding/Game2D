package kiloboltgame;

public class Background {
	// variables set to access the background movement
	private int bkgroundX;
	private int bkgroundY;
	private int speedX;

	// constructor with variables to build the background object
	public Background(int xAxis, int yAxis) {
		bkgroundX = xAxis;
		bkgroundY = yAxis;
		// background will be static so 'speed' will be 0 initially
		speedX = 0;
	}

	//
	public void updatebkground() {
		bkgroundX += speedX;
		// check to see if the background is less than the maximum length of the
		// 2 background images
		if (bkgroundX <= -2160) {
			bkgroundX += 4320;
		}

	}

	public int getBkgroundX() {
		return bkgroundX;
	}

	public int getBkgroundY() {
		return bkgroundY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setBkgroundX(int bkgroundX) {
		this.bkgroundX = bkgroundX;
	}

	public void setBkgroundY(int bkgroundY) {
		this.bkgroundY = bkgroundY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

}
