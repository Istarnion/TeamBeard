import lejos.nxt.*;

/**
*	Menu.java
*
*	This is the generalized class for the menues we run on the NXT.
*	It makes use of the MenuListener interface to report user-events to the classes in charge of the menu,
*	inspired by Java's ActionListener.
*	@see java.awt.event.ActionListener
*	@author TeamBeard
*/
class Menu {

	private int currOption = 0;
	private MenuListener menuListener;
	private String[] menuItems;
	private boolean exit;
	
	public Menu(String[] mi, MenuListener ml) {
		menuItems = mi;
		menuListener = ml;
	}

	/**
	*	Draws (refreshes) the menu to the LCD display.
	*	Calling this method from outside this class is in most cases useless,
	*	as we are running everything in a single thread, and the menu refreshes itself as it
	*	regains control after a method call has been completed.
	*/
	public void drawMenu() {
		LCD.clearDisplay();
		for(int i = 0; i < menuItems.length; i++) {
			if(i == currOption) {
				LCD.drawString(">" + menuItems[i], 2, i, true);
			}
			else {
				LCD.drawString(" " + menuItems[i], 2, i, false);
			}
		}
	}

	/**
	*	This initializes the menu-loop, that will wait for user input, and then warn the MenuListener.
	*/
	public void init() {
		drawMenu();
		while(Button.ESCAPE.isUp() && !exit) {
			Thread.yield();
			if(Button.ENTER.isDown()) {
				Button.ENTER.waitForPressAndRelease();
				menuListener.onMenuEvent(menuItems[currOption]);
			}

			else if(Button.LEFT.isDown()) {
				Button.LEFT.waitForPressAndRelease();
				currOption--;
				if(currOption<0) currOption=(menuItems.length - 1);
				drawMenu();
			}
			else if(Button.RIGHT.isDown()) {
				Button.RIGHT.waitForPressAndRelease();
				currOption++;
				if(currOption>=menuItems.length) currOption=0;
				drawMenu();
			}
		}
	}

	/**
	*	Sets the exit-flag to true, so the menu will exit out of itself,
	*	returning the previous menu, or if we are in the main menu, exiting the program.
	*/
	public void exit() {
		exit = true;
	}
}