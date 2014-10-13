import lejos.nxt.*;

/**
* Test.java
* This class is used for badly written use-and-throw
* test code. Never copy copy paste from this.
*/

class Test {

	public static void main(String[] args) throws Exception {
		Sound.playTone(440, 4000);
		Thread.sleep(1000);
		Sound.playTone(494, 3000);
		Thread.sleep(1000);
		Sound.playTone(587, 2000);
		Thread.sleep(2000);
	}
}
