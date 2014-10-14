import lejos.nxt.*;

class DrawTest {

	public static void main(String[] args) throws Exception {
		Motor.A.setSpeed(100);
		Motor.B.setSpeed(100);
		Motor.B.flt();
		Motor.A.forward();
		int forward = 0;	// Forward - Pause - Backward - Pause
		int right = 0;
		boolean setting = false;
		boolean reset = false;
		System.out.println("FORWARD!!!!");
		while(Button.ESCAPE.isUp()) {
			if(Button.ENTER.isDown() && !reset) {
				reset = true;
				switch(forward) {
					case 0:	// Is driving forward
						forward = 1;
						Motor.A.flt();
						System.out.println("Taking a break..");
						break;
					case 1:	// Is paused
						forward = 2;
						Motor.A.backward();
						System.out.println("Backing up!");
						break;
					case 2:	// Is driving backwards
						forward = 3;
						Motor.A.flt();
						System.out.println("Taking a break..");
						break;
					case 3:
						forward = 0;
						Motor.A.forward();
						System.out.println("FORWARD!!!!");
						break;
					default:
					break;
				}
			}
			else if(Button.ENTER.isUp()){
				reset = false;
			}

			if(Button.LEFT.isDown() && !setting) {
				setting = true;
				if(right == 0) {
					right = -1;
					Motor.B.forward();
				}
				else if(right == 1) {
					right = 0;
					Motor.B.flt();
				}
			}
			if(Button.RIGHT.isDown() && !setting) {
				setting = true;
				if(right == 0) {
					right = 1;
					Motor.B.backward();
				}
				else if(right == -1) {
					right = 0;
					Motor.B.flt();
				}
			}
			if(Button.LEFT.isUp() && Button.RIGHT.isUp()) {
				setting = false;
			}
		}
	}
}
