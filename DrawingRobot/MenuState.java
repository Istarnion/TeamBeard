import lejos.nxt.*;

/** 
 *  Menu.java
 *  This class handles the main menu.
 *	It provides the user options to choose between the different functionalities of the robot.
 *	@author TeamBeard
 */
public class MenuState implements MenuListener {

	//options for the menu
	private static final String 
		COPY="Copy",
		MUSIC="Music Player",
		SLAVE="PC slave",
		CANCEL="Cancel";

	private Menu menu;

	/**
	 * The constructor simply prepares the menu
	 */
	public MenuState(){
		menu = new Menu(new String[] {COPY, MUSIC, SLAVE, CANCEL}, this);
	}
	
	/**
	*	switches on the menuItem String, and initializes the appropriate state.
	*	Since we are running everything in a single thread, this menu does not regain control before the state
	*	that is activated finishes its run.
	*/
	@Override
	public void onMenuEvent(String menuItem){
		switch (menuItem){
			case COPY:
				new CopyState();
			break;

			case MUSIC:
				new MusicState().init();
			break;

			case SLAVE:
				new SlaveState();
			break;

			case CANCEL:
				System.exit(0);
		}
		menu.drawMenu();
	}

	/**
	*	Initializes the menu, pushing it to the LCD display.
	*/
	public void init() {
		menu.init();
	}
}

