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
	
	private Motor xAxis;
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	
	private Motor yAxis;
	
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
	 * 
	 * 
	 */
	
	private int xPos;
	
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
	
	private Motor marker;
	
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
	public Robot(){
		super();
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	
	public Robot getInstance() {
		// TODO implement me
		return null;	
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	
	public int getXPos() {
		// TODO implement me
		return 0;	
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	
	public void setXPos(int xPos) {
		// TODO implement me	
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	
	public int getXPos() {
		// TODO implement me
		return 0;	
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	
	public void setYPos(int yPos) {
		// TODO implement me	
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	
	public void setMarker(boolean down) {
		// TODO implement me	
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

