import lejos.nxt.*;
import java.util.Random;
import java.io.*;

import javax.microedition.lcdui.*;

class Tunnel extends Thread {

	private byte[] image = {
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xf3, (byte) 0xc7, (byte) 0x07, (byte) 0x0f, (byte) 0x1f, (byte) 0x1f, (byte) 0x3f, (byte) 0x7f, (byte) 0x7f, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x3f, (byte) 0x3f, (byte) 0x8f, (byte) 0x07, (byte) 0x03,
					(byte) 0x03, (byte) 0x03, (byte) 0x03, (byte) 0x1b, (byte) 0x61, (byte) 0x01, (byte) 0x03, (byte) 0x03, (byte) 0x03, (byte) 0x07, (byte) 0x0b, (byte) 0x23,
					(byte) 0x03, (byte) 0x03, (byte) 0x07, (byte) 0x07, (byte) 0x1f, (byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x3f, (byte) 0x3f, (byte) 0x1f, (byte) 0x0f, (byte) 0x0f, (byte) 0x87, (byte) 0xe3,
					(byte) 0xfb, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfe, (byte) 0xfc, (byte) 0xfc, (byte) 0xf8,
					(byte) 0xf0, (byte) 0xe0, (byte) 0xe0, (byte) 0xc1, (byte) 0x81, (byte) 0x83, (byte) 0x03, (byte) 0x07, (byte) 0x0f, (byte) 0x0f, (byte) 0x1f, (byte) 0x1f,
					(byte) 0x3f, (byte) 0x3f, (byte) 0x7f, (byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xc0, (byte) 0x80,
					(byte) 0x80, (byte) 0x80, (byte) 0x91, (byte) 0x82, (byte) 0x20, (byte) 0x00, (byte) 0x48, (byte) 0x00, (byte) 0x00, (byte) 0x20, (byte) 0x20, (byte) 0x00,
					(byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x04, (byte) 0x08, (byte) 0x50, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x63, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x7f, (byte) 0x3f, (byte) 0x3f, (byte) 0x1f, (byte) 0x1f, (byte) 0x0f,
					(byte) 0x0f, (byte) 0x07, (byte) 0x07, (byte) 0x83, (byte) 0x83, (byte) 0xc1, (byte) 0xc0, (byte) 0xe0, (byte) 0xf0, (byte) 0xf0, (byte) 0xf8, (byte) 0xfc,
					(byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xfe, (byte) 0xfe, (byte) 0xfc, (byte) 0xfc, (byte) 0xf8, (byte) 0xf8, (byte) 0xf0, (byte) 0xf0, (byte) 0xe0, (byte) 0xe0, (byte) 0xc1, (byte) 0xc1,
					(byte) 0x83, (byte) 0x9f, (byte) 0x33, (byte) 0xc0, (byte) 0x80, (byte) 0x00, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0x07,
					(byte) 0x82, (byte) 0xe0, (byte) 0xf8, (byte) 0xf8, (byte) 0xc2, (byte) 0x07, (byte) 0x0f, (byte) 0x0f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x06,
					(byte) 0x82, (byte) 0x0c, (byte) 0x08, (byte) 0xc7, (byte) 0xfb, (byte) 0xc3, (byte) 0xc1, (byte) 0xe1, (byte) 0xe0, (byte) 0xe0, (byte) 0xf0, (byte) 0xf0,
					(byte) 0xf8, (byte) 0xf8, (byte) 0xfc, (byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f,
					(byte) 0x3f, (byte) 0x9f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x7f, (byte) 0x7f, (byte) 0x3f,
					(byte) 0x3f, (byte) 0xbf, (byte) 0x1f, (byte) 0x5f, (byte) 0x5f, (byte) 0x0f, (byte) 0x2f, (byte) 0x2f, (byte) 0x07, (byte) 0xff, (byte) 0x03, (byte) 0xfe,
					(byte) 0xf0, (byte) 0xf0, (byte) 0xe0, (byte) 0xf0, (byte) 0xd1, (byte) 0xc1, (byte) 0xf1, (byte) 0xc1, (byte) 0xc1, (byte) 0xf0, (byte) 0xc0, (byte) 0xf0,
					(byte) 0xe0, (byte) 0xf8, (byte) 0x1e, (byte) 0xff, (byte) 0xf1, (byte) 0x80, (byte) 0x00, (byte) 0x3f, (byte) 0x83, (byte) 0x1f, (byte) 0x7f, (byte) 0xbf,
					(byte) 0x3f, (byte) 0x3f, (byte) 0x7f, (byte) 0x7f, (byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0x7f, (byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x7f, (byte) 0x7f, (byte) 0x23, (byte) 0x3d, (byte) 0x15, (byte) 0x1f,
					(byte) 0x1f, (byte) 0x0d, (byte) 0x8d, (byte) 0x84, (byte) 0xc0, (byte) 0x43, (byte) 0x01, (byte) 0xc1, (byte) 0xe1, (byte) 0xe0, (byte) 0xf0, (byte) 0xf0,
					(byte) 0xf2, (byte) 0xf8, (byte) 0xf9, (byte) 0xf8, (byte) 0xfc, (byte) 0xfc, (byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xf0, (byte) 0xe0, (byte) 0xcf, (byte) 0x0f, (byte) 0x3f, (byte) 0x3f, (byte) 0xff, (byte) 0x7f, (byte) 0x7f, (byte) 0xff,
					(byte) 0xff, (byte) 0x7f, (byte) 0x1f, (byte) 0x1f, (byte) 0x87, (byte) 0xe7, (byte) 0xf0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfc, (byte) 0xfe,
					(byte) 0xff, (byte) 0xfe, (byte) 0xfe, (byte) 0xff, (byte) 0xfc, (byte) 0xf8, (byte) 0xf8, (byte) 0xf8, (byte) 0xf1, (byte) 0xf0, (byte) 0xf0, (byte) 0xe0,
					(byte) 0xe1, (byte) 0xe1, (byte) 0x61, (byte) 0x03, (byte) 0x83, (byte) 0xc0, (byte) 0x94, (byte) 0x87, (byte) 0x07, (byte) 0x0f, (byte) 0x0f, (byte) 0x1f,
					(byte) 0x1f, (byte) 0x1f, (byte) 0x3f, (byte) 0x3f, (byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xf8, (byte) 0xf8, (byte) 0xf0, (byte) 0xf8, (byte) 0xf0,
					(byte) 0xf4, (byte) 0xe2, (byte) 0xea, (byte) 0xe3, (byte) 0xeb, (byte) 0xe1, (byte) 0xe9, (byte) 0xe0, (byte) 0xf6, (byte) 0xf8, (byte) 0xfc, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfe, (byte) 0xf8,
					(byte) 0xf0, (byte) 0xe0, (byte) 0xe0, (byte) 0xe1, (byte) 0xe0, (byte) 0xf0, (byte) 0xf8, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xf0, (byte) 0xe2, (byte) 0xc3, (byte) 0xc7, (byte) 0xc7, (byte) 0x87,
					(byte) 0x8f, (byte) 0x8f, (byte) 0x9f, (byte) 0xde, (byte) 0xde, (byte) 0xec, (byte) 0xec, (byte) 0xf1, (byte) 0xfc, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff
	};

	private final static int
		HIT_LEFT = 0,
		HIT_RIGHT = 1,
		HIT_BOTH = 2;

	private int motorSpeed = 250;

	private Random rnd;

	TouchSensor touch;
	TouchSensor touch2;

	SoundSensor sound;

	LightSensor light;

	File imperialMarch;

	public Tunnel() throws Exception {

		touch = new TouchSensor(SensorPort.S1);
		touch2 = new TouchSensor(SensorPort.S2);
		sound = new SoundSensor(SensorPort.S3);
		light = new LightSensor(SensorPort.S4);

		rnd = new Random();

		Motor.A.setSpeed(motorSpeed);
		Motor.C.setSpeed(motorSpeed);
		Motor.B.setSpeed(900);

		imperialMarch = new File("imperial.wav");

		calibrateLightSensor();

		Image img = new Image(100, 64, image);
		Graphics g = new Graphics();
		g.drawImage(img, 0, 0, 0);

		this.start();

		drive();

		rightFloat();
		leftFloat();
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

	// This is what happens in the separate thread.
	@Override
	public void run() {
		while(!Button.ESCAPE.isDown()) {
			// The sound is playing in it's own thread, so we're actually running three processor threads. Still, having the 'driving curcuit' on its own make it easier to manage.
			Sound.playSample(imperialMarch, Sound.VOL_MAX);
			try {
				Thread.sleep(9033);	// The sleep value here is exactly the length of the song.
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	* This is our main driving logic.
	* It's responsibility is to keep driving and check for events.
	* When an event happens, it breaks the loop, and call the apropriate function.
	*/
	private void drive() {
		try {
			rightForward();			// Rigt motor forward
			leftForward();			// Left motor forward
			int soundVal = 0;		// Initialize soundVal to zero, so it is ready to store the actual values.
			int lightVal = 0;		// same with light

			boolean forward = true;

			long lastTimeISawBlack = 0;

			Motor.B.backward();

			try {
				// While everything is normal, we just drive on.
				while(!crashRight() && !crashLeft() && soundVal < 85) {
					// If the escape button is pressed, we interrupt the music, and terminate the program.
					if(Button.ESCAPE.isDown()) {
						System.exit(0);
						this.interrupt();
					}

					soundVal = sound.readValue();	// Update the soundVal. This way we won't need to get the value again further down.
					lightVal = light.readValue();	// Same with the light sensor.

					// change direction if lightsensor tells us to
					if(lightVal <= 50 && (System.currentTimeMillis()-lastTimeISawBlack > 2000)) {
						lastTimeISawBlack = System.currentTimeMillis();
						if(forward) {
							forward = false;
							rightBackward();
							leftBackward();
							Motor.B.forward(); // reverse the brush too.
						}
						else {
							forward = true;
							rightForward();
							leftForward();
							Motor.B.backward();
						}
					}

					Thread.yield();		// Yield the processor to the music-thread. Even without that, it's good practice to let the processor breathe if you have an infinite loop.
				}
			}
			catch(Exception e) {
				System.out.println("sensors failed!");
				sleep(1000);
				System.exit(0);
			}	

	
			// first, we check the sound.
			if(soundVal >= 80) {
				// stop
				rightFloat();
				leftFloat();
				// wait for 2s
				sleep(2000);
				// drive on. You crazy diamond.
				drive();
			}
			// If not sound, we have crashed and need to work out how we crashed.
			else if(crashRight() && crashLeft()) {
				escape(HIT_BOTH);
			}
			else if(crashRight()) {
				escape(HIT_RIGHT);
			}
			else if(crashLeft()) {
				escape(HIT_LEFT);
			}
		}
		catch(Exception e) {
			System.out.println("Failed in drive()");
			sleep(1000);
		}
	}

	private void escape(int hit) {
		if(Button.ESCAPE.isDown()) {
			System.exit(0);
		}
		try {
			rightBackward();
			leftBackward();

			Thread.sleep(500);

			switch(hit) {
				case HIT_BOTH:

					if(rnd.nextBoolean()) {
						rightForward();
					}
					else {
						leftForward();
					}

					sleep(500 + rnd.nextInt(1000));
					break;
				case HIT_RIGHT:
					rightFloat();
					sleep(500 + rnd.nextInt(1000));
					break;
				case HIT_LEFT:
					leftFloat();
					sleep(500 + rnd.nextInt(1000));
					break;
				default:
					Sound.beepSequence();		// Mark error by beeping.
					break;
			}

			drive();
		}
		catch(Exception e) {
			System.out.println("Failed in escape()");
			sleep(1000);
		}	
	}

	// Motors forward/backward:
	private void rightForward() {
		Motor.A.backward();
	}

	private void rightBackward() {
		Motor.A.forward();
	}

	private void leftForward() {
		Motor.C.backward();
	}

	private void leftBackward() {
		Motor.C.forward();
	}

	// Motor float
	private void rightFloat() {
		Motor.A.flt();
	}

	private void leftFloat() {
		Motor.C.flt();
	}

	private boolean crashRight() throws Exception {
		return touch.isPressed();
	}

	private boolean crashLeft() throws Exception {
		return touch2.isPressed();
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

	public static void main(String[] args) throws Exception {
		new Tunnel();
	}

}
