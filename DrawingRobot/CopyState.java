import lejos.nxt.*;


/**
 * 
 * 
 * 
 */

public class CopyState {

	Robot robot;

	boolean[][] drawing;

	public void init() {

		robot = robot.getInstance();
		drawing = robot.scan();

		LCD.clear();
		LCD.drawString("Insert copy paper",1,1,false);

		Button.ENTER.waitForPressAndRelease();

		robot.setXPos(0);
		robot.setYPos(0);
		int x = 0;
		int y = 0;

		LCD.clear();
		LCD.drawString("Printing...",1,1,false);

		while (robot.getYPos() < robot.Y_POS_MAX) {
			while (robot.getXPos() < robot.X_POS_MAX) {
				if (drawing[x][y] == true) {
					robot.setMarker(true);
					robot.setMarker(false);
				}
				x++;
				robot.setXPos(0);
			}
			y++;
			robot.setYPos(0);
		}
	}

	public void resume() {}


	/**
	 * 
	 * 
	 * 
	 */
	public CopyState() {


/*		if 

		PSEUDOKODE:
		
		1: Første piksel i første linje blir sjekket
		2.1: Svart: senk tusj
		2.2: Hvit: beveg x til neste
		3.1: Svart til hvit: Hev tusjen (TOGGLE)
		3.2: Svart til svart: La tusj være
		3.3: Hvit til hvit: beveg x til neste
		3.4: Hvit til svart: senk tusjen (TOGGLE)
		4: Kommet til siste x-verdi, men ikke til siste y-verdi: Hev tusj, øk y-verdi med 1, gå til x = 0, kjør fra pt. 1

		// Svart til svart
		if ([x][y] == true && [x+1][y] == true) {
			setMarker(true); // trengs ikke
			setXPos++;	 // kan settes utenfor ifs
		}

		// Svart til hvit
		if ([x][y] == true && [x+1][y] == false) {
			setMarker(false);
			setXPos++;
		}

		// Hvit til svart
		if ([x][y] == false && [x+1][y] == true) {
			setMarker(true);
			setXPos++;
		}

		// Hvit til hvit
		if ([x][y] == false && [x+1][y] == false) {
			setXPos++;
*/
	}
}








