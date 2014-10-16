import lejos.nxt.*;

class AllMotorTest {
	// Motor A: y-axis
	// Motor B: x-axis
	// Motor C: tusj

	private static final int FULL_SPEED = 100;

	public static void main(String[] args) throws Exception {
		Motor.A.setSpeed(FULL_SPEED);
		Motor.B.setSpeed(FULL_SPEED);
		Motor.C.setSpeed(FULL_SPEED);

		Motor.C.forward();

		run();
		while(Button.ESCAPE.isUp()) {
			Thread.yield();
		}
	}

	private static void run() {
		Thread t = new Thread() {
			@Override
			public void run() {
				while(true) {
					Motor.A.forward();
					Motor.B.forward();
					AllMotorTest.sleep(2000);

					Motor.A.flt();
					Motor.B.flt();
					//Motor.C.rotate(30);
					AllMotorTest.sleep(1000);

					Motor.A.backward();
					Motor.B.backward();
					AllMotorTest.sleep(2000);

					Motor.A.flt();
					Motor.B.flt();
					//Motor.C.rotate(30);
					AllMotorTest.sleep(1000);
				}
			}
		};
		t.setDaemon(true);
		t.start();
	}

	private static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}