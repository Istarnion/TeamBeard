import lejos.nxt.*;

class Tester {

	public static void main(String[] main) throws Exception {
		for(int i=0; i<64; i++) {
			Motor.B.rotate(7);
		}

		sleep(3500);
	}

	private static void sleep(int i) {
		try {
			Thread.sleep(i);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}