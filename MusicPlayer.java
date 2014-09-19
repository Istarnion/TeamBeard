import lejos.nxt.*;
import java.io.*;
import java.util.ArrayList;

class MusicPlayer extends Thread {

	File f;

	public MusicPlayer(String track) {
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
			boolean running = true;
			BufferedReader reader;
			while(running) {
				try {
					reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
					
					int i = 0;
					for(String s=reader.readLine(); (s != null && !s.isEmpty()); s=reader.readLine()) {
						i = s.indexOf(" ");
						int note = Integer.parseInt(s.substring(0, i));
						if(note != 0) {
							Sound.playTone(Integer.parseInt(s.substring(0, i)), Integer.parseInt(s.substring(i+1)));
						}
						try {
							Thread.sleep(Integer.parseInt(s.substring(i+1)));
						}
						catch(InterruptedException e) {
							System.out.println("Sleeping failed");
							running = false;
							System.exit(0);
						}
					}
					reader.close();
				}
				catch(Exception e) {
					running =false;
					e.printStackTrace();
				}
				Thread.yield();
			}
		}
	}
}
