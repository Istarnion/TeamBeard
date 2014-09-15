import lejos.nxt.*;
import java.io.*;
import java.util.ArrayList;

class MusicPlayer extends Thread {

	private ArrayList<String> notes;

	public MusicPlayer(String track) {
		try {
			File f = new File(track);
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(f)));

			notes = new ArrayList<String>();
			for(String line=reader.readLine(); line != null; line=reader.readLine()) {
				notes.add(line);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		if(notes != null) {
			int i;
			for(String s : notes) {
				i = s.indexOf(" ");
				Sound.playTone(Integer.parseInt(s.substring(0, i)), Integer.parseInt(s.substring(i)));
				try {
					Thread.sleep(Integer.parseInt(s.substring(i)));
				}
				catch(InterruptedException e) {
					// Nothing
				}
			}
		}
	}
}
