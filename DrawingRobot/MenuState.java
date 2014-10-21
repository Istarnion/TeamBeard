import lejos.nxt.*;



/** This is the true menu, the one true menu, and the only true menu.
 *  
 *  One menu to rule them all, one menu to find them, 
 *  one menu to bring them all, and in darkness bind them.
 */

public class MenuState implements State {
	//options
	private static final int COPY=0, MUSIC=1, DRAW=2, CANCEL=3;
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
		drawMenu();
		while(Button.ESCAPE.isUp()){
			Thread.yield();
			if(Button.ENTER.isDown()){
				Button.ENTER.waitForPressAndRelease();
				switch(currOption){
					
					case COPY:
					//sam.push(new CopyState());
					LCD.clearDisplay();
					drawMenu();
					break;
					
					case MUSIC:
					sam.push(new MusicState(sam));
					LCD.clearDisplay();
					drawMenu();
					break;
					
					case DRAW:
					//sam.push(new DrawState());
					LCD.clearDisplay();
					drawMenu();
					break;
					
					case CANCEL:
					System.exit(0);
					break;
				}

			}
			else if(Button.LEFT.isDown()){
				Button.LEFT.waitForPressAndRelease();
				currOption--;
				if(currOption<0) currOption=3;
				drawMenu();
			}
			else if(Button.RIGHT.isDown()){
				Button.RIGHT.waitForPressAndRelease();
				currOption++;
				if(currOption>3) currOption=0;
				drawMenu();
			}
		}
	}
	private void drawMenu(){
		LCD.clearDisplay();
		if(currOption==COPY){
			LCD.drawString(">Copy",2,0,true);
		}
		else{
			LCD.drawString(" Copy",2,0,false);
		}
		if(currOption==MUSIC){
			LCD.drawString(">Play Music",2,2,true);
		}
		else{
			LCD.drawString(" Play Music",2,2,false);
		}
		if(currOption==DRAW){
			LCD.drawString(">Draw",2,4,true);
		}
		else{
			LCD.drawString(" Draw",2,4,false);
		}
		if(currOption==CANCEL){
			LCD.drawString(">Cancel",2,6,true);
		}
		else{
			LCD.drawString(" Cancel",2,6,false);
		}
	}
	public void resume() {
	}
	
}

