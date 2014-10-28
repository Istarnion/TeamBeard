import lejos.nxt.*;
import lejos.nxt.comm.*;
import java.io.*;

class CommTest {

	public static void main(String[] args) {
		LCD.drawString("Waiting for", 1, 2, false);
		LCD.drawString("connection..", 1, 3, false);
		NXTConnection connection = Bluetooth.waitForConnection((30*1000), NXTConnection.PACKET);

		if(connection == null) {
			System.out.println("Connection      attempt failed..");
			sleep(4000);
			System.exit(1);
		}

		try {
			LCD.drawString("Connected", 1, 4, false);
			DataInputStream dis = connection.openDataInputStream();

			// Reads int by int from the DataInputStream, and play the note of apropriate frequency.
			for(int i=-1; i!=0; i=dis.readInt()) {
				if(i>0){
					Sound.playTone(i, 500);
					sleep(500);
				}
			}

			connection.close();
		}
		catch(IOException ioe) {
			System.out.println("IO failure");
		}
		catch(Exception ie) {
			System.out.println("Failure");
		}
		finally {
			sleep(5000);			
		}
	}

	private static void sleep(int i) {
		try {
			Thread.sleep(i);
		}
		catch(Exception e){}
	}
}
