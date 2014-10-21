import lejos.nxt.*;
import java.util.Random;
/**
 * Music State!
 * This class contains methods for making music from a 2dimensional boolean array
 * 
 * @author Ole Jørgen Skogstad
 */

public class MusicState implements State {
	StateMachine sam;
	private static int[] noteArray = new int[25];
	private static int[] scaleArray;
	private static int[][] composition = new int[2][130];
	//private static boolean[][] scanArray;
	private static Robot robot;
	private static final double MUSIC_CONSTANT = 1.05946309436;
	//Variables for tonality menu
	private static final int CHROMATIC=0, MAJOR=1, MINOR=2, RESCAN=3, CANCEL=4;
	private static int currOption = 0;
	/**
	 * Constructor for the music state. Retrieves a robot object!
	 */
	public MusicState(StateMachine sam) {
		robot = Robot.getInstance();
		this.sam = sam;
	}

	private static boolean[][] scanArray = { 
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
{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },

};

	/**
	*
	*
	*
	*/
	@Override
	public void init() {
		LCD.clear();
		//scanArray = robot.scan();
		generateTones();
		drawMenu();
		chooseTonality();
	}
	/**
	*
	*
	*/
	@Override
	public void resume() {
	}

	/** Method for creating notes for noteArray, by the function 
	*	f(n) = 12th-rt(2)^(n-49) * 440
	*	where 440 is the note A4, and n is the n-th key on a 88-key piano.
	*	Read more about this on <a href="http://en.wikipedia.org/wiki/Piano_key_frequencies">Wikipedia (Piano key frequencies</a>
	*
	*/
	private static void generateTones() {
		for(int i = 0; i < noteArray.length; i++) {
			noteArray[i] = (int)(Math.round((Math.pow(MUSIC_CONSTANT, ((28 + i) - 49)) * 440)));
		}
	}

	/**
	* 	Menu for choosing what tonality the music should be in
	*	Major, frygian
	*	Minor, harmonic
	*	All-tone bonanza 
	*/
	private static void chooseTonality() {
		while(Button.ESCAPE.isUp()) {
			Thread.yield();
			if(Button.ENTER.isDown()) {
				Button.ENTER.waitForPressAndRelease();
				switch(currOption) {
					
					case CHROMATIC:
					generateChromatic();
					break;
					
					case MAJOR:
					generateMajorFrygian();	
					break;
					
					case MINOR:
					generateMinorHarmonic();
					break;
					
					case RESCAN:
					scanArray = robot.scan();
					LCD.clearDisplay();
					
					case CANCEL:
					return;
				}

			}
			else if(Button.LEFT.isDown()) {
				Button.LEFT.waitForPressAndRelease();
				currOption--;
				if(currOption<0) currOption=4;
				drawMenu();
			}
			else if(Button.RIGHT.isDown()) {
				Button.RIGHT.waitForPressAndRelease();
				currOption++;
				if(currOption>4) currOption=0;
				drawMenu();
			}
		}
	}


	private static void generateMusic() {
		// Generates frequency and duration for composition array
		// First for loop goes through the rows of the scanArray (y-axis)
		for(int y = 0; y < scanArray[0].length; y++) {
			int trueCounter = 0;
			// First nested for loop goes through the first half of the row
			// Count the number of "true", this amount will be used to chose a note from the scaleArray
				for(int x = 0; x < (scanArray.length / 2); x++) {
					if(scanArray[x][y])
						trueCounter++;
				}
			
				composition[0][y*2] = scaleArray[trueCounter];
				composition[1][y*2+1] = trueCounter * 40;
				trueCounter = 0;
			
				// Second nesten for loop goes through the second half of the row
				for(int x = (scanArray.length / 2); x < scanArray.length; x++) {
					if(scanArray[x][y])
						trueCounter++;
				}
			
				composition[0][y*2 + 1] = scaleArray[trueCounter];
				composition[1][y*2] = trueCounter * 40;
				trueCounter = 0;
		}
	}

	// Creates a scaleArray equal to the original noteArray
	private static void generateChromatic() {
		scaleArray = noteArray;
		generateMusic();
		MusicPlayer.play(composition);
		LCD.clearDisplay();
	}
	// Creates a scaleArray with notes from the C major frygian scale
	private static void generateMajorFrygian() {
		
		generateMusic();
		MusicPlayer.play(composition);
		LCD.clearDisplay();
	}

	// Creates a scaleArray with notes from the C Minor harmonic scale
	private static void generateMinorHarmonic() {
		
		generateMusic();
		MusicPlayer.play(composition);
		LCD.clearDisplay();
	}
	
	// Method for drawing menu, choosing what scale to play
	public static void drawMenu() {
		LCD.clearDisplay();
		if(currOption==CHROMATIC) {
			LCD.drawString(">Chromatic", 2, 0, true);
		}
		else {
			LCD.drawString(" Chromatic", 2, 0, false);
		}
		if(currOption==MAJOR) {
			LCD.drawString(">Major frygian", 2, 1, true);
		}
		else {
			LCD.drawString(" Major frygian", 2, 1, false);
		}
		if(currOption==MINOR) {
			LCD.drawString(">Minor harmonic", 2, 2, true);
		}
		else {
			LCD.drawString(" Minor harmonic", 2, 2, false);
		}
		if(currOption==RESCAN) {
			LCD.drawString(">Rescan sheet", 2, 4, true);
		}
		else {
			LCD.drawString(" Rescan sheet", 2, 4, false);
		}
		if(currOption==CANCEL) {
			LCD.drawString(">Cancel", 2, 5, true);
		}
		else {
			LCD.drawString(" Cancel", 2, 5, false);
		}
	}		
}

