import lejos.nxt.*;

/**
 * 
 * 
 * 
 */
public class Robot {
	//
	private static Robot singleton;
	
	//
	private NXTRegulatedMotor xAxis;
	
	//
	private NXTRegulatedMotor yAxis;
	
	//	How many millis the motors need to go per 'pixel'
	private static final int MILLIS_PER_UNIT = 70;
	
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
	
	// The light sensor
	private LightSensor lightSensor;

	// The light trigger value. Lower value see more stuff as light.
	private static final int LIGHT_TRIGGER_VALUE = 550;

	// Robo Contruction site
	private Robot(){
		xAxis 		= Motor.B;
		yAxis 		= Motor.A;
		marker 		= Motor.C;
		lightSensor = new LightSensor(SensorPort.S1);

		lightSensor.setFloodlight(true);

		xAxis.setSpeed(100);
		yAxis.setSpeed(100);

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
		int x = xPos;
		if(x<0) x = 0;
		else if(x>=X_POS_MAX) x = X_POS_MAX-1;
		int amountToMove = x - this.xPos;

		if(amountToMove > 0) {
			xAxis.forward();
			try {
				Thread.sleep(70*amountToMove);
				this.xPos = x;
				xAxis.stop();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(amountToMove < 0) {
			xAxis.backward();
			try {
				Thread.sleep(70*Math.abs(amountToMove));
				this.xPos = x;
				xAxis.stop();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
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
		int y = yPos;
		if(y<0) y = 0;
		else if(y>=Y_POS_MAX) y = Y_POS_MAX-1;
		int amountToMove = y - this.yPos;
		if(amountToMove > 0) {
			yAxis.backward();
			try {
				Thread.sleep(70*amountToMove);
				this.yPos = y;
				yAxis.stop();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(amountToMove < 0) {
			yAxis.forward();
			try {
				Thread.sleep(70*Math.abs(amountToMove));
				this.yPos = y;
				yAxis.stop();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}	
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
	 * @return True if dark, False if light
	 */
	 public boolean readValue() {
	 	return (lightSensor.getNormalizedLightValue() < 550);
	 }	

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public boolean[][] scan() {
		boolean[][] output = new boolean[X_POS_MAX][Y_POS_MAX];
		byte c;
		for(byte b=0; b<Y_POS_MAX; b++) {
			setYPos(b);
			for(c=0; c<X_POS_MAX; c++) {
				setXPos(c);
				if(lightSensor.getNormalizedLightValue() < 500) {
					output[b][c] = true;
				}
				else {
					output[b][c] = false;
				}
			}
			b++;
			setYPos(b);
			for(c=X_POS_MAX-1; c>=0; c--) {
				setXPos(c);
				if(lightSensor.getNormalizedLightValue() < 500) {
					output[b][c] = true;
				}
				else {
					output[b][c] = false;
				}
			}
		}

		return output;
	}
}
