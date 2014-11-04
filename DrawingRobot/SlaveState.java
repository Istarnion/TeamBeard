import java.io.*;
import lejos.nxt.*;
import lejos.nxt.comm.*;

class SlaveState {

	private boolean[][] sourceArray;

	private MusicGenerator generator;

	private final static byte
		TRANSFER = 0,
		PLAY = 1,
		DRAW = 2,
		DISCONNECT = 3;

	public SlaveState() {
		sourceArray = new boolean[64][64];
		generator = new MusicGenerator();

		NXTConnection connection = Bluetooth.waitForConnection(30*1000, NXTConnection.PACKET);	// Timeout at 30s

		if(connection != null) {
			try {
				DataInputStream dis = connection.OpenDataInputStream();

				LCD.clear();
				LCD.drawString("Waiting for BT input..");

				byte b = dis.readByte();
				while(b != DISCONNECT) {
					executeCommand(b, dis);

					LCD.clear();
					LCD.drawString("Waiting for BT input..");

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
				break;
			case PLAY:
				generator.generateMusic(sourceArray, "lydian");
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
