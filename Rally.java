import lejos.nxt.*;
import javax.microedition.lcdui.*;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.Color;

class Rally {

	MusicPlayer music;

	ColorHTSensor rightSensor;
	LightSensor middleSensor;
	ColorHTSensor leftSensor;

	// Flags to denote wether the sensors is on the liine or not.
	boolean left = false;
	boolean middle = false;
	boolean right = false;

	int motorSpeed = 250;

	float rightMotorTargetSpeed = motorSpeed;
	float leftMotorTargetSpeed = motorSpeed;
	float acceleration = 0.5f;
	float minMotorSpeed = 100.0f;

	int triggerLevel = 5;

	boolean search = true;
	int shouldTurn = 0;

	public Rally() {
		rightSensor = new ColorHTSensor(SensorPort.S1);
		middleSensor = new LightSensor(SensorPort.S3);
		leftSensor = new ColorHTSensor(SensorPort.S2);
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
			if(middle) {
				// GO!
				driveForward();
				search = false;
			}
			else {
				// Stop, determine wich side to turn, and turn.
				if(!search) {
					Motor.A.setSpeed(50);
					Motor.C.setSpeed(50);
					search = true;
					rightMotorTargetSpeed = Motor.A.getSpeed();
					leftMotorTargetSpeed = Motor.C.getSpeed();
					shouldTurn = 0;
				}
				if(left) {
					accelLeftTurn();
					shouldTurn = 2;
				}
				else if(right) {
					accelRightTurn();
					shouldTurn = 1;
				}
				else {
					if(shouldTurn == 1) {
						accelRightTurn();
					}
					else if(shouldTurn == 2) {
						accelLeftTurn();
					}
				}
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
					middle = isMiddleBlack();
					right = isRightBlack();
					Rally.sleep(10); // Gives the sensors enough time to make good readings.
				}
			}
		};

		t.setDaemon(true);
		t.start();
	}

	private void calibrateSensors() {
		//leftSensor.setFloodlight(true);

		// RIGHT SENSOR
		System.out.println("Hold rightSensor about 15mm from a diffuse white surface, press Enter..");
		waitForButtonPress(Button.ENTER);
		rightSensor.initWhiteBalance();
		Sound.beep();

		LCD.clear();
		System.out.println("Point rightSensor into the room,  press  Enter..");
		waitForButtonPress(Button.ENTER);
		rightSensor.initBlackLevel();
		Sound.beep();

		// LEFT SENSOR
		System.out.println("Hold leftSensor about 15mm from a diffuse white surface, press Enter..");
		waitForButtonPress(Button.ENTER);
		leftSensor.initWhiteBalance();
		Sound.beep();

		LCD.clear();
		System.out.println("Point leftSensor into the room,  press  Enter..");
		waitForButtonPress(Button.ENTER);
		leftSensor.initBlackLevel();
		Sound.beep();

		LCD.clear();
		System.out.println("Show middle sensor dark, and press Enter..");
		waitForButtonPress(Button.ENTER);
		middleSensor.calibrateLow();
		Sound.beep();

		LCD.clear();
		System.out.println("Show middle sensorlight, and press Enter..");
		waitForButtonPress(Button.ENTER);
		middleSensor.calibrateHigh();
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
		Color c = rightSensor.getColor();
		if(c.getRed() < triggerLevel && c.getGreen() < triggerLevel && c.getBlue() < triggerLevel) return true;
		return false;
	}

	private boolean isLeftBlack() {
		// 7 == BLACK
		Color c = leftSensor.getColor();
		if(c.getRed() < triggerLevel && c.getGreen() < triggerLevel && c.getBlue() < triggerLevel) return true;
		return false;
	}

	private boolean isMiddleBlack() {
		if(middleSensor.readValue() <= 70) return true;
		return false;
	}

	private void accelRightTurn() {
		if(rightMotorTargetSpeed < motorSpeed) {
			rightMotorTargetSpeed += acceleration;
			Motor.C.setSpeed((int)rightMotorTargetSpeed);
		}
		if(leftMotorTargetSpeed > minMotorSpeed) {
			leftMotorTargetSpeed -= acceleration;
			if(leftMotorTargetSpeed < minMotorSpeed) leftMotorTargetSpeed = minMotorSpeed;
			Motor.A.setSpeed((int)leftMotorTargetSpeed);
		}
	}

	private void accelLeftTurn() {
		if(leftMotorTargetSpeed < motorSpeed) {
			leftMotorTargetSpeed += acceleration;
			Motor.A.setSpeed(leftMotorTargetSpeed);
		}
		if(rightMotorTargetSpeed > minMotorSpeed) {
			rightMotorTargetSpeed -= acceleration;
			if(rightMotorTargetSpeed < minMotorSpeed) rightMotorTargetSpeed = minMotorSpeed;
			Motor.C.setSpeed(rightMotorTargetSpeed);
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
