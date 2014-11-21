import lejos.nxt.*;

/**DrawingRobot.java
 * 
 * 
 * The main class.
 * We just initialize the menuState
 *
 * @author TeamBeard
 */
public class DrawingRobot
{
	
	public DrawingRobot(){
		new MenuState().init();
	}

	public static void main(String[] args) {
		new DrawingRobot();	
	}
}
