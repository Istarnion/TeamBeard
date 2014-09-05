import lejos.nxt.*;
import java.util.Random;

class Dodge {

	private final static int
		HIT_LEFT = 0,
		HIT_RIGHT = 1,
		HIT_BOTH = 2;

	private int motorSpeed = 250;

	private Random rnd;

	TouchSensor touch;
	TouchSensor touch2;

	public Dodge() throws Exception {

		touch = new TouchSensor(SensorPort.S1);
		touch2 = new TouchSensor(SensorPort.S2);

		rnd = new Random();

		Motor.A.setSpeed(motorSpeed);
		Motor.C.setSpeed(motorSpeed);

		drive();
	}

	private void drive() {
		try {
			LCD.clear();
			System.out.println("Driving.");

			rightForward();
			leftForward();

			try {
				while(!crashRight() && !crashLeft()) {
					if(Button.ESCAPE.isDown()) {
						return;
					}
					Thread.yield();
				}
			}
			catch(Exception e) {
				System.out.println("sensors failed!");
				sleep(1000);
				System.exit(0);	
			}	

			if(crashRight() && crashLeft()) {
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
			LCD.clear();
			System.out.println("ESCAPE!");

			rightBackward();
			leftBackward();

			Thread.sleep(1000);

			switch(hit) {
				case HIT_BOTH:

					if(rnd.nextBoolean()) {
						rightForward();
					}
					else {
						leftForward();
					}

					Thread.sleep(750 + rnd.nextInt(1000));
					break;
				case HIT_RIGHT:
					rightFloat();
					sleep(1000 + rnd.nextInt(1000));
					break;
				case HIT_LEFT:
					leftFloat();
					sleep(1000 + rnd.nextInt(1000));
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

	// Motor stop/float
	private void rightFloat() {
		Motor.A.flt();
	}

	private void rightStop() {
		Motor.A.stop();
	}

	private void leftFloat() {
		Motor.C.flt();
	}

	private void leftStop() {
		Motor.C.stop();
	}

	private boolean crashRight() throws Exception {
		return touch.isPressed();
	}

	private boolean crashLeft() throws Exception {
		return touch2.isPressed();
	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		}
		catch(Exception e) {
			System.exit(0);
		}
	}

	public static void main(String[] args) throws Exception {
		new Dodge();
	}

}
