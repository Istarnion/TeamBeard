import lejos.nxt.*;

class Test {

	int maxSpeed = 900;
	int loSpeed = 100;

	public Test() throws Exception {
		Motor.A.setSpeed(maxSpeed);
		Motor.C.setSpeed(maxSpeed);

		Motor.A.forward();
		Motor.C.forward();

		Thread.sleep(3000);
/*
*		Motor.A.setSpeed(loSpeed);
*
*		Thread.sleep(1000);
*
*		Motor.A.setSpeed(maxSpeed);
*
*		Thread.sleep(2000);
*
*		Motor.C.setSpeed(loSpeed);
*
*		Thread.sleep(1000);
*
*		Motor.C.setSpeed(maxSpeed);
*
*		Thread.sleep(2000);
*/
	}

	public static void main(String[] args) throws Exception {

		new Test();

	}

}