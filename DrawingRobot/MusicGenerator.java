import java.util.Random;
import lejos.nxt.*;

class MusicGenerator {
	private int[] noteArray = new int[25];
	private int[] scaleArray;
	private int[][] composition = new int[2][512];
	private static final double MUSIC_CONSTANT = 1.05946309436;

	public MusicGenerator() {
		generateTones();
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
	public void generateMusic(boolean[][] scanArray) {
		short[] scanArrayConverted = convertBool2DToShort2D(scanArray);
		Random randomLastNote = new Random();
		Random random = new Random(1337); // Random generator for spicing things up if the same leap is made many times in  a row		

		int noteLength; // stores the int that is returned from getNoteLength()
		int indexOfLeaps; // stores the int that is returned from getNoteLeapBits()
		int leap = 0; // 
		int turnCounter = 0;
		int lastLeap = 0; // Keeps a track of what the last leap was
		int leapRepeatCounter = 0; // Counts how many times a leap has been repeated
		int lastNote = randomLastNote.nextInt(14); // keeps track of the last note (the number is used as index in scaleArray). Starts at 11, the note C.


		// Check what int getNoteLengthBits returns and set the frequency of the note
		for(int i = 0; i < scanArrayConverted.length; i++) {
			
			noteLength = getNoteLengthBits(scanArrayConverted[i]);
			switch(noteLength) {
					case 0:
					composition[1][i] = 100; // 
					break;

					case 1:
					composition[1][i] = 59; // 
					break;

					case 2:
					composition[1][i] = 240; // 
					break;

					case 3:
					composition[1][i] = 120; // 
					break;
			}

			// Check what int getNoteLeapBits returns and set the note leap
			indexOfLeaps = getNoteLeapBits(scanArrayConverted[i]);
			
			// Index 0:
			// Make a second leap
			if(indexOfLeaps < 1) {
				if(turnCounter > 1) {
					if(random.nextInt(2) == 0) {
						leap = 2;
						turnCounter = 0;
					}
					else {
						leap = -2;
						turnCounter = 0;
					}
				}
				else {
					// Is the last leap a second? If not, make a random second leap, -1 is down, 1 is up 
					if(lastLeap != 1 && lastLeap != -1) {
						if(random.nextInt(2) == 0) {
							leap = 1;
						} 
						else {
							leap = -1;
						}
					}

					else if(lastLeap == 1 && leapRepeatCounter > 4) {
						leap = -1;
						leapRepeatCounter = 0;
						turnCounter++;
					}

					else if(lastLeap == -1 && leapRepeatCounter > 4) {
						leap = 1;
						leapRepeatCounter = 0;
						turnCounter++;
					}

					else if(lastLeap == 1) {
						leap = 1;
					}

					else if(lastLeap == -1) {
						leap = -1;
					}
				}	
			}

			// Index 1-62:
			// Make a random leap, fourth, fifth or sixth
			else if(isBetween(indexOfLeaps, 1, 62)) {
				leap = random.nextInt(3) + 3;
			}

			// Index 63: 
			// Make a third leap
			
			else {
				//insertPrintln("INDEX 63");
				if(turnCounter > 2) {
					if(random.nextInt(2) == 0) {
						leap = 1;
						turnCounter = 0;
					}
					else {
						leap = -1;
						turnCounter = 0;
					}
				}

				else {

					if(lastLeap != -2 && lastLeap != 2) {
						if(random.nextInt(2) == 0) {
							leap = 2;
						}
						else {
							leap = -2;
						}
					}
				
					else if(lastLeap == 2 && leapRepeatCounter > 2) {
						leap = -2;
						leapRepeatCounter = 0;
						turnCounter++;
						//insertPrintln("turned down");
					}
					else if(lastLeap == -2 && leapRepeatCounter > 2) {
						leap = 2;
						leapRepeatCounter = 0;
						turnCounter++;
						//insertPrintln("turned up");
					}
					else if(lastLeap == 2) {
						leap = 2;
						//insertPrintln("going up");
					}
					else if(lastLeap == -2) {
						leap = -2;
						//insertPrintln("Going down");
					}
				}
			}

			// Check if lastNote + leap is out of bounds for scaleArray
			if((lastNote + leap) > 0 && lastNote + leap < scaleArray.length) {
				composition[0][i] = scaleArray[lastNote + leap];
				lastNote += leap;
				lastLeap = leap;
			}
			else {
				composition[0][i] = scaleArray[11];
				lastLeap = 0;
				lastNote = random.nextInt(3) + 8;
			}
			
			if(lastLeap == leap) {
				leapRepeatCounter++;
			}

			
			

			// Check if the duration of the current note in composition is set to 59ms (pause length)
			// if it is, set the frequency of current note to 0. It will be ignored in Music Player and instead sleep for 59ms
			if(composition[1][i] == 59) {
				composition[0][i] = 0;
			}
		}
	}

	private boolean isBetween(int x, int lower, int upper) {
		return lower <= x && x <= upper;
	}
	
	// Creates a scaleArray equal to the original noteArray
	public void generateChromatic(boolean[][] scanArray) {
		scaleArray = noteArray;
		generateMusic(scanArray);
		MusicPlayer.play(composition);
	}
	
	// Creates a scaleArray with notes from the C major lydian scale
	public void generateMajorLydian(boolean[][] scanArray) {
		scaleArray = new int[] 
		{noteArray[0], noteArray[2], noteArray[4], noteArray[5], noteArray[7], 
		noteArray[9], noteArray[11], noteArray[12], noteArray[14], noteArray[16], 
		noteArray[17], noteArray[19], noteArray[21], noteArray[23], noteArray[24]
		};
		generateMusic(scanArray);
		MusicPlayer.play(composition);
	}

	// Creates a scaleArray with notes from the C Minor harmonic scale
	public void generateMinorHarmonic(boolean[][] scanArray) {
		scaleArray = new int[]
		{noteArray[0], noteArray[2], noteArray[3], noteArray[5], noteArray[7], 
		noteArray[8], noteArray[11], noteArray[12], noteArray[14], noteArray[15], 
		noteArray[17], noteArray[19], noteArray[20], noteArray[23], noteArray[24]
		};
		generateMusic(scanArray);
		MusicPlayer.play(composition);
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

	private void insertPrintln(String output) {
		System.out.println(output);
		try{
			Thread.sleep(1500);
		}
		catch(Exception e) {
					
		}
	}
}
