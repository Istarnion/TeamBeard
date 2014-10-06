import lejos.nxt.*;

class TunnelV2 {

	int motorSpeed = 300;
	int millisPrDegree = 60;

	LightSensor light;
	SoundSensor sound;
	TouchSensor touch, touch2;

	public TunnelV2() throws Exception {
		touch = new TouchSensor(SensorPort.S1);
		touch2 = new TouchSensor(SensorPort.S2);
		sound = new SoundSensor(SensorPort.S3);
		light = new LightSensor(SensorPort.S4);

		calibrateLightSensor();

		while(!Button.ESCAPE.isDown()) {
			driveForward(motorSpeed);

			if(crashLeft()) {
				driveBackward(motorSpeed/2);
				Motor.C.setSpeed(motorSpeed/4);
				sleep(750);
			}
			if(crashRight()) {
				driveBackward(motorSpeed/2);
				Motor.A.setSpeed(motorSpeed/4);
				sleep(750);
			}
			else if(light.readValue() < 50) {
				driveBackward(motorSpeed/2);
				sleep(500);
				driveForward(motorSpeed/2);
				rotate(180);
			}
			else if(sound.readValue() > 85) {
				stop();
				sleep(1500);
			}
		}
	}

	// Boring stuff below
	public static void main(String[] args) throws Exception {
		new TunnelV2();
	}

	private void calibrateLightSensor() {
		int lightVal = 0;
		light.setFloodlight(true);
		System.out.println("Press enter to calibrate high val:");
		while(!Button.ENTER.isDown()) {
			Thread.yield();
		}
		light.calibrateHigh();
		System.out.println("High: "+light.getHigh());
		Sound.beep();
		sleep(1000);
		LCD.clear();
		System.out.println("Press enter to calibrate high val:");
		while(!Button.ENTER.isDown()) {
			Thread.yield();
		}
		light.calibrateLow();
		System.out.println("Low: "+light.getLow());
		Sound.beep();
		sleep(2000);
	}

	private void driveForward(int speed) {
		Motor.A.setSpeed(speed);
		Motor.C.setSpeed(speed);
		Motor.A.backward();
		Motor.C.backward();
	}

	private void driveBackward(int speed) {
		Motor.A.setSpeed(speed);
		Motor.C.setSpeed(speed);
		Motor.A.forward();
		Motor.C.forward();
	}

	private void stop() {
		Motor.A.flt();
		Motor.C.flt();
	}

	private void rotate(int degrees) {
		if(degrees < 0) {
			Motor.C.forward();
			sleep(Math.abs(degrees)*millisPrDegree);
			Motor.C.backward();
		}
		else {
			Motor.A.forward();
			sleep(Math.abs(degrees)*millisPrDegree);
			Motor.A.backward();
		}
	}

	// Handled sleep for convenience
	private void sleep(int i) {
		try {
			Thread.sleep(i);
		}
		catch(Exception e) {
			System.exit(0);
		}
	}

	private boolean crashRight() throws Exception {
		return touch.isPressed();
	}

	private boolean crashLeft() throws Exception {
		return touch2.isPressed();
	}
}
