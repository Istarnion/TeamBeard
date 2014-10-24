import lejos.nxt.*;

class Tester {

	public static void main(String[] main) throws Exception {
		Robot robot = Robot.getInstance();
		sleep(100);
		while(Button.ENTER.isUp()) {
			System.out.println(robot.readValue());
			sleep(750);
		}
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