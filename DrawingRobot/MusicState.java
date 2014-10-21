import lejos.nxt.*;
import java.util.Random;
/**
 * Music State!
 * This class contains methods for making music from a 2dimensional boolean array
 * 
 * @author Ole Jørgen Skogstad
 */

public class MusicState implements State {
	private static int[] noteArray = new int[25];
	private static int[] scaleArray;
	private static int[][] composition = new int[2][140];
	private static boolean[][] scanArray;
	private Robot robot;
	private static final double MUSIC_CONSTANT = 1.05946309436;
 
	
	/**
	 * Constructor for the music state. Retrieves a robot object!
	 */
	public MusicState() {
		robot = Robot.getInstance();
	}

	/**
	*
	*
	*
	*/
	@Override
	public void init() {
		scanArray = robot.scan();
		test();
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
	* Choose what tonality the music should be in
	*	Major, frygian
	*	Minor, harmonic
	*	All-tone bonanza 
	*/
	private static void chooseTonality() {
	}

	private void generateAllToneBonanza() {

		// Generates frequency for composition array
		// First for loop goes through the rows of the scanArray (y-axis)
		for(int y = 0; y < scanArray[0].length; y++) {
			int trueCounter = 0;

			// First nested for loop goes through the first half of the row
			// Count the number of "true", this amount will be used to chose a note from the scaleArray
			for(int x = 0; x < (scanArray.length / 2); x++)
			{
				if(scanArray[y][x])
					trueCounter++;
			}
			composition[0][y*2] = noteArray[trueCounter];
			composition[1][y*2+1] = trueCounter * 100;
			trueCounter = 0;
			// Second nesten for loop goes through the second half of the row
			for(int x = (scanArray.length / 2); x < scanArray.length; x++)
			{
				if(scanArray[y][x])
					trueCounter++;
			}
			composition[0][y*2 + 1] = noteArray[trueCounter];
			composition[1][y*2] = trueCounter * 100;
			trueCounter = 0;
		}


		// Generate duration for composition array


	}

	private void generateMajorFrygian() {

	}

	private void generateMinorHarmonic() {

	}
	

	// ALL BEYOND THIS POINT WILL BE PURGED IN HELLFIRE
	//---------------------------------------------------------------------
	//---------------------------------------------------------------------
	//---------------------------------------------------------------------
	//TEMP METHODs
	public static void bullshitScanArray() {
		Random random = new Random();
		scanArray = new boolean[50][70];

		for(int i = 0; i < scanArray.length; i++)
		{
			for(int j = 0; j < scanArray[0].length; j++)
			{
				scanArray[i][j] = random.nextBoolean();
			}
		}
	}

	// TEMP MAIN THREAD
	public static void test(){
		generateTones();
		bullshitScanArray();
		while(Button.ESCAPE.isUp()) {
			for(int i = 0; i < composition[0].length; i++)
			{

				Sound.playTone(composition[0][i], composition[1][i]);
			}				
		}
	}		
}

