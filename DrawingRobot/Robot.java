import lejos.nxt.*;

/**
 * 
 * 
 * 
 */

public class Robot
{
	//
	private static Robot singleton;
	
	//
	private NXTRegulatedMotor xAxis;
	
	//
	private NXTRegulatedMotor yAxis;
	
	//
	private int degreesPerUnit;
	
	//The maximum positions of the x-axis, in pixels.
	public final static int X_POS_MAX = 64;
	
	// The printer heads current position on the x-axis 
	private int xPos;
	
	//The maximum positions of the y-axis, in pixels.
	public final static int Y_POS_MAX = 64;

	// The printer heads current position on the y-axis 
	private int yPos;

	// The motor that controls the marker (up/down)
	private NXTRegulatedMotor marker;
	
	// Boolean for if the marker is up or down
	private boolean isDown;
	
	// Robo Contruction site
	private Robot(){
		xAxis 		= Motor.B;
		yAxis 		= Motor.A;
		marker 		= Motor.C;

		setXPos(0);
		setYPos(0);
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public static Robot getInstance() {
		if(singleton==null)
		{
			singleton = new Robot();
		}
		return singleton;	
	}
	
	/**
	 * Returns the printer heads current position on the x-axis
	 * 
	 * 
	 * 
	 */
	public int getXPos() {
		return xPos;	
	}
	
	/**
	 * Moves the printer head to a specified point on the x-axis
	 * 
	 * 
	 * 
	 */
	public void setXPos(int xPos) {
		this.xPos = xPos;	
	}
	
	/**
	 * Returns the printer heads current position on the y-axis
	 * 
	 * 
	 * 
	 */
	public int getYPos() {
		return yPos;	
	}
	
	/**
	 * Moved the printer head to a specified point on the y-axis
	 * 
	 * 
	 * 
	 */
	public void setYPos(int yPos) {
		this.yPos = yPos;	
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public void setMarker(boolean down) {
		marker.rotateTo(down?30:0);
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public void toggleMarker() {
		marker.rotateTo(isDown?0:30);
		isDown = !isDown;
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public boolean[][] scan() {
		return null;	
	}
}
