import lejos.nxt.*;

class Test {
	
	public Test() throws Exception {
		while(!Button.ESCAPE.isPressed()) {
			TouchSensor touch = new TouchSensor(SensorPort.S1);
			TouchSensor touch2 = new TouchSensor(SensorPort.S2);
			if(touch.isPressed()) {
				System.out.println("Right sensor pushed!");
			}
			else if(touch2.isPressed()) {
				System.out.println("Left sensor pressed!");
			}
		}	
	}

	public static void main(String[] args) throws Exception {

		new Test();

	}

}