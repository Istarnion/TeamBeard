import lejos.nxt.*;

/**DrawingRobot.java
 * 
 * <p>
 * The main class.
 * We just initializes the stateMachine, and 
 */
public class DrawingRobot
{

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private StateMachine stateMachine;
	
	/**
	 * 
	 * 
	 * 
	 */
	public DrawingRobot(){
		stateMachine = new StateMachine();
		stateMachine.push(new MenuState(stateMachine));
	}

	public static void main(String[] args) {
		new DrawingRobot();	
	}
}
