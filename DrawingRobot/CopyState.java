import lejos.nxt.*;


/**CopyState.java
 *
 * Copies the provided drawing.
 * 
 * @author TeamBeard
 */

public class CopyState {

	Robot robot;

	boolean[][] drawing;

	public void init() {

		robot = robot.getInstance();
		drawing = robot.scan();

		LCD.clear();
		LCD.drawString("Insert copy paper",1,1,false);

		Button.ENTER.waitForPressAndRelease();

		robot.draw(drawing);
	}
}








