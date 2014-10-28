import lejos.nxt.*;

/**DrawingRobot.java
 * 
 * <p>
 * The main class.
 * We just initializes the menuState
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
