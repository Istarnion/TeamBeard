import lejos.nxt.*;



/** This is the true menu, the one true menu, and the only true menu.
 *  
 *  One menu to rule them all, one menu to find them, 
 *  one menu to bring them all, and in darkness bind them.
 */

public class MenuState implements State {
	//options
	private static int COPY=0, MUSIC=1, DRAW=2;
	private int currOption=0;

	private StateMachine sam;
	/**
	 * 
	 * 
	 * 
	 */
	public MenuState(StateMachine sam){
		this.sam=sam;
		
		
		
	}
	private void waitForButtonPress(){
		while(Button.ENTER.isUp() && Button.ESCAPE.isUp() && Button.LEFT.isUp() && Button.RIGHT.isUp()){
			
		}
	}
	
	@Override
	public void init() {
		while(Button.ESCAPE.isUp()){
			Thread.yield();
			if(Button.ENTER.isDown()){

			}
			else if(Button.LEFT.isDown()){
				currOption--;
				if(currOption<0) currOption=2;
				drawMenu();
			}
			else if(Button.RIGHT.isDown()){
				currOption++;
				if(currOption>2) currOption=0;
				drawMenu();
			}
		}
	}
	private void drawMenu(){
		LCD.clearDisplay();
		if(currOption==COPY){
			System.out.println(">Copy");
		}
		else{
			System.out.println(" Copy");
		}
		if(currOption==MUSIC){
			System.out.println(">Play Music");
		}
		else{
			System.out.println(" Play Music");
		}
		if(currOption==DRAW){
			System.out.println(">Draw");
		}
		else{
			System.out.println(" Draw");
		}
	}
	public void resume() {
		
	}
	
}

