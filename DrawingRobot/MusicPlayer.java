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
		while(Button.ESCAPE.isUp()) {
			for(int i = 0; Button.ESCAPE.isUp() && (i < composition[0].length); i++) {
				Sound.playTone(composition[0][i], composition[1][i]);
				try {
					Thread.sleep(composition[1][i]);
				} 
				catch (InterruptedException e) {
					System.out.println("Something went wrong, very very wrong. RUN!");
				}
			}				
		}
	}
}