package drawingRobot;


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
	
	private Robot singleton;
	
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
	*
	*
	*/
	private final static int xPosMax = 49;

	/**
	 * 
	 * 
	 * 
	 * 
	 */

	private int xPos;
	
	/**
	*
	*
	*/
	private final static int yPosMax = 70;

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
		marker = down;
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	
	public boolean[][] scan() {
		// TODO implement me
		return null;	
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	
	public void calibrate() {
		// TODO implement me	
	}
	
}

