import lejos.nxt.*;


/** 
 * CopyState.java
 * Copies the provided drawing.
 * 
 * @author TeamBeard
 */

public class CopyState {

	private Robot robot;

	private boolean[][] drawing;

	/**
	*	Simply grabs the robot instance and assumes control of it.
	*	Then, we make it scan the current paper, before we prompt the user to insert new paper, and print.
	*/
	public void init() {

		robot = robot.getInstance();
		drawing = robot.scan();

		LCD.clear();
		LCD.drawString("Insert paper..",1,1,false);

		Button.ENTER.waitForPressAndRelease();

		robot.draw(drawing);
	}
	
	public CopyState() {
		init();
	}
}
