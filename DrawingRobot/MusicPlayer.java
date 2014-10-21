import lejos.nxt.*;

/**
* This class plays the composition made in the MusicState
*
*
*
*/
class MusicPlayer {

	private MusicPlayer() {	
	}

	public static void play(int[][] composition) {
		LCD.clearDisplay();
		for(int i = 0; Button.ESCAPE.isUp() && (i < composition[0].length); i++) {
			Sound.playTone(composition[0][i], composition[1][i]);
			try {
				Thread.sleep(composition[1][i]);
			} 
			catch (InterruptedException e) {
				System.out.println("Something went wrong, very very wrong. RUN!");
			}

			int progress = (i* (100/composition[0].length));
			for(int x=0; x<10; x++) {
				LCD.setPixel(progress, 55+x, 1);
			}
		}				
	}
}
