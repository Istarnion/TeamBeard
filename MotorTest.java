import lejos.nxt.*;

class MotorTest {

	public static void main(String[] args) throws Exception {
		Motor.A.setSpeed(600);
		Motor.B.setSpeed(900);
		Motor.C.setSpeed(600);

		Motor.A.forward();
		Motor.B.forward();
		Motor.C.forward();

		Thread.sleep(5000);
	}

}
