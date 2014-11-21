import java.io.*;
import lejos.nxt.*;
import lejos.nxt.comm.*;

/**
*	SlaveState.java
*
*	This state sets the NXT into a slave mode, meaning it will first listen and wait for a connection,
*	then listen to the stream for incoming command-bytes.
*	6 commands is defined, where 0 is special, because it will (or shall) de followed by 64*64 single
*	bits that together for a two-dimensional boolean array.
*
*	This class is made to function with the Drawduck PC application.
*
*	@author TeamBeard
*/
class SlaveState {

	private boolean[][] sourceArray;

	private MusicGenerator generator;

	private final static byte
		TRANSFER 			= 0,
		PLAY_LYDIAN 		= 1,
		PLAY_HARMONIC 		= 2,
		PLAY_CHROMATIC 		= 3,
		DRAW 				= 4,
		DISCONNECT 			= 5;

	public SlaveState() {
		LCD.clearDisplay();
		System.out.println("Waiting for \nconnection ...");
		sourceArray = new boolean[64][64];
		generator = new MusicGenerator();
	
		NXTConnection connection = Bluetooth.waitForConnection(60*1000, NXTConnection.PACKET);	// Timeout at 30s


		if(connection != null) {
			try {
				DataInputStream dis = new DataInputStream(connection.openInputStream());

				LCD.clear();
				LCD.drawString("Waiting for BT input..", 0, 2, false);

				byte b = dis.readByte();
				while(b != DISCONNECT) {
					executeCommand(b, dis);

					LCD.clear();
					LCD.drawString("Waiting for BT input..", 0, 2, false);

					b = dis.readByte();
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void executeCommand(byte b, DataInputStream dis) {
		switch(b) {
			case TRANSFER:
				for(byte x=0; x<sourceArray.length; x++) {
					for(byte y=0; y<sourceArray[0].length; y++) {
						try {
							sourceArray[x][y] = dis.readBoolean();
						}
						catch(IOException e) {
							System.out.println("ERROR IN READING");
							sourceArray[x][y] = false;
						}
					}
				}
				Sound.twoBeeps();
				break;
			case PLAY_LYDIAN:
				generator.generateMajorLydian(sourceArray);
				break;

			case PLAY_HARMONIC:
				generator.generateMinorHarmonic(sourceArray);
				break;

			case PLAY_CHROMATIC:
				generator.generateChromatic(sourceArray);
				break;

			case DRAW:
				Robot.getInstance().draw(sourceArray);
				break;
			default:
				System.out.println("ERROR");
				break;
		}
	}
}
