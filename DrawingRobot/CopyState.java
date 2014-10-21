/**
 * 
 * 
 * 
 */

public class CopyState implements State
{

	Robot robot;

	boolean[][] drawing;

	public void init()

	public void resume()


	/**
	 * 
	 * 
	 * 
	 */
	public CopyState(){

		robot = Robot.getInstance;
		drawing = robot.scan();

		System.out.println("Insert copy paper");

		Button.ENTER.waitForPressAndRelease();

		Robot.setXPos(0);
		Robot.setYPost(0);

		while (Robot.Y_POS <= Robot.Y_POS_MAX) {
			while (Robot.X_POS <= Robot.X_POS_MAX) {
				if ([x][y] == true) {
					setMarker(true);
					setMarker(false);
				}
			}
		}

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








