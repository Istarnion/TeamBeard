import lejos.nxt.*;
import javax.microedition.lcdui.*;
import lejos.nxt.addon.ColorHTSensor;

class Rally {

	MusicPlayer music;

	ColorHTSensor rightSensor;
	LightSensor leftSensor;

	// Flags to denote wether the sensors is on the liine or not.
	boolean left = false;
	boolean right = false;

	int motorSpeed = 250;

	float rightMotorTargetSpeed = motorSpeed;
	float leftMotorTargetSpeed = motorSpeed;
	float acceleration = 0.1f;

	boolean shouldTurnRight = true;

	public Rally() {
		rightSensor = new ColorHTSensor(SensorPort.S1);
		leftSensor = new LightSensor(SensorPort.S2);
		calibrateSensors();

		resetMotorSpeed();

		setImageToDisplay(Images.PIRATE_FLAG);
		startMusic();

		drive();
	}

	private void drive() {
		checkLine();
		sleep(100);	// Gives the sensors time to do three readings
		driveForward();
		while(!Button.ESCAPE.isDown()) {
			/* DRVING LOGIC HERE */
			if(shouldTurnRight) {
				if(left) shouldTurnRight = false;
				accelRightTurn();
			}
			else {
				if(right) shouldTurnRight = true;
				accelLeftTurn();
			}
			/* END OF DRIVING LOGIC */
			Thread.yield();
		}
	}

	private void checkLine() {
		Thread t = new Thread() {
			@Override
			public void run() {
				while(true) {
					left = isLeftBlack();
					right = isRightBlack();
					Rally.sleep(30); // Gives the sensors enough time to make good readings.
				}
			}
		};

		t.setDaemon(true);
		t.start();
	}

	private void calibrateSensors() {
		leftSensor.setFloodlight(true);

		System.out.println("Hold rightSensor about 15mm from a diffuse white surface, press Enter..");
		waitForButtonPress(Button.ENTER);
		rightSensor.initWhiteBalance();
		Sound.beep();

		LCD.clear();
		System.out.println("Point rightSensor into the room,  press  Enter..");
		waitForButtonPress(Button.ENTER);
		rightSensor.initBlackLevel();
		Sound.beep();

		LCD.clear();
		System.out.println("Show left sensor dark, and press Enter..");
		waitForButtonPress(Button.ENTER);
		leftSensor.calibrateLow();
		Sound.beep();

		LCD.clear();
		System.out.println("Show left sensorlight, and press Enter..");
		waitForButtonPress(Button.ENTER);
		leftSensor.calibrateHigh();
		Sound.beep();
		sleep(1000);

		LCD.clear();
		System.out.println("Thank you!");
		System.out.println("Press enter to  run");
		while(!Button.ENTER.isDown()) {
			Thread.yield();
		}
	}

	private boolean isRightBlack() {
		// 7 == BLACK
		if(rightSensor.getColorID() == 7) return true;
		return false;
	}

	private boolean isLeftBlack() {
		if(leftSensor.readValue() <= 50) return true;
		return false;
	}

	private void accelRightTurn() {
		if(rightMotorTargetSpeed < motorSpeed) {
			rightMotorTargetSpeed += acceleration;
			Motor.A.setSpeed((int)rightMotorTargetSpeed);
		}
		if(leftMotorTargetSpeed > 0) {
			leftMotorTargetSpeed -= acceleration;
			if(leftMotorTargetSpeed < 0) leftMotorTargetSpeed = 0;
			Motor.C.setSpeed((int)leftMotorTargetSpeed);
		}
	}

	private void accelLeftTurn() {
		if(leftMotorTargetSpeed < motorSpeed) {
			leftMotorTargetSpeed += acceleration;
			Motor.C.setSpeed(leftMotorTargetSpeed);
		}
		if(rightMotorTargetSpeed > 0) {
			rightMotorTargetSpeed -= acceleration;
			if(rightMotorTargetSpeed < 0) rightMotorTargetSpeed = 0;
			Motor.A.setSpeed(rightMotorTargetSpeed);
		}
	}

	private void driveForward() {
		resetMotorSpeed();
		Motor.A.backward();
		Motor.C.backward();
	}

	private void driveBackward() {
		resetMotorSpeed();
		Motor.A.forward();
		Motor.C.forward();
	}

	private void turnRight() {
		Motor.C.setSpeed(350);
		Motor.A.setSpeed(10);
	}

	private void turnLeft() {
		Motor.A.setSpeed(350);
		Motor.C.setSpeed(10);
	}

	private void resetMotorSpeed() {
		Motor.A.setSpeed(motorSpeed);
		Motor.C.setSpeed(motorSpeed);
	}

	private void startMusic() {
		music = new MusicPlayer("track.txt");
		music.start();
	}

	private void stopMusic() {
		music.interrupt();
	}

	private void setImageToDisplay(byte[] image) {
		Image img = new Image(100, 64, image);
		new Graphics().drawImage(img, 0, 0, 0);
	}

	private void waitForButtonPress(Button b) {
		while(!b.isDown()) {
			Thread.yield();
		}
		while(b.isDown()) {
			Thread.yield();
		}
	}

	// Handled sleep for convenience
	private static void sleep(int i) {
		try {
			Thread.sleep(i);
		}
		catch(Exception e) {
			System.exit(0);
		}
	}

	public static void debugAndQuit(String msg) {
		LCD.clear();
		System.out.println(msg);
		sleep(2000);
		Runtime.getRuntime().halt(0);
	}

	/*Main just calls constructor of this*/
	public static void main(String[] args) throws Exception {
		new Rally();
	}
}
