import javax.swing.*;
import java.util.Random;
import java.awt.*;
public class Dots extends JApplet {
	public void init() {
		add(new JPanel() {
			public void paintComponent(Graphics g) {
				Random rnd = new Random(90009);
				int x,y, lx = 0, ly = 0;
				int firstX = -1, firstY = -1;
				for(int i=0; i<10; i++) {
					x = rnd.nextInt(400);
					y = rnd.nextInt(400);
					if(i > 0) {
						g.drawLine(lx, ly, x, y);
					}
					else {
						firstX = x;
						firstY = y;
					}
					if(i%2==0) g.fillOval(x-10, y-10, 20, 20);
					else g.drawOval(x-10, y-10, 20, 20);

					lx = x;
					ly = y;
				}
				g.drawLine(lx, ly, firstX, firstY);
			}
		});
	}
}