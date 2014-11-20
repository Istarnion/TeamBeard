import lejos.nxt.*;

/**
 * MusicState.java
 * 
 * 
 * @author TeamBeard
 */
public class MusicState implements MenuListener {
	//
	private Menu menu;
	// Menu items
	private static final String CHROMATIC="Chromatic", MAJOR="Major lydian", MINOR="Minor harmonic", RESCAN="Rescan", CANCEL="Cancel";
	// 
	private Robot robot;
	// 
	private boolean[][] scanArray;
	// 
	private MusicGenerator composer = new MusicGenerator();
	

	/**
	 * Constructor for the music state. Retrieves a robot object!
	 *
	 */
	public MusicState() {
		robot = Robot.getInstance();
	}


	/**
	* Switches on menuItem and composes music based on the chosen scale 
	* @param menuItem User selected menu item
	*/
	@Override
	public void onMenuEvent(String menuItem) {
		switch(menuItem) {
			case CHROMATIC:
			composer.generateChromatic(scanArray);
			menu.drawMenu();
			break;

			case MAJOR:
			composer.generateMajorLydian(scanArray);
			menu.drawMenu();
			break;

			case MINOR:
			composer.generateMinorHarmonic(scanArray);
			menu.drawMenu();
			break;

			case RESCAN:
			scanArray = robot.scan();
			menu.drawMenu();
			break;

			case CANCEL:
			menu.exit();
		}
	}

	/**
	* Initializes the music menu and starts a new scan.
	*
	*
	*/
	public void init() {
		LCD.clearDisplay();
		menu = new Menu(new String[] {CHROMATIC, MAJOR, MINOR, RESCAN, CANCEL}, this);
		scanArray = robot.scan();
		menu.init();
	}

}