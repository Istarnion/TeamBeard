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
			LCD.drawString("Recieved: "+dis.readInt(), 1, 5, false);
			sleep(5000);
		}
		catch(IOException ioe) {
			System.out.println("IO failure");
		}
		catch(Exception ie) {
			System.out.println("Failure");
		}

		sleep(5000);
	}

	private static void sleep(int i) {
		try {
			Thread.sleep(i);
		}
		catch(Exception e){}
	}
}
