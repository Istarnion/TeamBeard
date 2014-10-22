import lejos.nxt.*;
import java.util.Random;
/**
 * Music State!
 * This class contains the magic that makes the music!
 * 
 * @author Ole Jørgen Skogstad
 */

/**
*
*
*
*/
public class MusicState implements State, MenuListener {
	//
	StateMachine sam;
	//
	private Menu menu;
	//
	private static final String CHROMATIC="Chromatic", MAJOR="Major lydian", MINOR="Minor harmonic", RESCAN="Rescan", CANCEL="Cancel";
	//
	private int[] noteArray = new int[25];
	//
	private int[] scaleArray;
	//
	private int[][] composition = new int[2][128];
	//
	//private boolean[][] scanArray = new ;
	//
	private short[][] scanArrayConverted = new short[8][8];
	//
	private Robot robot;
	//
	private static final double MUSIC_CONSTANT = 1.05946309436;
	
	
// UGLY TEMP ARRAJ, SHALL BE PURGD IN HELFIREZ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private boolean[][] scanArray = { 
		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, true, false, true, true, true, false, false, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, true, true, false, },
		{false, false, false, false, false, false, true, true, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, true, true, false, false, true, true, false, },
		{false, false, false, false, false, false, true, false, false, false, false, false, false, true, false, false, false, false, false, true, true, true, true, true, false, false, false, false, true, true, false, false, false, false, false, true, true, false, true, true, true, false, },
		{false, false, false, false, false, false, true, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, true, true, false, false, false, true, true, false, false, false, false, false, true, true, true, true, false, true, false, },
		{false, false, false, false, false, false, true, true, false, false, false, false, true, false, false, false, false, false, true, true, true, true, true, false, false, false, false, true, false, true, false, false, false, false, false, true, false, true, true, false, false, true, },
		{false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true, false, true, true, false, false, false, false, true, false, false, true, false, false, true, },
		{false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true, true, true, true, true, false, false, false, true, false, false, false, false, false, true, },
		{false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, true, true, true, false, false, false, false, false, false, false, true, false, false, true, false, false, false, false, true, false, false, false, false, false, true, },
		{false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false, true, true, true, true, false, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false, false, true, },
		{false, false, false, false, false, false, false, false, true, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, true, false, false, false, false, false, false, false, false, true, true, },
		{false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, true, true, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, true, true, false, true, true, true, false, true, false, true, true, true, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true, false, true, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, true, false, true, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true, false, true, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, true, false, true, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true, false, false, true, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, true, false, true, false, false, false, true, false, false, false, false, false, true, false, true, true, true, true, true, false, true, true, false, false, true, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, true, true, false, true, false, true, true, false, false, false, false, false, false, true, false, false, false, false, false, false, false, true, false, true, true, true, true, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, true, false, true, true, true, true, true, false, false, false, false, false, false, true, false, false, false, false, false, false, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, true, false, false, false, true, false, false, true, true, false, false, false, false, true, false, false, false, false, false, false, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, true, true, false, false, true, false, false, true, false, false, false, false, true, true, true, true, true, true, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, true, true, true, false, false, true, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, true, false, true, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, true, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, true, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, true, false, false, false, true, true, true, false, false, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, true, true, false, false, false, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, true, false, false, true, false, true, false, false, false, false, true, false, false, false, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, true, true, true, false, false, false, false, false, false, false, true, false, false, false, false, true, true, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, true, true, true, false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, true, false, false, false, true, true, false, false, false, false, true, false, false, false, false, false, false, false, true, true, true, true, true, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, true, false, false, false, true, false, false, false, false, false, false, true, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, },
		{false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, },
		{false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, true, false, false, },
		{false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, true, false, false, },
		{false, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, true, false, false, },
		{false, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, false, false, true, false, false, false, false, false, false, false, true, false, false, },
		{false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, true, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, true, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, false, false, false, false, true, true, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, true, true, true, true, true, true, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },
		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },};




	/**
	 * Constructor for the music state. Retrieves a robot object!
	 */
	public MusicState(StateMachine sam) {
		robot = Robot.getInstance();
		this.sam = sam;
	}

	/**
	*
	*
	*/
	@Override
	public void onMenuEvent(String menuItem) {
		switch(menuItem) {
			case CHROMATIC:
			generateChromatic();
			break;

			case MAJOR:
			generateMajorLydian();
			break;

			case MINOR:
			generateMinorHarmonic();

			case RESCAN:
			LCD.clearDisplay();
			System.out.println("Scanning!");
			scanArray = robot.scan();
			LCD.clearDisplay();
			menu.drawMenu();
			break;

			case CANCEL:
			menu.exit();
		}
	}

	/**
	*
	*
	*
	*/
	// ----------------------------------------------------------------------------------REMEMBARR REMOV COMENT
	@Override
	public void init() {
		LCD.clearDisplay();
		menu = new Menu(new String[] {CHROMATIC, MAJOR, MINOR, RESCAN, CANCEL}, this);
		LCD.drawString("Scanning! Please wait ...", 2, 2, false);
		//scanArray = robot.scan();
		generateTones();
		menu.init();
	}
	/**
	*
	*
	*/
	
	@Override
	public void resume() {
	}

	/** Method for creating note frequencies for noteArray, by the function 
	*	f(n) = 12th-rt(2)^(n-49) * 440
	*	where 440 is the note A4, and n is the n-th key on a 88-key piano.
	*	Read more about this on <a href="http://en.wikipedia.org/wiki/Piano_key_frequencies">Wikipedia (Piano key frequencies</a>
	*
	*/
	private void generateTones() {
		for(int i = 0; i < noteArray.length; i++) {
			noteArray[i] = (int)(Math.round((Math.pow(MUSIC_CONSTANT, ((28 + i) - 49)) * 440)));
		}
	}

	// Generate music!
	private void generateMusic() {
			
	}

	// Creates a scaleArray equal to the original noteArray
	private void generateChromatic() {
		scaleArray = noteArray;
		generateMusic();
		MusicPlayer.play(composition);
		menu.drawMenu();
	}
	
	// Creates a scaleArray with notes from the C major lydian scale
	private void generateMajorLydian() {
		scaleArray = new int[] 
		{noteArray[0], noteArray[2], noteArray[4], noteArray[5], noteArray[7], 
		noteArray[9], noteArray[11], noteArray[12], noteArray[14], noteArray[16], 
		noteArray[17], noteArray[19], noteArray[21], noteArray[23], noteArray[24], 
		noteArray[2], noteArray[23], noteArray[4], noteArray[21], noteArray[5], 
		noteArray[19], noteArray[7], noteArray[17], noteArray[9], noteArray[16]};
		generateMusic();
		MusicPlayer.play(composition);
		menu.drawMenu();
	}

	// Creates a scaleArray with notes from the C Minor harmonic scale
	private void generateMinorHarmonic() {
		scaleArray = new int[]
		{noteArray[0], noteArray[2], noteArray[3], noteArray[5], noteArray[7], 
		noteArray[8], noteArray[11], noteArray[12], noteArray[14], noteArray[15], 
		noteArray[17], noteArray[19], noteArray[20], noteArray[23], noteArray[24], 
		noteArray[2], noteArray[23], noteArray[3], noteArray[20], noteArray[5], 
		noteArray[19], noteArray[7], noteArray[17], noteArray[8], noteArray[15]};
		generateMusic();
		MusicPlayer.play(composition);
		menu.drawMenu();
	}		
}

