import lejos.nxt.*;
import java.io.*;
import java.util.ArrayList;

class MusicPlayer extends Thread {

	File f;

	public MusicPlayer(String track) {
		setDaemon(true);	// A daemon thread will not cause the program to to keep running if all other threads are dead.
		try {
			f = new File(track);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		if(f != null) {
			try {
				boolean running = true;
				BufferedReader reader;
				String s = "";
				int i = 0;
				while(running) {
					try {
						reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)), 16);
						for(s=reader.readLine(); !s.isEmpty(); s=reader.readLine()) {
							if(s.startsWith("END") ||s.trim().equals("") || s.trim().length() == 0 || s.isEmpty()) {
								break;	// Should be uneccesary but..
							}
							i = s.indexOf(" ");
							int note = Integer.parseInt(s.substring(0, i));
							if(note != 0) {
								Sound.playTone(Integer.parseInt(s.substring(0, i)), Integer.parseInt(s.substring(i+1)));
							}
							try {
								Thread.sleep(Integer.parseInt(s.substring(i+1)));
							}
							catch(InterruptedException e) {
								running = false;
								System.exit(0);
							}
						}
					}
					catch(IOException e) {
						running =false;
						e.printStackTrace();
					}
					Thread.yield();
				}
			}
			catch(Exception e) {
				System.exit(0);
			}
		}
	}
}
