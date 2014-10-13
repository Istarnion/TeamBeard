import javax.swing.*;
import java.awt.*;

class SmileyApp {
	public static void main(String[] args) {
		JFrame frame = new JFrame("SmileyApp - Java");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Fill
				g.setColor(new Color(0xE6D415));	// Yellow
				g.fillOval(20, 20, 360, 360);

				// Outline
				g.setColor(Color.BLACK);
				g.drawOval(20, 20, 360, 360);
				g.drawOval(21, 21, 358, 358);
				g.drawOval(19, 19, 362, 362);

				// Eyes
				g.fillOval(75, 155, 70, 70);
				g.fillOval(255, 155, 70, 70);

				// Mouth
				g.fillArc(90, 220, 220, 120, 0, -180);
			}
		};
		panel.setPreferredSize(new Dimension(400, 400));
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
