
// http://www.kilobolt.com/day-8-animations.html

package kilboltgame.framework;

import java.util.ArrayList;

public class Animation {
	
	/* objectives of the animation class and constructor:
	 * 
	 * 	We need the following methods:
	 * 		- One that will add a frame to a new animation.
	 * 		- One that will update the current frame with the appropriate image.
	 * 		- One that returns the current frame's image, so that we can paint it in the StartingClass.
	 * 		- One that returns the current frame's index (index being the numerical location on a list of objects). 
	 * */
	
	// - The 'ArrayList frames' will contain AnimFrame objects (to be created later) that
	// 		have two values: an image and a duration it is displayed.
	private ArrayList frames;
	private int currentFrame;
	// - currentFrame refers to the integer value index (the numerical location in the list starting with 0) of 
	// 		the current frame in the ArrayList (the current image shown).
	private long animTime; //long takes up more memory than int but can hold more accurate numbers.
	// - The third variable animTime will keep track of how much time has elapsed since the current image was 
	// 		displayed, so that when animTime reaches a certain value, we can switch over to the 
	// 		next frame.
	private long totalDuration;
	// - The last variable, totalDuration, refers to the amount of time that each frame 
	// 		(image) will be displayed for.
	
	public void Animation(){
		
		// Since the animation will have an unknown "finite" amount of frames use ArrayList
		
	}
}
