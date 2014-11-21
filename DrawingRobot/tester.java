import lejos.nxt.*;

class Tester {

	public static void main(String[] args) throws Exception {
		Motor.C.rotateTo(0);
		int down = -45;
		while(Button.ESCAPE.isUp()) {
			Motor.C.rotateTo(down);
			System.out.println(down);
			sleep();
			Motor.C.rotateTo(0);
			Button.waitForAnyPress();
			if(Button.ESCAPE.isDown()) {
				System.exit(0);
			}
			down -= 2;
		}
	}

	public static void sleep() {
		try {
			Thread.sleep(1000);
		}
		catch(Exception e) {

		}
	}
}