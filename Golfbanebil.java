import lejos.nxt.*;

class Golfbanebil {

	private static final float beltRatio = 0.666667f;

	private NXTRegulatedMotor a, b, c;

	private int millisPerDegree = 500;

	public Golfbanebil() throws Exception {
		a = Motor.A;
		b = Motor.B;
		c = Motor.C;

		setSpeedAll(900);
		Thread.sleep(5000);
		rotate(90);
		Thread.sleep(5000);
		rotate(90);
		Thread.sleep(5000);
		rotate(90);
		Thread.sleep(5000);
		rotate(90);
		Thread.sleep(5000);
	}

	private void setSpeedAll(int speed) throws Exception {
		a.setSpeed((int)(speed*beltRatio));
		b.setSpeed(speed);
		c.setSpeed((int)(speed*beltRatio));
	}

	private void stopAll() throws Exception {
		a.stop();
		b.stop();
		c.stop();
	}

	private void rotate(int degrees) throws Exception {
		if(degrees < 0) {	// Rotate left
			c.stop();
			Thread.sleep(Math.abs(degrees*millisPerDegree));
			c.forward();
		}
		else {		// Rotate right
			a.stop();
			Thread.sleep(Math.abs(degrees*millisPerDegree));
			a.forward();
		}
	}

	public static void main(String[] args) throws Exception {
		new Golfbanebil();
	}
}
