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
			
			if(composition[0][i] == 0) {
				try {
					Thread.sleep(composition[1][i]);	
				}
				catch (InterruptedException e) {
					System.out.println("Something went wrong in MusicPlayer!");
				}
				
			}

			else {
				Sound.playTone(composition[0][i], composition[1][i]);
				try {
					Thread.sleep(composition[1][i]);
				} 
				catch (InterruptedException e) {
					System.out.println("Something went wrong in MusicPlayer!");
				}
			}

			// Progress bar!
			int progress = (int)(i* (100.0/(double)composition[0].length));
			LCD.drawString(progress + "%", 0, 2);
			for(int x=0; x<10; x++) {
				LCD.setPixel(progress, 25+x, 1);
			}
		}				
	}
}
