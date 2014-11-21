import lejos.nxt.*;

/** Robot.java
 * This class handles all comunication with hardware such as motors and sensors,
 * to serve as an abstraction between the robot and our algorithms.
 * We have designed this class with the singelton design pattern, as described by
 * [Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides (1994), "Design Patterns: Elements of Reusable Object-Oriented Software", Pearson Education]
 * 
 * @author TeamBeard
 */
public class Robot {
	// The singelton instance. See getInstance()
	private static Robot singleton;
	
	// The Motor that moves the read/write head on the x-axis
	private NXTRegulatedMotor xAxis;
	
	// The motor that moves the read/write head on the y-axis
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
	private boolean isDown = false;
	
	// The light sensor
	private LightSensor lightSensor;

	// The light trigger value. Lower value see more stuff as light.
	private static final int LIGHT_TRIGGER_VALUE = 550;

	// Private constructor. See below.
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
	 * This is the singelton program design pattern.
	 * We want one and only one instance of the robot class to
	 * avoid conflict of motor control and such.
	 * We achieve this by keeping the constructor private,
	 * and only use it here in this method.
	 * We use lazy initialization. This means that we don't create
	 * an instance if we are not asked for it.
	 */
	public static Robot getInstance() {
		if(singleton==null)
		{
			singleton = new Robot();
		}
		return singleton;	
	}
	
	/**
	 * @return The printer head current position on the y-axis
	 */
	public int getXPos() {
		return xPos;	
	}
	
	/**
	 * @param The position on the x-axis to where the printer head should go.
	 */
	public void setXPos(int xPos) {
		setXPos(xPos, MILLIS_PER_UNIT);
	}
	
	public void setXPos(int xPos, int speed) {
		int x = xPos;
		if(x<0) x = 0;
		else if(x>=X_POS_MAX) x = X_POS_MAX-1;
		int amountToMove = x - this.xPos;

		if(amountToMove > 0) {
			xAxis.forward();
			try {
				Thread.sleep(speed*amountToMove);
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
				Thread.sleep(speed*Math.abs(amountToMove));
				this.xPos = x;
				xAxis.stop();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return The printer head current position on the y-axis
	 */
	public int getYPos() {
		return yPos;	
	}
	
	/**
	 * @param The position on the y-axis to where the printer head should go.
	 */
	public void setYPos(int yPos) {
		int y = yPos;
		if(y<0) y = 0;
		else if(y>=Y_POS_MAX) y = Y_POS_MAX-1;
		int amountToMove = y - this.yPos;
		if(amountToMove > 0) {
			yAxis.backward();
			try {
				Thread.sleep((MILLIS_PER_UNIT-5)*amountToMove);
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
				Thread.sleep(MILLIS_PER_UNIT*Math.abs(amountToMove));
				this.yPos = y;
				yAxis.stop();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * @param true sets the marker down, false lifts it up.
	 */
	public void setMarker(boolean down) {
		marker.rotateTo(down?-46:0);
	}
	
	/**
	 * Toggles the marker. If it is currently up, we move it down, and vice versa.
	 */
	public void toggleMarker() {
		setMarker(!isDown);
		isDown = !isDown;
	}

	/**
	 * @return True if dark, False if light
	 */
	 public boolean readValue() {
	 	return (lightSensor.getNormalizedLightValue() < 550);
	 }	

	/**
	 * While scanning, the thread is blocked. Scanning takes several minutes,
	 * as the read/write head moves over the surface.
	 * 
	 * @return a two dimensional boolean array where false is a white pixel and true is dark.
	 */
	public boolean[][] scan() {
		LCD.clear();
		LCD.drawString("Scanning...", 0,1,false);
		boolean[][] output = new boolean[X_POS_MAX][Y_POS_MAX];

		setMarker(false);
		setXPos(0);
		setYPos(0);

		boolean right = true;
		boolean[] dataline;
		for(int i=0; (Button.ESCAPE.isUp() && i<Y_POS_MAX); i++) {
			LCD.drawString(i+"/63", 11, 3, false);

			dataline = readLine(right);
			right = !right;

			for(byte b=0; b<dataline.length; b++) {
				LCD.setPixel(b, getYPos(), (dataline[b]?1:0));
			}

			output[i] = dataline;
			if(getYPos() < Y_POS_MAX-1) {
				setYPos(getYPos()+1);
				Sound.beep();
			}
		}

		// Reset
		setXPos(0);
		setYPos(0);
		xAxis.flt();
		yAxis.flt();
		marker.flt();
		Sound.twoBeeps();
		return output;
	}

	/**
	 * While drawing, the thread is blocked. Drawing takes several minutes,
	 * as the read/write head moves over the surface.
	 */
	public void draw(boolean[][] barray) {
		LCD.clear();
		setMarker(false);
		setXPos(0);
		setYPos(0);

		// push the drawing to the LCD screen
		for(int i=0; i<barray.length; i++) {
			for(int j=0; j<barray[0].length; j++) {
				LCD.setPixel(i, j, (barray[i][j]?1:0));
			}
		}

		for(int i=0; (Button.ESCAPE.isUp() && i<barray.length); i++) {
			LCD.drawString((i+1)+"/64", 11, 3, false);

			boolean[] line = barray[i];
			boolean empty = true;
			for(boolean b : line) {
				if(b) {
					empty = false;
					break;
				}
			}

			if(!empty) {
				writeLine(line);
			}

			if(getYPos() < Y_POS_MAX-1) {
				setYPos(getYPos()+1);
				Sound.beep();
			}
		}

		// Reset
		setMarker(false);
		setXPos(0);
		setYPos(0);
		xAxis.flt();
		yAxis.flt();
		marker.flt();

		Sound.twoBeeps();
	}

	private boolean[] readLine(boolean right) {
		boolean[] dataline = new boolean[64];

		if(right) {
			setXPos(0);
		}	
		else {
			setXPos(X_POS_MAX-1);
		}

		Thread t = new Thread() {
			@Override
			public void run() {
				for(byte b=0; (b<(byte)dataline.length && !isInterrupted() && Button.ESCAPE.isUp()); b++) {
					try {
						sleep(MILLIS_PER_UNIT);
					}
					catch(InterruptedException e) {
						// Nothing
					}
					dataline[b] = readValue();
				}
			}
		};
		t.setDaemon(true);
		t.start();
		setXPos(right?X_POS_MAX-1:0);
		t.interrupt();

		return flip(dataline);
	}

	private void writeLine(boolean[] dataline) {
		setXPos(X_POS_MAX-1);

		boolean black = false;
		for(byte b=0; (b<dataline.length && Button.ESCAPE.isUp()); b++) {
			if(dataline[b] != black) {
				setXPos(dataline.length-b+(black?1:0));
				setMarker(!black);
				black = !black;
			}
		}
	}

	private boolean[] flip(boolean[] barray) {
		boolean[] output = new boolean[barray.length];
		for(int i=0; i<barray.length; i++) {
			output[i] = barray[barray.length-i-1];
		}
		return output;
	}
}
