import lejos.nxt.*;

class Rally {

	public static void main(String[] args) throws Exception {
		MusicPlayer mp = new MusicPlayer("track.txt");
		mp.start();
		while(!Button.ESCAPE.isDown()) {
			Thread.yield();
		}
		mp.interrupt();
	}

}
