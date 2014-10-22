import lejos.nxt.*;

class Menu {

	private int currOption = 0;
	private MenuListener menuListener;
	private String[] menuItems;
	private boolean exit;
	
	public Menu(String[] mi, MenuListener ml) {
		menuItems = mi;
		menuListener = ml;
	}

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

	public void exit() {
		exit = true;
	}
}