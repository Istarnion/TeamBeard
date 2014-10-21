import java.util.Stack;

/** StateMachine.java
 * 
 * <p>
 * This is a simple implementation of a finite state machine.
 * It is stack-based.
 * Having a HashMap to store all states, was decided against, due to restrictions in memory.
 */
public class StateMachine
{
	
	private Stack<State> stateStack;

	/** Constructor
	 * 
	 * <p>
	 * Initializes the stack.
	 */
	public StateMachine() {
		stateStack = new Stack<State>();
	}

	public State pop() {
		State s = stateStack.pop();
		stateStack.peek().resume();
		return null;
	}
	
	public State peek() {
		return stateStack.peek();	
	}
	
	public void push(State s) {
		stateStack.push(s);
		s.init();	
	}
	
}

