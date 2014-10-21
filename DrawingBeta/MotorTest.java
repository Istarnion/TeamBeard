import lejos.nxt.*;

class MotorTest {
	public static void main(String[] args) throws Exception {
		
		while(Button.ESCAPE.isUp())
		{
				Motor.B.rotate(7);
				Thread.sleep(1000);	
		}
		
	}
}