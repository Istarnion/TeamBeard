import javax.swing.*;
import java.awt.*;
public class SmileyApplet extends JApplet {
	public void init() {
		add(new JPanel() {
			public void paintComponent(Graphics g) {
				g.setColor(new Color(0xE6D415));		// Yellow
				g.fillOval(20, 20, 360, 360);			// Color fill
				g.setColor(Color.BLACK);				
				g.drawOval(20, 20, 360, 360);			// Outline
				g.fillOval(75, 155, 70, 70);			// Left eye
				g.fillOval(255, 155, 70, 70);			// Right eye
				g.fillArc(90, 220, 220, 120, 0, -180);	// Mouth
			}
		});
	}
}
