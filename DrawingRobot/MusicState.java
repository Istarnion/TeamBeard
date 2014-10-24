import lejos.nxt.*;
import java.util.Random;

/**
 * Music State!
 * This class contains the magic that makes the music!
 * 
 * @author TeamBeard
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
	// Array for storing the final tone frequency/length
	private int[][] composition = new int[2][512];
	//
	private boolean[][] scanArray;
	//
	private short[] scanArrayConverted;
	//
	private Robot robot;
	//
	private static final double MUSIC_CONSTANT = 1.05946309436;

	/**
	 * Constructor for the music state. Retrieves a robot object!
	 *
	 *@param sam An instance of the statemachine in use, so objects of MusicState can manipulate the State stack
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
		LCD.drawString("Scanning!", 2, 2, false);
		LCD.drawString("Please wait ...", 2, 3, false);
		scanArray = robot.scan();
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
		scanArrayConverted = convertBool2DToShort2D(scanArray);
		int noteLength; // stores the int that is returned from getNoteLength()
		int indexOfLeaps; // stores the int that is returned from getNoteLeapBits()
		int leap; // 
		int lastLeap = 0; // Keeps a track of what the last leap was
		int leapRepeatCounter = 0; // Counts how many times a leap has been repeated
		int lastNote = 11; // keeps track of the last note (the number is used as index in scaleArray). Starts at 11, the note C.
		Random random = new Random(1337); // Random generator for spicing things up if the same leap is made many times in  a row

		// Check what int getNoteLengthBits returns and set the frequency of the note
		for(int i = 0; i < scanArrayConverted.length; i++) {
			noteLength = getNoteLengthBits(scanArrayConverted[i]);
			switch(noteLength) {
					case 0:
					composition[1][i] = 300; // 500ms, this is the 1/8 note length
					break;

					case 1:
					composition[1][i] = 299; // 299ms, this is the pause length (set to one off the 1/8 note length)
					break;

					case 2:
					composition[1][i] = 150; // 250ms, this is the 1/16 note length
					break;

					case 3:
					composition[1][i] = 600; // 1000ms, this the 1/4 note length
					break;
			}

			// Check what int getNoteLeapBits returns and set the note leap
			indexOfLeaps = getNoteLeapBits(scanArrayConverted[i]); //
				if(indexOfLeaps < 5) {
					leap 		= 1; //makes a second leap up
					
				}
				else if(indexOfLeaps < 10) {
					leap 		= -1; // makes a second leap down
				}
				else if(indexOfLeaps < 15) {
					leap 		= 2;// makes a third leap up
				}
				else if(indexOfLeaps < 20) {
					leap 		= -2; // makes a third leap down
				}
				else if(indexOfLeaps < 25) {
					leap 		= 4; // makes a fifth leap up
				}
				else if(indexOfLeaps < 30) {
					leap 		= -4; // makes a fifth leap down
				}
				else if(indexOfLeaps < 35) {
					leap 		= 5; // makes a sixth leap up
				}
				else if(indexOfLeaps < 40) {
					leap 		= -5; // makes a sixth leap down
				}
				else if(indexOfLeaps < 44) {
					leap 		= 2; // makes a fourth leap up
				}
				else if(indexOfLeaps < 48) {
					leap 		= -2; // makes a fourth leap down
				}
				else if(indexOfLeaps < 52) {
					leap 		= 2; // makes a octave leap up
				}
				else if(indexOfLeaps < 56) {
					leap 		= -2; // makes a octave leap down
				}
				else if(indexOfLeaps < 59) {
					leap 		= 2; // makes a sept leap up
				}
				else if(indexOfLeaps < 62) {
					leap 		= -2; // makes a sept leap down
				}
				else {
					leap 		= 2; // Makes no leap
				}

				

				// Updates the counter for repeating leaps
				if(leap==lastLeap) {
					if(lastLeap==0)
						leapRepeatCounter = 10;
					leapRepeatCounter++;
				}
				else
					leapRepeatCounter = 0;



				// Checks if repeating leaps exceeds 3 or 10
				if(leapRepeatCounter > 3 && leapRepeatCounter < 10)
					leap = -leap; // Reverses the leap
				else if(leapRepeatCounter >= 10)
					leap = random.nextInt(15) - 7;


				// Checks if the leap goes out of bounds for the scaleArray. 
				// Changes direction of leap if out of bounds.
				if((lastNote + leap) > 0 && lastNote + leap < scaleArray.length) {
					composition[0][i] = scaleArray[lastNote + leap];
					lastLeap = leap;
					lastNote += leap;
				}
				else {
					composition[0][i] = scaleArray[lastNote - leap];
					lastLeap = -leap;
					lastNote -= leap;
				}


				// Check if the duration of the current note in composition is set to 499ms (pause length)
				// if it is, set the frequency of current note to 0. It will then be ignored in Music Player and instead sleep for 499ms
				if(composition[1][i] == 299)
					composition[0][i] = 0;
		}
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
		noteArray[17], noteArray[19], noteArray[21], noteArray[23], noteArray[24]
		};
		generateMusic();
		MusicPlayer.play(composition);
		menu.drawMenu();
	}

	// Creates a scaleArray with notes from the C Minor harmonic scale
	private void generateMinorHarmonic() {
		scaleArray = new int[]
		{noteArray[0], noteArray[2], noteArray[3], noteArray[5], noteArray[7], 
		noteArray[8], noteArray[11], noteArray[12], noteArray[14], noteArray[15], 
		noteArray[17], noteArray[19], noteArray[20], noteArray[23], noteArray[24]
		};
		generateMusic();
		MusicPlayer.play(composition);
		menu.drawMenu();
	}

	/**
	*	Conversion between a boolean array to a short array.
	*	We use short and not byte, since byte is signed, so theres only seven bits to the actual value.
	*	
	*	@param barray The boolean array that shall be converted. Its length should be dividable by 8.
	*/
	public static short[] convertBoolToShort(boolean[] barray) {
		if(barray.length<8) return null;
		short[] output = new short[(int)(barray.length/8)];	// Makes an array of apropriate length. e.g. length 64 gives an array of length 8

		short total = 0;
		for(byte b=0; b<output.length; b++) {
			total = 0;
			for(byte c=0; c<8; c++) {	// 8 bits per value.
				total += ((barray[b*8+c]?1:0)<<c);	// Adds the bit to the apropriate location in the bit pattern.
			}
			output[b] = total;
		}
		return output;
	}

	/**
	*	Conversion between a two dimensianal boolean array to a two dimensional short array
	*	
	*	@param barray The two dimensional boolean array that shall be converted.
	*/
	public static short[] convertBool2DToShort2D(boolean[][] barray) {
		short[] sarray = new short[barray.length * (barray[0].length/8)];
		short[] s;
		for(byte b=0; b<barray.length; b++) {
			s = convertBoolToShort(barray[b]);
			for(byte c=0; c<barray[0].length/8; c++) {
				sarray[c*64+b] = s[c];
			}
		}
		return sarray;
	}

	// Extracts the to bits used to determine the length of the note
	// Returns a byte to save memory and speed.
	// value 0 - 4 (exlusive)
	private static byte getNoteLengthBits(short s) {
		return (byte)(s>>6);
	}

	// Extracts the last six bits of a short.
	// Returns a byte to save memory and speed.
	// Value 0 - 64(exlusive)
	private static byte getNoteLeapBits(short s) {
		return (byte)(s & ~(3<<6));
	}
}

