import lejos.nxt.*;

/**
 * 
 * 
 * 
 */

public class Robot
{
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private static Robot singleton;
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private NXTRegulatedMotor xAxis;
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private NXTRegulatedMotor yAxis;
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private int degreesPerUnit;
	

	/**
	*	The maximum positions of the x axis, in pixels.
	*/
	public final static int X_POS_MAX = 49;

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private int xPos;
	
	/**
	*	The maximum positions of the y axis, in pixels.
	*/
	public final static int Y_POS_MAX = 70;

	/**
	 * 
	 * 
	 * 
	 * 
	 */	
	private int yPos;

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private NXTRegulatedMotor marker;
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private boolean isDown;
	
	/**
	 * 
	 * 
	 * 
	 */
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
	 * Get current xPos
	 * 
	 * 
	 * 
	 */
	public int getXPos() {
		return xPos;	
	}
	
	/**
	 * Set xPos
	 * 
	 * 
	 * 
	 */
	public void setXPos(int xPos) {
		this.xPos = xPos;	
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public int getYPos() {
		return yPos;	
	}
	
	/**
	 * 
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
